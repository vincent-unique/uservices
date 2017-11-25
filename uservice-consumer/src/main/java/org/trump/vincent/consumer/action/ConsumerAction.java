package org.trump.vincent.consumer.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trump.vincent.consumer.component.ResourceConsumer;

/**
 * Created by Vincent on 2017/11/13.
 */
@RestController
public class ConsumerAction {

    @Autowired
    private ResourceConsumer resourceConsumer;

    @GetMapping(value = "consumer/url")
    public ResponseEntity showProducerUrl(){
        return new ResponseEntity(resourceConsumer.getUrl(), HttpStatus.OK);
    }

    @GetMapping("resource/consumer")
    public ResponseEntity resourceConsumer(){
        return new ResponseEntity(resourceConsumer.seeResourceInfo(),HttpStatus.OK);
    }

    @GetMapping("resource/consumer1")
    public ResponseEntity resourceConsumer1(){
        return new ResponseEntity(resourceConsumer.seeResourceInfo1(),HttpStatus.OK);
    }


    @GetMapping("resource/consumer2")
    public ResponseEntity resourceConsumer2(){
        return new ResponseEntity(resourceConsumer.seeResourceInfo2(),HttpStatus.OK);
    }

    @GetMapping("resource/consumer3")
    public ResponseEntity resourceConsumer3(){
        return new ResponseEntity(resourceConsumer.seeResourceInfo3(),HttpStatus.OK);
    }
}
