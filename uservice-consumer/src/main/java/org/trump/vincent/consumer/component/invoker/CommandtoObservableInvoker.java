package org.trump.vincent.consumer.component.invoker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trump.vincent.consumer.component.command.ConsumerObservableCommand;
import rx.Observable;

import java.util.List;

/**
 * Created by Vincent on 2017/11/14 0014.
 */
//@Component
public class CommandtoObservableInvoker implements Invoker<List> {

    @Autowired
    private ConsumerObservableCommand consumerObservableCommand;

    private Observable<List> observing;

    public void consumerInvoke(){
        observing = consumerObservableCommand.toObservable();
    }


    public Observable<List> getObserving() {
        return observing;
    }
}
