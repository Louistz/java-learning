package com.cheny.concurrency.blockingqueue;

import java.util.Comparator;
import java.util.concurrent.*;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class TestBlockingQueue {

    public static void main(String[] args){

        //testArrayBlockingQueue();
        testDelayQueue();

    }

    public static void testArrayBlockingQueue() {
        final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);
        final BlockingQueue<String> q = new LinkedBlockingQueue<String>();

        for (int i = 0; i <4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String log = (String) queue.take();
                        parseLog(log);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
        for(int i=0;i<16;i++){
            String log = (i+1) +"->"+System.currentTimeMillis();
            try{
                queue.put(log);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
        System.out.println(queue);
    }

    public static void parseLog(String log){
        System.out.println(System.currentTimeMillis()+":"+log);
        try{
            Thread.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void testPriorityBlockingQueue() {
        final BlockingQueue<String> q =
                new PriorityBlockingQueue<String>(10, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.hashCode() - o2.hashCode();
                    }
                });
    }

    public static void testDelayQueue() {
        class Student implements Delayed {

            private String name;
            private long startTime;
            private long endTime;

            public Student(String name, long delayTime) {
                this.name = name;
                this.startTime = System.nanoTime();
                this.endTime = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.NANOSECONDS) + System.nanoTime();
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getStartTime() {
                return startTime;
            }

            public void setStartTime(long startTime) {
                this.startTime = startTime;
            }

            public long getEndTime() {
                return endTime;
            }

            public void setEndTime(long endTime) {
                this.endTime = endTime;
            }

            @Override
            public long getDelay(TimeUnit unit) {
                return unit.convert(endTime - System.nanoTime(), TimeUnit.NANOSECONDS);
            }

            @Override
            public int compareTo(Delayed o) {
                Student that = (Student) o;
                return this.getEndTime() > that.getEndTime() ? 1 : (this.getEndTime() < that
                        .getEndTime() ? -1 : 0);
            }
        }
        final BlockingQueue<Student> q = new DelayQueue<Student>();

        for(int i=0;i<10;i++){
            Student s = new Student("学生"+i,Math.round(Math.random()*10+i));
            try{
                q.put(s);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        try {
            Student s = null;
            while ((s=q.poll()) != null){
                System.out.println(s.getName()+",用时"+(s.getEndTime()-s.getStartTime()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
