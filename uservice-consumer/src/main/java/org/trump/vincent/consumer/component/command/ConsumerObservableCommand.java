package org.trump.vincent.consumer.component.command;

import com.netflix.hystrix.HystrixObservableCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vincent on 2017/11/14 0014.
 */

//@Component
public class ConsumerObservableCommand extends HystrixObservableCommand<List> {

    @Value("${uservice.producer.url}")
    private String producerUrl;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public ConsumerObservableCommand(Setter setter){
        super(setter);
    }

    protected Observable<List> construct(){
        return Observable.create(new Observable.OnSubscribe<List>() {
            @Override
            public void call(Subscriber<? super List> subscriber) {
                String getApi = producerUrl+"/resource/getAll";
                logger.debug("Consuming Request Url: "+getApi);
                if(!subscriber.isUnsubscribed()) {
                    List response = restTemplate.getForEntity(getApi, List.class).getBody();
                    subscriber.onNext(response);
                    subscriber.onCompleted();
                }
            }
        });
    }
}
