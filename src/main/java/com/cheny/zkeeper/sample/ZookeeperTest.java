package com.cheny.zkeeper.sample;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class ZookeeperTest {

    public static final String HOST = "localhost";
    public static final int PORT = 2181;
    public static final int TIME_OUT = 1000;

    public static final Logger logger = LoggerFactory.getLogger(ZookeeperTest.class);

    private ZooKeeper zookeeper ;

    private Watcher watcher = new Watcher() {
        @Override
        public void process(WatchedEvent event) {
            logger.info("process : " + event.getType());
        }
    };


    @Before
    public void connect() throws IOException{
        zookeeper = new ZooKeeper(HOST+":"+PORT,TIME_OUT,watcher);
        if(null == zookeeper){
            logger.info("zookeeper connect error ....");
        }
    }
    @After
    public void close() {
        try{
            if(null != zookeeper){
                zookeeper.close();
            }
        }catch (Exception e){

        }
    }

    @Test
    public void testCreate(){
        String result = null;
        try{
            result = zookeeper.create("/zk001","zk001_data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        logger.info("create result : " + result);
    }


    @Test
    public void testCreateChildren(){
        String result = null;
        try{
            result = zookeeper.create("/zk001/zk001001","zk001001_data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        logger.info("create result : " + result);
    }

    @Test
    public void testGetChildren(){
        List<String> result = null;
        try{
            result = zookeeper.getChildren("/zk001",null);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        logger.info("create result : " + result);
    }

    @Test
    public void testGetData(){
        String result = null;
        try{
            byte[] bytes = zookeeper.getData("/zk001",null,null);
            result = new String(bytes);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        logger.info("get data : [/zk001]=" + result);
    }

    @Test
    public void testDelete(){
        try{
            zookeeper.delete("/zk001", -1);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
