package com.cheny.concurrency.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class TestForkJoin {

    public static void main(String[] args) throws Exception{
        //testForkJoinWithoutResult();
        //testForkJoinWithResult();
        testMultiForkJoin();
    }


    public static void testForkJoinWithoutResult(){
        ForkJoinPool pool = new ForkJoinPool();
        List<Product> products = generateProducts();
        ProductPriceUpdateTask task = new ProductPriceUpdateTask(products,0,products.size());
        pool.execute(task);
        do {
            System.out.printf("Main: Thread Count: %d\n",pool.getActiveThreadCount());
            System.out.printf("Main: Thread Steal: %d\n",pool.getStealCount());
            System.out.printf("Main: Parallelism: %d\n",pool.getParallelism());
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());
        pool.shutdown();
        if(task.isCompletedNormally()){
            System.out.println("task complete normally!");
        }
        for(int i=0;i<products.size();i++){
            if(products.get(i).getPrice() != 12){
                System.out.println(products.get(i).getName()+".price:"+products.get(i).getPrice());
            }
        }

    }

    private static List<Product> generateProducts (){
        List<Product> products = new ArrayList<>();
        for(int i = 0 ;i<1000000;i++){
            products.add(new Product("Product"+i,10));
        }
        return products;
    }

    public static void testForkJoinWithResult() throws Exception{
        ForkJoinPool pool = new ForkJoinPool();
        FibTask task = new FibTask(35);
        pool.execute(task);
        do {
            System.out.printf("Main: Thread Count: %d\n",pool.getActiveThreadCount());
            System.out.printf("Main: Thread Steal: %d\n",pool.getStealCount());
            System.out.printf("Main: Parallelism: %d\n",pool.getParallelism());
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());

        pool.shutdown();
        if(task.isCompletedNormally()){
            System.out.println("task complete normally!");
        }

        System.out.println(task.get());
    }

    public static void testMultiForkJoin(){
        ForkJoinPool pool = new ForkJoinPool();
        FolderProcessor app = new FolderProcessor("/Applications","app");
        pool.execute(app);
        do {
            System.out.printf("Main: Thread Count: %d\n",pool.getActiveThreadCount());
            System.out.printf("Main: Thread Steal: %d\n",pool.getStealCount());
            System.out.printf("Main: Parallelism: %d\n",pool.getParallelism());
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!app.isDone());

        pool.shutdown();
        if(app.isCompletedNormally()){
            try{
                List<String> appStrings = app.get();
                if(null != appStrings && appStrings.size()>0){
                    for(String s : appStrings){
                        System.out.println(s);
                    }
                }
                System.out.println(app.get());
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }


}
