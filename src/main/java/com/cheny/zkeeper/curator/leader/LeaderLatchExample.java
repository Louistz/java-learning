package com.cheny.zkeeper.curator.leader;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.curator.utils.CloseableUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * TODO
 * <p>Filename: com.cheny.zkeeper.curator.leader.LeaderLatchExample.java</p>
 * <p>Date: 2017-05-03 19:42.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public class LeaderLatchExample {

    private static final int CLIENT_QTY = 10;
    private static final String PATH = "/examples/leader";

    public static void main(String[] args) throws Exception {


        List<CuratorFramework> clients = Lists.newArrayList();
        List<LeaderLatch> examples = Lists.newArrayList();

        TestingServer server = new TestingServer();

        try{
            for(int i= 0;i<CLIENT_QTY;i++){
                CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), new ExponentialBackoffRetry(1000,3));
                clients.add(client);

                LeaderLatch example = new LeaderLatch(client,PATH,"Client#"+i);
                examples.add(example);

                client.start();
                example.start();
            }

            Thread.sleep(20000);

            LeaderLatch leader = null;
            for(int i=0;i<CLIENT_QTY;i++){
                LeaderLatch example = examples.get(i);
                if(example.hasLeadership()){
                    leader = example;
                }
            }

            System.out.println("current leader is " + leader.getId());
            System.out.println("release the leader " + leader.getId());
            leader.close();

            System.out.println("Client #0 maybe is elected as the leader or not although it want to be");
            System.out.println("the new leader is " + examples.get(0).getLeader().getId());

            System.out.println("Press enter/return to quit\n");
            new BufferedReader(new InputStreamReader(System.in)).readLine();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("Shutting down ...");
            for(LeaderLatch example : examples){
                CloseableUtils.closeQuietly(example);
            }
            for(CuratorFramework client : clients){
                CloseableUtils.closeQuietly(client);
            }
            CloseableUtils.closeQuietly(server);
        }

    }
}
