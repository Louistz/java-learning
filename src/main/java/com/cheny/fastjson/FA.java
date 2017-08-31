package com.cheny.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO
 * <p>Filename: com.cheny.fastjson.User.java</p>
 * <p>Date: 2017-08-19 12:57.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
@Getter
@Setter
public class FA {

    @JSONField(name = "name")
    private String name = "name";

    @JSONField(name = "value")
    private String value = "value";
}
