package com.cheny.fastjson;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;

/**
 * TODO
 * <p>Filename: com.cheny.fastjson.Test.java</p>
 * <p>Date: 2017-08-19 12:59.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public class Test {

    public static void main(String[] args) {
        FA fa = new FA();fa.setName("saaadfsdfs");

        FB fb = new FB(); fb.setFas(Arrays.asList(fa));

        FC fc = new FC(); fc.setFbs(Arrays.asList(fb));

        String s = JSONObject.toJSONString(fc);
        FC fcn = JSONObject.parseObject(s,FC.class);

        System.out.println(fcn);


    }
}
