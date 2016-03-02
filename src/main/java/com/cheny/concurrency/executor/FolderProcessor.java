package com.cheny.concurrency.executor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class FolderProcessor extends RecursiveTask<List<String>> {

    private String path;
    private String extension;

    public FolderProcessor(String path,String extension){
        this.path = path;
        this.extension = extension;
    }

    @Override
    protected List<String> compute() {
        List<String> list = new ArrayList<String>();
        List<FolderProcessor> tasks = new ArrayList<FolderProcessor>();
        File file = new File(path);
        File content[] = file.listFiles();

        if(null != content){
            for(int i=0;i<content.length;i++){
                if(content[i].isDirectory()){
                    FolderProcessor task = new FolderProcessor(content[i].getAbsolutePath(),extension);
                    task.fork();
                    tasks.add(task);
                }else {
                    if(content[i].getName().endsWith(extension)){
                        list.add(content[i].getAbsolutePath());
                    }
                }
            }
            if (tasks.size()>50) {
                System.out.printf("%s: %d tasks ran.\n",file.getAbsolutePath(),tasks.size());
            }

        }
        addResultFromTasks(list,tasks);
        return list;
    }

    private List<String> addResultFromTasks(List<String> list,List<FolderProcessor> tasks){
        for(FolderProcessor task : tasks){
            list.addAll(task.join());
        }
        return list;
    }
}
