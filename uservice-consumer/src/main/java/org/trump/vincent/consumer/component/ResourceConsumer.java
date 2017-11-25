package org.trump.vincent.consumer.component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.ChangedCharSetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vincent on 2017/11/14 0014.
 */
@Component
@PropertySource(value = {"classpath:application.yml"})
public class ResourceConsumer {

    @Value("${uservice.producer.url}")
    private String producerUrl;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public String getUrl(){
        return producerUrl;
    }

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 1、 General Rest Request For Resource Information
     * @return
     */
    public Map seeResourceInfo(){
        String getApi = producerUrl+"/resource/getAll";
        logger.debug("Consuming Request Url: "+getApi);

        List response = restTemplate.getForEntity(getApi,List.class).getBody();
        Map result = new HashMap();
        result.put("data",response);
        result.put("result",true);
        return result;
    }

    /**
     * 2、CircuitBreaker but without fallback
     * @return
     */
    @HystrixCommand
    public Map seeResourceInfo1(){
        String getApi = producerUrl+"/resource/getAll";
        logger.debug("Consuming Request Url: "+getApi);

        List response = restTemplate.getForEntity(getApi,List.class).getBody();
        Map result = new HashMap();
        result.put("data",response);
        result.put("result",true);
        return result;
    }

    /**
     * 3、Fallback when request timeout
     * @return
     */
    @HystrixCommand(fallbackMethod = "seeResourceFallback")
    public Map seeResourceInfo2(){
        String getApi = producerUrl+"/resource/getAll";
        logger.debug("Consuming Request Url: "+getApi);

        List response = restTemplate.getForEntity(getApi,List.class).getBody();
        Map result = new HashMap();
        result.put("data",response);
        result.put("result",true);
        return result;
    }

    protected Map seeResourceFallback(){
        logger.info("Fallback for seeing Resource.");
        Map result = new HashMap();
        result.put("info","Fallback for seeing Resource.");
        return result;
    }

    /**
     * 4、Fallback Chain with exceptions
     *
     * Simple CircuitBreaker Style ：@HystrixCommand to construct synchronous HystrixCommnad Invoker
     * @return
     */
    //    @HystrixCommand(observableExecutionMode = ObservableExecutionMode.LAZY,fallbackMethod = "defaultFallBack")
    //    @CacheResult
    @HystrixCommand(fallbackMethod = "defaultFallBack")
    public Map seeResourceInfo3(){

        String getApi = producerUrl+"/resource/getAll";
        logger.debug("Consuming Request Url: "+getApi);

        List response = restTemplate.getForEntity(getApi,List.class).getBody();
        Map result = new HashMap();
        result.put("data",response);
        result.put("result",true);
        return result;
    }



    @HystrixCommand(fallbackMethod = "stopChain" ,ignoreExceptions = {ChangedCharSetException.class})
    protected Map defaultFallBack(){
        throw new RuntimeException("Programming Excute Error.");
    }

    /**
     * the final fallback method
     * @return
     */
    protected Map stopChain(){
        Map result = new HashMap();
        result.put("info","Stop Fallback Chain.");
        return result;
    }
}
