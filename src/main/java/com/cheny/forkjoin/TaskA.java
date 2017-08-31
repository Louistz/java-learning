package com.cheny.forkjoin;

import com.cheny.concurrency.threadlocal.Task;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * <p>Filename: com.cheny.forkjoin.TaskA.java</p>
 * <p>Date: 2017-08-17 14:18.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public class TaskA extends RecursiveTask<String> {

    private String load ;

    public TaskA(String load){
        this.load = load;
    }

    @Override
    protected String compute() {
        if(StringUtils.isNotBlank(load) && load.length() > 3){
            List<TaskA> subTasks = createSubTask();
            invokeAll(subTasks);


            StringBuilder sb = new StringBuilder();
            subTasks.forEach(t -> sb.append(t.join()));
            return sb.toString();
        }else{
            return load;
        }
    }

    private List<TaskA> createSubTask(){
        int length = this.load.length();
        TaskA tk1 = new TaskA(load.substring(0,length/2));
        TaskA tk2 = new TaskA(load.substring(length/2));

        return Arrays.asList(tk1,tk2);
    }
}
