package org.trump.vincent.consumer.component.command;

import com.netflix.hystrix.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by Vincent on 2017/11/14 0014.
 */

//@Component
public class ConsumerCommand extends HystrixCommand<List> {

    @Value("${uservice.producer.url}")
    private String producerUrl;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(getClass());


    public ConsumerCommand(Setter setter){
        super(setter);
    }

    @Override
    protected List run() throws Exception {
        String getApi = producerUrl+"/resource/getAll";
        logger.debug("Consuming Request Url: "+getApi);

        List response = restTemplate.getForEntity(getApi,List.class).getBody();
        return response;
    }

    @Override
    protected List getFallback(){
       List result = new ArrayList();
       result.add("ConsummerCommand Fallback Method.");
       return result;
    }
}
