package com.cheny.concurrency.thread;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.AutoCloseable;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class TestThread {

    public static void main(String[] args) throws Exception{
        testJoin();
    }

    public static void testJoin() throws Exception{
        Thread a = new Thread(new Calculator(1));
        Thread b = new Thread(new Calculator(2));
        b.start();
        a.start();


        a.join();
        b.join();
    }

    private static void writeThreadInfo(PrintWriter pw ,Thread thread,Thread.State state){
        pw.printf("Main : Id %d - %s\n",thread.getId(),thread.getName());
        pw.printf("Main : Priority: %d\n",thread.getPriority());
        pw.printf("Main : Old State: %s\n",state);
        pw.printf("Main : New State: %s\n",thread.getState());
        pw.printf("Main : ************************************\n");

    }

    public static void test(){
        Thread[] threads = new Thread[10];
        Thread.State[] states = new Thread.State[10];

        for(int i=0;i<10;i++){
            threads[i] = new Thread(new Calculator(i));
            if(i % 2 == 0){
                threads[i].setPriority(Thread.MAX_PRIORITY);
            }else{
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("Thread"+i);
        }

//        try(FileWriter log = new FileWriter("log.txt");
//            PrintWriter pw = new PrintWriter(log);){
//
//            for (int i=0; i<10; i++){
//                pw.println("Main : Status of Thread "+i+" : " +threads[i].getState());
//                states[i]=threads[i].getState();
//            }
//
//            for(int i=0;i<10;i++){
//                threads[i].start();
//            }
//
//            boolean finish = false;
//            while(!finish){
//                for(int i=0;i<10;i++){
//                    if(threads[i].getState() != states[i]){
//                        writeThreadInfo(pw,threads[i],threads[i].getState());
//                        states[i] = threads[i].getState();
//                    }
//                }
//                finish = true;
//                for (int i=0; i<10; i++){
//                    finish=finish && (threads[i].getState()==Thread.State.TERMINATED);
//                }
//            }
//
//            if(finish){
//                System.out.println("Done");
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
