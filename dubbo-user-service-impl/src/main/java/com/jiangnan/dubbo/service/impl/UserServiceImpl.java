package com.jiangnan.dubbo.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.jiangnan.dubbo.bean.User;
import com.jiangnan.dubbo.service.UserService;

/**
 * @author 吴叶俊
 */
public class UserServiceImpl implements UserService {

    private static Map<Integer, User> USER_MAP = new HashMap<Integer, User>();

    private Random random = new Random();

    @Override
    public Collection<User> getAll() {
        System.out.println("thread name = " + Thread.currentThread().getName());
        return USER_MAP.values();
    }

    @Override
    public User getById(Integer id) {
        System.out.println("get by id = " + id);
        if (!USER_MAP.containsKey(id)) {
            throw new IllegalArgumentException("argument id invalid");
        }
        User user = USER_MAP.get(id);
        System.out.println("return = " + user);
        return user;
    }

    @Override
    public boolean deleteById(Integer id) {
        return USER_MAP.remove(id) == null;
    }

    @Override
    public boolean update(User user) {
        return USER_MAP.put(user.getId(), user) == null;
    }

    @Override
    public Integer getRandom() {
        return random.nextInt(1000);
    }

    private void init() {
        System.out.println("init...");
        for (int i = 0; i < 10; i++) {
            //USER_MAP.put(random.nextInt(), new User(i, "name" + random.nextInt(), 20 + i, new Date()));
            USER_MAP.put(i, new User(i, "name" + random.nextInt(), 20 + i, new Date()));
        }
    }
}
