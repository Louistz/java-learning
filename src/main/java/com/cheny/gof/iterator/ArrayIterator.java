package com.cheny.gof.iterator;

import java.lang.reflect.Array;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class ArrayIterator implements Iterator {

    private Object array;
    private int currentIndex=0;

    public ArrayIterator(Object array){
        if(!array.getClass().isArray()){
            throw new IllegalArgumentException("not an array");
        }else{
            this.array = array;
        }

    }

    @Override
    public boolean hasNext() {
        return currentIndex >= Array.getLength(array);
    }

    @Override
    public Object next() {
        return Array.get(array,currentIndex);
    }

    @Override
    public void remove() {

    }
}
