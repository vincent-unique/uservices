package org.trump.vincent.producer.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.trump.vincent.producer.domain.Resource;
import org.trump.vincent.producer.service.ResourceService;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Vincent on 2017/11/13 0013.
 */

/**
 * Scope Value: request、prototype、session、glbal session、singleton
 * session means new Instance for different client session
 */
//@Scope("session")
@RestController
public class ResourceController {

    @javax.annotation.Resource(name = "ResourceService")
    private ResourceService resourceService;


    private AtomicInteger resNum = new AtomicInteger(0);

    private Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * @test
     * @return
     */
    @GetMapping(value = "resource/generate")
    public ResponseEntity generateResource(){
        Resource resource = new Resource().setId(UUID.randomUUID().toString())
                .setCreated(new java.util.Date(System.currentTimeMillis()).toString())
                .setCreatorId("admin")
                .setCreatorName("vincent")
                .setName("《上古对决：黄执中 pk 林正疆》")
                .setDeleteFlag(1)
                .setUsable(true);
        boolean flag = resourceService.save(resource);
        Map response = new HashMap();
        response.put("result",flag);
        response.put("info",resource);
        return new ResponseEntity(response,HttpStatus.OK);
    }


    @GetMapping(value = "resource/get/{id}")
    public ResponseEntity getResourceById(@PathVariable String id){
        Resource resource = resourceService.findById(id);
        JsonObject jsonObject = resource!=null?new Gson().toJsonTree(resource).getAsJsonObject():null;
        return new ResponseEntity(jsonObject, HttpStatus.OK);
    }

    @GetMapping(value = "resource/getAll")
    public ResponseEntity getAll()  throws InterruptedException{
        /**
         * SpinLock to ensure thread-safe
         */
        resNum.incrementAndGet();

        List<Resource> resources = resourceService.findAll();

        if(resNum.get()%2 == 1){
            TimeUnit.SECONDS.sleep(8);
        }

        return new ResponseEntity(resources,HttpStatus.OK);
    }


    @GetMapping(value = "resource/getByName")
    public ResponseEntity getAllByName(@RequestParam String name){
        /**
         * SpinLock to ensure thread-safe
         */
        resNum.incrementAndGet();

        if(name==null||name.isEmpty()){
            return  new ResponseEntity("Not Allowed Request With Null Name",HttpStatus.BAD_REQUEST);
        }

        List<Resource> resources = resourceService.findByName(name);

        return new ResponseEntity(resources,HttpStatus.OK);
    }

    @PostMapping(value = "resource/create")
    public ResponseEntity createResource(@RequestBody String requestBody){

        /**
         * SpinLock to ensure thread-safe
         */
        resNum.incrementAndGet();

        if(requestBody==null||requestBody.isEmpty()){
            return  new ResponseEntity("Not Allowed Request With Null Parameter",HttpStatus.BAD_REQUEST);
        }
        Map params = new Gson().fromJson(requestBody,Map.class);
        boolean result = false;
        if(params!=null&& params.size()>0){
            Resource resource = new Resource()
                    .setId(UUID.randomUUID().toString())
                    .setCreated(new java.util.Date(System.currentTimeMillis()).toString());
            if(params.containsKey("name")){
                resource.setName(String.valueOf(params.get("name")));
            }
            if(params.containsKey("ccid")){
                resource.setCcid(String.valueOf(params.get("ccid")));
            }
            if(params.containsKey("creatorId")){
                resource.setCreatorId(String.valueOf(params.get("creatorId")));
            }
            if(params.containsKey("creatorName")){
                resource.setCreatorName(String.valueOf(params.get("creatorName")));
            }
//            resource.setIconUrl("")
            if(params.containsKey("iconUrl")){
                resource.setIconUrl(String.valueOf(params.get("iconUrl")));
            }

            result = resourceService.save(resource);
        }

        return new ResponseEntity("The Result "+String.valueOf(result),HttpStatus.OK);
    }

    @PutMapping(value = "resource/update/{id}")
    public ResponseEntity updateResource(@PathVariable String id,@RequestParam String requestBody){
        if(id==null||id.isEmpty()){
            return  new ResponseEntity("Not Allowed Request With Null ID.",HttpStatus.BAD_REQUEST);
        }
        if(requestBody==null||requestBody.isEmpty()){
            return  new ResponseEntity("Not Modification For This Request.",HttpStatus.BAD_REQUEST);
        }
        Map params = new Gson().fromJson(requestBody,Map.class);
        Boolean result = resourceService.update(id,params);

        return new ResponseEntity("The Result "+String.valueOf(result),HttpStatus.OK);

    }
    @DeleteMapping(value = "resource/delete/{id}")
    public ResponseEntity deleteResource(@PathVariable String id){
        if(id==null||id.isEmpty()){
            return  new ResponseEntity("Not Allowed Request With Null ID.",HttpStatus.BAD_REQUEST);
        }
        Boolean result = resourceService.delete(id);
        return new ResponseEntity("The Request Result "+String.valueOf(result),HttpStatus.OK);
    }

}
