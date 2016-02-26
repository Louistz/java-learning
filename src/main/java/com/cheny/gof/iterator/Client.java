package com.cheny.gof.iterator;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Client {

    public static void main(String[] args) {
        MyList<String> myList = new MyList();
        myList.add("Hello");
        myList.add("World");
        myList.add("oye");

        Iterator iterator = myList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        myList.remove();
        Iterator iterator1 = myList.iterator();
        while (iterator1.hasNext()){
            System.out.println(iterator1.next());
        }

    }
}
