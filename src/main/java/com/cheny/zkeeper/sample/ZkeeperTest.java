package com.cheny.zkeeper.sample;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class ZkeeperTest {

    public static final String HOST = "localhost";
    public static final int PORT = 2081;
    public static final int TIME_OUT = 1000;

    public static void main(String[] args){

        try{
            ZooKeeper zk = new ZooKeeper(HOST + ":" + PORT, TIME_OUT, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }




    }
}
