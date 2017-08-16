package com.cheny.reflect;

import java.util.function.Function;

/**
 * TODO
 * <p>Filename: com.cheny.reflect.TestFunc.java</p>
 * <p>Date: 2017-07-24 15:48.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public class TestFunc {

    public static void main(String[] args) throws Exception{
        Function f = Func.String2BigDemical.class.newInstance();
    }
}
