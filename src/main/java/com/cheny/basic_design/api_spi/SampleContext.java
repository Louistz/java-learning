package com.cheny.basic_design.api_spi;

/**
 * <p>API 和 SPI 分离</p>
 *
 * 这里:action是api,render是SPI
 * 两者都可以随时替换和装配
 *
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class SampleContext {

    private Render render;
    private Action action;

    public Object doSomething(){
        return render.render(action.execute());
    }
}
