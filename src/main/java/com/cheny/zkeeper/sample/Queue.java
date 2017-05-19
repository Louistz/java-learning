package com.cheny.zkeeper.sample;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * TODO
 * <p>Filename: com.cheny.zkeeper.sample.Queue.java</p>
 * <p>Date: 2017-05-02 15:41.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public class Queue extends SyncPrimitive {

    public Queue(String address,String name) throws KeeperException, IOException , InterruptedException {
        super(address);
        this.root = name;

        if(zk != null){
            Stat s = zk.exists(root,false);
            if(s == null){
                zk.create(root, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        }
    }

    public boolean push(int i) throws KeeperException , InterruptedException {
        ByteBuffer b = ByteBuffer.allocate(4);
        b.putInt(i);
        byte[] value = b.array();

        zk.create(root+"/element", value , ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        return true;
    }

    public int pop() throws KeeperException, InterruptedException {
        int retvalue = -1;
        Stat stat = null;
        while (true){
            synchronized (mutex){
                List<String> elements = zk.getChildren(root,true);
                if(elements.isEmpty()){
                    System.out.println("waiting for push ...");
                    mutex.wait();
                }else{
                    Integer min = new Integer(elements.get(0).substring(7));
                    for(String element : elements){
                        Integer tmp = new Integer(element.substring(7));
                        if(tmp < min){
                            min = tmp;
                        }
                    }

                    System.out.println("Temporary value : " + root + "/element" + min);
                    byte[] b = zk.getData(root+"/element" + min , false, stat);
                    zk.delete(root + "/element" + min , 0);
                    ByteBuffer buffer = ByteBuffer.wrap(b);
                    retvalue = buffer.getInt();

                    return retvalue;
                }
            }
        }
    }
}
