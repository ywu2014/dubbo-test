package com.jiangnan.dubbo.service.impl;

import com.jiangnan.dubbo.service.HelloService;

/**
 * @author 吴叶俊
 */
public class HelloServiceImpl2 implements HelloService {
    @Override
    public String sayHello(String name) {
        String result = "hehe " + name;
        return result;
    }
}
