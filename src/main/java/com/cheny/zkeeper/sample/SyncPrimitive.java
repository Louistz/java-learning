package com.cheny.zkeeper.sample;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * TODO
 * <p>Filename: com.cheny.zkeeper.sample.SyncPrimitive.java</p>
 * <p>Date: 2017-04-28 17:03.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public class SyncPrimitive implements Watcher{

    static ZooKeeper zk = null;
    static final Object mutex = new Object();
    String root;

    SyncPrimitive(String address) throws KeeperException,IOException{
        if(zk == null){
            System.out.println("Starting zk....");
            zk = new ZooKeeper(address,3000,this);
            System.out.println("Finished start zk : " + zk);
        }
    }

    @Override
    public void process(WatchedEvent event) {
        synchronized (mutex){
            mutex.notify();
        }
    }

    public void destroy() throws KeeperException, InterruptedException {
        if(zk != null){
            zk.delete(root,0);
            zk.close();
        }
    }
}
