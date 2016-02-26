package com.cheny.zkeeper;

import org.apache.zookeeper.*;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class TestZookeeper {

    public static int TIME_OUT = 60000;


    public static void main(String[] args){
        ZooKeeper zk = null;
        try{
            zk = new ZooKeeper(ZookeeperServer.SERVER1.toServerString(), TIME_OUT, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("触发了["+watchedEvent.getType()+"]事件");
                }
            });

            zk.create("/test","test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            zk.create("/test/child","testChild".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);

            System.out.println(zk.exists("/test",true));
            System.out.println(zk.getChildren("/",true));

            zk.setData("/test/child","hello,this is a child node".getBytes(),-1);

            System.out.println(new String(zk.getData("/test/child",true,null)));

            zk.delete("/test/child",-1);
            zk.delete("/test",-1);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != zk){
                try{
                    zk.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }
}
