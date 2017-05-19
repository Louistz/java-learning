package com.cheny.zkeeper.sample;


import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

/**
 * TODO
 * <p>Filename: com.cheny.zkeeper.sample.Barrier.java</p>
 * <p>Date: 2017-04-28 17:02.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public class Barrier extends SyncPrimitive {

    private String address;
    private String name;
    private int size;

    public Barrier(String address, String name , int size) throws KeeperException,InterruptedException ,IOException {
        super(address);
        this.root = name;
        this.size = size;

        if(zk != null){
            Stat s = zk.exists(root,false);
            if(s == null){
                zk.create(root,new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        }
        this.name = new String(InetAddress.getLocalHost().getCanonicalHostName().toString());
    }

    public boolean enter() throws KeeperException, InterruptedException{
        zk.create(root + "/" + name, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        while (true){
            synchronized (mutex){
                List<String> list = zk.getChildren(root,true);
                if(list.size() < size){
                    mutex.wait();
                } else {
                   return true;
                }
            }
        }
    }

    public boolean leave() throws KeeperException, InterruptedException{
        zk.delete(root + "/" + name,0);
        while (true){
            synchronized (mutex){
                List<String> list = zk.getChildren(root,true);
                if(list.size() > 0){
                    mutex.wait();
                }else{
                    return true;
                }
            }
        }
    }
}
