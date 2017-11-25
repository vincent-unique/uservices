package org.trump.vincent.consumer.component.invoker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trump.vincent.consumer.component.command.ConsumerCommand;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Vincent on 2017/11/14 0014.
 */
//@Component
public class AsynCommandInvoker implements Invoker<Map> {

    @Autowired
    private ConsumerCommand consumerCommand;

    private Future<List> futureResult;

    public Future<List> getResult() {
        return futureResult;
    }


    public void consumerInvoke(){
        futureResult =  consumerCommand.queue();
    }

}
