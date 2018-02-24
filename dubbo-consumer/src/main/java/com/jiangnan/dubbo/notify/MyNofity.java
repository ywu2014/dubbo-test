package com.jiangnan.dubbo.notify;

import com.jiangnan.dubbo.bean.User;

/**
 * @author 吴叶俊
 */
public class MyNofity {
    public void onInvoke(User user, Integer id) {
        System.out.println("start to invoke getUser, id = " + id);
    }
    public void onReturn(User user) {
        System.out.println("on return user = " + user);
    }

    public void onThrow(Throwable throwable) {
        System.out.println("exception:" + throwable.getMessage());
    }
}
