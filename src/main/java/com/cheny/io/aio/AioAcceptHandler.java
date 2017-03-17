package com.cheny.io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class AioAcceptHandler implements CompletionHandler<AsynchronousSocketChannel,AsynchronousServerSocketChannel> {

    @Override
    public void completed(AsynchronousSocketChannel socket, AsynchronousServerSocketChannel server) {
        try{
            //继续接受下一个连接
            server.accept(server,this);

            read(socket);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {
        exc.printStackTrace();
    }

    private void read(AsynchronousSocketChannel socket){
        ByteBuffer buf = ByteBuffer.allocate(1024);
        socket.read(buf,buf,new AioReadHandler(socket));
    }

}
