package com.jiangnan.dubbo.service;

import java.util.Collection;

import com.jiangnan.dubbo.bean.User;

/**
 * @author 吴叶俊
 */
public interface UserService {
    Collection<User> getAll();

    User getById(Integer id);

    boolean deleteById(Integer id);

    boolean update(User user);

    //@interface Update {}

    Integer getRandom();
}
