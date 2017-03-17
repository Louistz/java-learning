package com.cheny.basic_design.api_spi;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class IntegerRender implements Render<Integer> {

    @Override
    public Integer render(ActionResult result) {
        return null == result ? 0 : 1;
    }
}
