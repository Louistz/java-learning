package com.cheny.gof.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class MyList<T> implements Iterable,Iterator{

    private List<T> myList;
    private int currentIndex=0;


    public MyList(){
        this.myList = new ArrayList<T>();
    }

    public void add(T t){
        myList.add(t);
    }

    @Override
    public Iterator iterator() {
        return new ArrayIterator(myList.toArray());
    }

    @Override
    public boolean hasNext() {
        if(currentIndex == myList.size()){
            return false;
        }
        return true;
    }

    @Override
    public Object next() {
        return myList.get(currentIndex++);
    }

    @Override
    public void remove() {
        myList.remove(myList.size()-1);
    }
}
