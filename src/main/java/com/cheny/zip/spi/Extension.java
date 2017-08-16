package com.cheny.zip.spi;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>Filename: com.qianmi.pc.admin.task.common.zip.spi.extension.java</p>
 * <p>Date: 2017-08-16 15:13.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */

@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface Extension {
    String value() default "";
}
