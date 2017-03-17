package com.cheny.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class TimeClient {

    public static void main(String[] args) {
        int port = 8080;
        if(args != null && args.length > 0){
            try{
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
            }
        }

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            socket = new Socket("localhost",port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.print("QUERY TIME ORDER");
            System.out.println("Send order to server succeed.");

            String resp = in.readLine();
            System.out.println("Now is : " + resp);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
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
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
