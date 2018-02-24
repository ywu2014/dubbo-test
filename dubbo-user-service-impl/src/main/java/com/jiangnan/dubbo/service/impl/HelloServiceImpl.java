package com.jiangnan.dubbo.service.impl;

import com.jiangnan.dubbo.service.HelloService;

/**
 * @author 吴叶俊
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        String result = "hello " + name;
        System.out.println(result);
        return result;
    }
}
