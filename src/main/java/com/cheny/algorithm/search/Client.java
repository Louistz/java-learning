package com.cheny.algorithm.search;


import java.util.Iterator;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Client {

    public static void main(String[] args) {
        ST st = new BST<>();

        st.put("A", 1);
        st.put("B", 2);
        st.put("C", 3);
        st.put("D",4);

        Iterator<String> ks = st.keys();
        while (ks.hasNext()){
            String key = ks.next();
            Integer value =(Integer) st.get(key);
            System.out.println(key + "->"+value);
        }
        st.delete("B");
        Iterator<String> ks2 = st.keys();
        while (ks2.hasNext()){
            String key = ks2.next();
            Integer value =(Integer) st.get(key);
            System.out.println(key + "->"+value);
        }


    }
}
