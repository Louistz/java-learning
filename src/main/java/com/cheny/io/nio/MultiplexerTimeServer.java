package com.cheny.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class MultiplexerTimeServer implements Runnable {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private volatile  boolean stop;
    private AtomicLong clients = new AtomicLong(0);


    public MultiplexerTimeServer(int port){

        try{
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("The time server is started in port : " + port);

        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

    }

    public void stop(){
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop){
            try{
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();

                SelectionKey key = null;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();

                    if(!key.isValid()){
                        continue;
                    }

                    if(key.isAcceptable()){
                        clients.incrementAndGet();
                        accept(key);
                    }
                    else if(key.isReadable()){
                        read(key);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if(serverSocketChannel != null){
            try{
                serverSocketChannel.close();
            }catch (IOException e){
            }
        }
        if(selector != null){
            try{
                selector.close();
            }catch (IOException e){
            }
        }
    }

    private void accept(SelectionKey key) throws IOException{
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel sc = ssc.accept();
        sc.configureBlocking(false);
        sc.register(selector, SelectionKey.OP_READ);
    }
    private void read(SelectionKey key) throws IOException{
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);

        int numRead = -1;
        numRead = sc.read(readBuffer);
        if(numRead == -1){
            sc.close();
            key.cancel();
        }else{
            readBuffer.flip();
            byte[] bytes = new byte[readBuffer.remaining()];
            readBuffer.get(bytes);
            String body = new String(bytes, "UTF-8");

            System.out.println("The time server receive order : " + body);

            String currentTime =
                    "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(
                            System.currentTimeMillis()).toString() : "BAD ORDER";

            doWrite(sc, currentTime);
            System.out.println("clients:" + clients.get());
        }
    }

    private void doWrite(SocketChannel channel,String response) throws IOException{

        if(StringUtils.isNotBlank(response)){
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }

    }
}
