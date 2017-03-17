package com.cheny.io.aio;

import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Executors;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class AioClient {
    public static void main(String[] args) {
        try{
            AioClient client = new AioClient();
            Thread.sleep(300);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private AsynchronousChannelGroup group;

    public AioClient() throws Exception{
        group= AsynchronousChannelGroup.withThreadPool(Executors.newFixedThreadPool(5));
        AsynchronousSocketChannel socket = AsynchronousSocketChannel.open(group);
        socket.setOption(StandardSocketOptions.TCP_NODELAY,true);
        socket.setOption(StandardSocketOptions.SO_REUSEADDR,true);
        socket.setOption(StandardSocketOptions.SO_KEEPALIVE,true);

        socket.connect(new InetSocketAddress("localhost", 8080), socket, new CompletionHandler<Void, AsynchronousSocketChannel>() {
            @Override
            public void completed(Void result, AsynchronousSocketChannel socket) {

                byte[] rq = "QUERY TIME ORDER".getBytes();
                ByteBuffer byteBuffer = ByteBuffer.allocate(rq.length);
                byteBuffer.put(rq);
                byteBuffer.flip();

                socket.write(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer attachment) {
                        if(!attachment.hasRemaining()){
                            System.out.println("Send order to server succeed.");
                        }
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        socket.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                            @Override
                            public void completed(Integer result, ByteBuffer buf) {
                                if(result > 0){
                                    buf.flip();
                                    byte[] bytes = new byte[buf.remaining()];
                                    buf.get(bytes);
                                    try {
                                        String body = new String(bytes,"UTF-8");
                                        System.out.println("Now is : " + body);

                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }else {
                                    buf = null;
                                }
                                try{
                                    socket.close();
                                    group.shutdown();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void failed(Throwable exc, ByteBuffer attachment) {
                                exc.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {

                    }
                });
            }

            @Override
            public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
                exc.printStackTrace();
            }
        });
    }
}
