package com.cheny.gof.filterchain.impl;

import com.cheny.gof.filterchain.Filter;
import com.cheny.gof.filterchain.FilterChain;
import com.cheny.gof.filterchain.Request;
import com.cheny.gof.filterchain.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * <p>Filename: com.cheny.gof.filterchain.impl.FilterChainImpl.java</p>
 * <p>Date: 2017-07-06 18:36.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public class FilterChainImpl implements FilterChain {

    private List<Filter> filters = new ArrayList<>();
    private int index = 0;

    public FilterChainImpl addFilter(Filter f){
        filters.add(f);
        return this;
    }

    @Override
    public void doFilter(Request request, Response response) {
        if(filters.size() == index) return;
        Filter filter = filters.get(index);
        index ++;
        filter.doFilter(request,response,this);
    }
}
