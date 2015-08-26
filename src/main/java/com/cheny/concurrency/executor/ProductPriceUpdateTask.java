package com.cheny.concurrency.executor;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class ProductPriceUpdateTask extends RecursiveAction{

    private final int throHold = 10;
    private List<Product> products;
    private int first;
    private int last;

    public ProductPriceUpdateTask(List<Product> products,int first,int last){
        this.products = products;
        this.first = first;
        this.last = last;
    }


    @Override
    protected void compute() {
        if(last - first < throHold){
            updatePrice();
        }else{
            int middle = (first+last)/2;
            ProductPriceUpdateTask t1 = new ProductPriceUpdateTask(products,first,middle+1);
            ProductPriceUpdateTask t2 = new ProductPriceUpdateTask(products,middle+1,last);
            invokeAll(t1,t2);
        }
    }

    private void updatePrice(){
        for(int i = first;i<last;i++){
            products.get(i).setPrice(products.get(i).getPrice()+2);
        }
    }
}
