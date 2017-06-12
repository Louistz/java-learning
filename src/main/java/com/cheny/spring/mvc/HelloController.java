package com.cheny.spring.mvc;

import com.cheny.ddd.domain.model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;
import java.util.HashMap;

/**
 * TODO
 * <p>Filename: com.cheny.spring.mvc.HelloController.java</p>
 * <p>Date: 2017-06-12 15:53.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
@RestController
public class HelloController {

    //返回字符串
    @RequestMapping("/")
    public String helloWorld(){
        return "Hello World";
    }

    //返回模板(jsp等等)
    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        //模板附带的属性，可以直接取出来
        mv.addObject("attributeName","attributeValue");

        //index.jsp, index.html等等
        mv.setViewName("index");

        return mv;
    }

    //动态url, 返回json .通常用于ajax
    @RequestMapping(value = "/index/{userId}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getUser(@PathVariable String userId){
        User user = new User();
        user.setId(userId);
        user.setName("username");

        Map<String,Object> result = new HashMap<String,Object>();
        result.put("user",user);
        return result;
    }
}
