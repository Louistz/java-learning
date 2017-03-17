package com.cheny.io.nio;

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
        for(int i=0;i<1000;i++){
            new Thread(new TimeClientHandler("localhost",port),"timeClientHandler"+i).start();
        }

    }
}
