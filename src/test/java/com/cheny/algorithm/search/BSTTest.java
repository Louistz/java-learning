package com.cheny.algorithm.search;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class BSTTest {

    private ST<String,Integer> st ;

    @Before
    public void init(){
        st = new BST<>();
    }

    @Test
    public void test(){
        Assert.assertEquals(0, st.size());
        st.put("A", 1);

        Assert.assertEquals(1, st.size());

        Assert.assertSame(st.get("A"), 1);

        st.delete("A");
        Assert.assertEquals(0, st.size());
        Assert.assertNull(st.get("A"));

        st.delete("A");
        Assert.assertEquals(0, st.size());
        Assert.assertNull(st.get("A"));


        st.put("A", 1);
        st.put("B", 2);
        st.put("B", 3);
        st.put("C", 3);
        java.util.Iterator<String> iterator = st.keys();
        if(iterator.hasNext()){
            String k = iterator.next();
            Assert.assertEquals("A", k);
            Assert.assertSame(1,st.get(k));
        }
        if(iterator.hasNext()){
            String k = iterator.next();
            Assert.assertEquals("B", k);
            Assert.assertSame(3, st.get(k));
        }
        if(iterator.hasNext()){
            String k = iterator.next();
            Assert.assertEquals("C", k);
            Assert.assertSame(3,st.get(k));
        }
        Assert.assertSame(false, iterator.hasNext());
    }

}
