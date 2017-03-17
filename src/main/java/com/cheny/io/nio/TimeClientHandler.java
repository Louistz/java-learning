package com.cheny.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class TimeClientHandler implements Runnable {

    private String host;
    private int port;

    private Selector selector;
    private SocketChannel socketChannel;

    private volatile boolean stop;


    public TimeClientHandler(String host,int port){
        this.host = host == null ? "localhost" : host;
        this.port = port;

        try{
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);

            socketChannel.connect(new InetSocketAddress(host,port));
            socketChannel.register(selector,SelectionKey.OP_CONNECT);
        }catch (Exception e){
            e.printStackTrace();
        }

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

                    if (key.isConnectable()) {
                        connect(key);
                    }
                    else if(key.isReadable()){
                        read(key);
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
                close();
            }
        }
        close();

    }

    private void close(){
        if(socketChannel != null){
            try{
                socketChannel.close();
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

    private void connect(SelectionKey key) throws  IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        if (sc.finishConnect()) {
            write(sc);
            sc.register(selector, SelectionKey.OP_READ);
        }
    }

    private void read(SelectionKey key) throws IOException{

        SocketChannel sc = (SocketChannel) key.channel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int readBytes = -1;
        readBytes = sc.read(byteBuffer);
        if(readBytes != -1){
            byteBuffer.flip();

            byte[] bytes = new byte[byteBuffer.remaining()];
            byteBuffer.get(bytes);

            String body = new String(bytes,"UTF-8");
            System.out.println("Now is : " + body);
        }
        key.cancel();
        sc.close();
        this.stop = true;
    }

    private void write(SocketChannel sc) throws IOException {
        byte[] rq = "QUERY TIME ORDER".getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(rq.length);

        byteBuffer.put(rq);
        byteBuffer.flip();
        sc.write(byteBuffer);
        if(!byteBuffer.hasRemaining()){
            System.out.println("Send order to server succeed.");
        }
    }

}
