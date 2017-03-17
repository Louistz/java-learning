package com.cheny.io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class AioWriteHandler implements CompletionHandler<Integer,ByteBuffer> {

    private AsynchronousSocketChannel socket;

    public AioWriteHandler(AsynchronousSocketChannel socket){
        this.socket = socket;
    }

    @Override
    public void completed(Integer result, ByteBuffer buf) {
        if(result > 0){
            buf.flip();
            System.out.println("Server send :" + String.valueOf(buf.get()));
            buf.compact();
        }else if(result == -1){
            buf = null;
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        exc.printStackTrace();
    }
}
