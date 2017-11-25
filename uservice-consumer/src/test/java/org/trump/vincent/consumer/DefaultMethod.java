package org.trump.vincent.consumer;

import java.util.List;

/**
 * Created by Vincent on 2017/11/16 0016.
 */
public class DefaultMethod {

    public static <T> List<T> getall(T lock){
            lock.toString();
            return null;
    }

    public static void main(String[] args) {
//        List<Object > result = DefaultMethod.<Object>getall(new Object());

        String timeStr = "00:32:00:00";String hour = timeStr.split(":")[0];
        String minute = timeStr.split(":")[1];
        Float h = Float.parseFloat(hour);
        h = Float.parseFloat(minute)>0? h + Float.parseFloat(minute)/60:h;

        System.out.printf("result hour: "+h);
    }
}
