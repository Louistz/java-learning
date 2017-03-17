package com.cheny.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class TimeServerHandler implements Runnable {

    private Socket socket;

    public TimeServerHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(),true);

            String currentTime = null;
            String body = null;
            while (true){
                body = in.readLine();
                if(body == null){
                    break;
                }
                System.out.println("The time server receive order :" + body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
                        ? new Date(System.currentTimeMillis()).toString()
                        : "BAD ORDER";
                out.println(currentTime);
            }
        } catch (IOException e){

        } finally {
            try{
                if(in != null){
                    in.close();
                }
                if(out != null){
                    out.println();
                }
                if(socket != null){
                    socket.close();
                }
            }catch (IOException ex){
            }
        }
    }
}
