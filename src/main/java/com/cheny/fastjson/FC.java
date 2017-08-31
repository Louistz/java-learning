package com.cheny.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * TODO
 * <p>Filename: com.cheny.fastjson.FC.java</p>
 * <p>Date: 2017-08-19 12:58.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
@Getter
@Setter
public class FC {

    @JSONField(name = "fbs")
    private List<FB> fbs;

    @JSONField(name = "nc")
    private String nc = "helloworld";
}
