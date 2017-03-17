package com.cheny.io.aio;

import org.apache.commons.lang3.StringUtils;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class AioReadHandler implements CompletionHandler<Integer,ByteBuffer> {

    private AsynchronousSocketChannel socket;

    public AioReadHandler(AsynchronousSocketChannel socket){
        this.socket = socket;
    }

    @Override
    public void completed(Integer result, ByteBuffer buf) {
        try{
            if(result > 0){
                buf.flip();
                byte[] bytes = new byte[buf.remaining()];
                buf.get(bytes);
                String body = new String(bytes, "UTF-8");

                System.out.println("The time server receive order : " + body);
                String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(
                                System.currentTimeMillis()).toString() : "BAD ORDER";
                buf.compact();

                write(currentTime);
            }else if(result == -1){
                buf = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        exc.printStackTrace();
    }

    private void write(String message){
        if(StringUtils.isNotBlank(message)){
            byte[] bytes = message.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            socket.write(writeBuffer,writeBuffer,new AioWriteHandler(socket));
        }

    }
}
