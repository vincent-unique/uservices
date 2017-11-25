package org.trump.vincent.consumer.component.annotation;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by Vincent on 2017/11/14 0014.
 */

@Component
@PropertySource(value = {"classpath:application.yml","classpath:bootstrap.yml"})
public class ConsumeComponent {
    @Value("${uservice.producer.url}")
    private String producerUrl;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 1、Synchronous Command Pattern
     * @return
     */
    @HystrixCommand
    public List synConsumer(){
        String getApi = producerUrl+"/resource/getAll";
        logger.debug("Consuming Request Url: "+getApi);

        List response = restTemplate.getForEntity(getApi,List.class).getBody();
        return response;
    }

    /**
     * 2、Asynchronous Command Pattern
     * @return
     */
    @HystrixCommand
    public Future<List> asynConsumer(){
        return new AsyncResult<List>() {
            @Override
            public List invoke() {
                String getApi = producerUrl+"/resource/getAll";
                logger.debug("Consuming Request Url: "+getApi);

                List response = restTemplate.getForEntity(getApi,List.class).getBody();
                return response;
            }
        };
    }


    /**
     * 3、observe() style execution
     * @return
     */
    @HystrixCommand(observableExecutionMode = ObservableExecutionMode.EAGER)
    public Observable<List> consumerObseve(){
        return Observable.create(new Observable.OnSubscribe<List>() {
            @Override
            public void call(Subscriber<? super List> subscriber) {
                String getApi = producerUrl+"/resource/getAll";
                logger.debug("Consuming Request Url: "+getApi);
                if(!subscriber.isUnsubscribed()) {
                    List response = restTemplate.getForEntity(getApi, List.class).getBody();
                    subscriber.onNext(response);
                    subscriber.onNext(response);
                    subscriber.onCompleted();
                }
            }
        });
    }


    /**
     * 4、toObservable() style excution
     * @return
     */
    @HystrixCommand(observableExecutionMode = ObservableExecutionMode.LAZY)
    public Observable<List> consumer2Obsevable(){
        return Observable.create(new Observable.OnSubscribe<List>() {
            @Override
            public void call(Subscriber<? super List> subscriber) {
                String getApi = producerUrl+"/resource/getAll";
                logger.debug("Consuming Request Url: "+getApi);
                if(!subscriber.isUnsubscribed()) {
                    List response = restTemplate.getForEntity(getApi, List.class).getBody();
                    List secondAction = new ArrayList();
                    secondAction.add("Another Command Action.");
                    subscriber.onNext(response);
                    subscriber.onNext(secondAction);
                    subscriber.onCompleted();
                }
            }
        });
    }

    public  void main(String[] args) {

    }

}
