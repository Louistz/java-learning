package com.cheny.gof.filterchain;

/**
 * TODO
 * <p>Filename: com.cheny.gof.filterchain.FilterChain.java</p>
 * <p>Date: 2017-07-06 18:31.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public interface FilterChain {

    void doFilter(Request request , Response response);
}
