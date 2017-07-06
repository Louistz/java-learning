package com.cheny.gof.filterchain.impl;

import com.cheny.gof.filterchain.Filter;
import com.cheny.gof.filterchain.FilterChain;
import com.cheny.gof.filterchain.Request;
import com.cheny.gof.filterchain.Response;

/**
 * TODO
 * <p>Filename: com.cheny.gof.filterchain.impl.HtmlFilter.java</p>
 * <p>Date: 2017-07-06 18:32.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public class HtmlFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.requestStr = request.requestStr.replace("<", "[").replace(">", "]") + "--HTML--";
        chain.doFilter(request, response);
        response.responseStr += "--HTML--";
    }
}
