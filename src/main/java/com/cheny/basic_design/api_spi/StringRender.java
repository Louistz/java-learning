package com.cheny.basic_design.api_spi;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class StringRender implements Render<String> {

    @Override
    public String render(ActionResult result) {
        return result.toString();
    }
}
