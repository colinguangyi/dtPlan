package com.colin.server.controller;

import com.colin.server.util.JedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaolz
 */
@RestController
public class DemoController {

    @RequestMapping("/demo")
    public Object demo(){
        return JedisUtil.setNxEx("test_ex_nx", "1", 60);
    }

}
