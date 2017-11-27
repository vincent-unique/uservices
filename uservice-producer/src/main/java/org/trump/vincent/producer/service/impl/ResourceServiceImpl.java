package org.trump.vincent.producer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.trump.vincent.producer.dao.ResourceRepository;
import org.trump.vincent.producer.domain.Resource;
import org.trump.vincent.producer.service.ResourceService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Vincent on 2017/11/13 0013.
 */
@Service("ResourceService")
@Transactional
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public Resource findById(String id){
        return resourceRepository.findById(id);
    }

    public List<Resource> findByName(String name){
        return resourceRepository.findAllByName(name);
    }

    public List<Resource> findAll(){
        return resourceRepository.findAll();
    }


    public Boolean save(Resource resource){
        try {
            resourceRepository.save(resource);
            return true;
        }catch (final Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean save(List<Resource> resources){
        try {
            resourceRepository.save(resources);
            return true;
        }catch (final Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean update(String id, Map properties){
        if(id==null||id.isEmpty()){
            throw new NullPointerException("Null Resource ID.");
        }

        if(properties==null ||properties.size()<1 ){
            logger.warn("Not Allowed to Update Operation With Null Properties.");
            return false;
        }
        Resource resource = resourceRepository.findById(id);

        if(resource ==null){
            logger.warn("Not Found Resource With [ID="+id+"].");
            return false;
        }
        Set<Map.Entry> parameters = properties.entrySet();
        for (Map.Entry entry : parameters) {
            String key = String.valueOf(entry.getKey());
            Object value = entry.getValue();
            if (key.equalsIgnoreCase("name")){
                resource.setName(String.valueOf(value));
            }else if(key.equalsIgnoreCase("ccid")){
                resource.setCcid(String.valueOf(value));
            }else if(key.equalsIgnoreCase("iconUrl")){
                resource.setIconUrl(String.valueOf(value));
            }else if(key.equalsIgnoreCase("creatorId")){
                resource.setCreatorId(String.valueOf(value));
            }else if(key.equalsIgnoreCase("creatorName")){
                resource.setCreatorName(String.valueOf(value));
            }else if(key.equalsIgnoreCase("aclContent")){
                resource.setAclContent(String.valueOf(value));
            }else if(key.equalsIgnoreCase("deleteFlag")){
                resource.setDeleteFlag(Integer.valueOf(String.valueOf(value)));
            }else {
                logger.warn("Not Found OR Unsupported For ["+key+"].");
            }
        }
        return true;
    }

    public Boolean delete(String id){
        if(id==null||id.isEmpty()){
            throw new NullPointerException("Null Resource ID.");
        }
        try {
            resourceRepository.delete(id);
        }catch (final Exception e){
            logger.error("Exception Occus In Deleting Resource.",e);
            return false;
        }
        return true;
    }
}
