package com.cheny.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class TimeServer {

    public static void main(String[] args) {

        int port = 8080;
        if(args != null && args.length > 0){
            try{
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){

            }
        }

        ServerSocket server = null;
        try{
            server = new ServerSocket(port);
            System.out.println("The time server is start in port : " + port);
            Socket socket = null;
            while (true){
                socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(server != null){
                try{
                    server.close();
                }catch (IOException e){
                }
            }
        }
    }




}
