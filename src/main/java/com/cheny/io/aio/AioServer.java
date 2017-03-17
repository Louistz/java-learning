package com.cheny.io.aio;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.Executors;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class AioServer implements Runnable {

    private AsynchronousServerSocketChannel server;

    public AioServer(String host,int port) throws Exception{
        String h = null == host || host.length() == 0 ? "localhost" : host;

        AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(Executors.newFixedThreadPool(3));
        server = AsynchronousServerSocketChannel.open(group)
                .bind(new InetSocketAddress(h,port));
    }

    @Override
    public void run() {
        try{
            server.accept(server,new AioAcceptHandler());
            Thread.sleep(10000000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try{
            new Thread(new AioServer("localhost",8080)).start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
