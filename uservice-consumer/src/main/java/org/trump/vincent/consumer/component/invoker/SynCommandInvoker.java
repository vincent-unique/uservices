package org.trump.vincent.consumer.component.invoker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trump.vincent.consumer.component.command.ConsumerCommand;

import java.util.List;
import java.util.Map;

/**
 * Created by Vincent on 2017/11/14 0014.
 */

//@Component
public class SynCommandInvoker implements Invoker<Map> {

    @Autowired
    private ConsumerCommand consumerCommand;

    private List result;
    public void consumerInvoke(){
        result = consumerCommand.execute();
    }

    public List getResult(){
        return this.result;
    }
}
