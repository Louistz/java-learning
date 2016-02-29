package com.cheny.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class ChannelSample {

    public static void main(String[] args) {
        testChannel();
    }


    public static void testChannel(){
        RandomAccessFile accessFile = null;
        try {
            accessFile = new  RandomAccessFile("src/main/resources/words.txt","rw");
            FileChannel fileChannel = accessFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = fileChannel.read(buf);
            while (bytesRead != -1){
                System.out.println("Read " + bytesRead);
                buf.flip();
                while (buf.hasRemaining()){
                    System.out.print((char) buf.get());
                }
                System.out.println();
                buf.clear();
                bytesRead = fileChannel.read(buf);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(null != accessFile){
                try{
                    accessFile.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
