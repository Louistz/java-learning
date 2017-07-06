package com.cheny.gof.filterchain;

import com.cheny.gof.filterchain.impl.FilterChainImpl;
import com.cheny.gof.filterchain.impl.HtmlFilter;
import com.cheny.gof.filterchain.impl.SensitiveFilter;

/**
 * TODO
 * <p>Filename: com.cheny.gof.filterchain.Client.java</p>
 * <p>Date: 2017-07-06 18:39.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public class Client {
    public static void main(String[] args) {
        String msg = "测试，<script>，被就业，敏感信息";
        FilterChainImpl fc = new FilterChainImpl();
        fc.addFilter(new HtmlFilter()).addFilter(new SensitiveFilter());
        Request request = new Request();
        request.setRequestStr(msg);
        Response response = new Response();
        response.setResponseStr("response");

        fc.doFilter(request, response);
        System.out.println("message: " + msg);

        System.out.println("request: " + request.getRequestStr());
        System.out.println("response: " + response.getResponseStr());
    }
}
