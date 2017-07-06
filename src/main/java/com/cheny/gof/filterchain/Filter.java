package com.cheny.gof.filterchain;

/**
 * TODO
 * <p>Filename: com.cheny.gof.filterchain.Filter.java</p>
 * <p>Date: 2017-07-06 18:30.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public interface Filter {

    void doFilter(Request request, Response response, FilterChain chain);
}
