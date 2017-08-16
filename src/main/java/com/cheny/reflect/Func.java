package com.cheny.reflect;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * TODO
 * <p>Filename: com.cheny.reflect.Func.java</p>
 * <p>Date: 2017-07-24 15:46.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public class Func {

    public static class String2BigDemical implements Function<String,BigDecimal> {
        @Override
        public BigDecimal apply(String s) {
            return new BigDecimal(s);
        }
    }
}
