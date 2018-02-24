package com.jiangnan.dubbo;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.jiangnan.dubbo.bean.User;
import com.jiangnan.dubbo.service.HelloService;
import com.jiangnan.dubbo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author 吴叶俊
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-consumer.xml"})
public class ConsumerTest {

    @Autowired
    UserService userService;
    @Autowired
    //@Qualifier("helloService")
    HelloService helloService;

    /*@Autowired
    @Qualifier("helloService2")
    HelloService helloService2;*/

    @Autowired
    ApplicationContext context;

    @Test
    public void testGetAll() {
        System.out.println("start test...");
        for (int i = 0; i < 20; i++) {
            Collection<User> users = userService.getAll();
            System.out.println(users);
            /*try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            System.out.println(helloService.sayHello("zhangsan" + i));
        }
    }

    @Test
    public void testGet() {
        User user = userService.getById(2);
        System.out.println(user);
    }

    /**
     * 直连
     */
    @Test
    public void testDirect() {
        System.out.println("start test...");
        for (int i = 0; i < 10; i++) {
            Collection<User> users = userService.getAll();
            System.out.println(users);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 分组测试
     */
    /*@Test
    public void testHello() {
        for (int i = 0; i < 20; i++) {
            System.out.println(helloService.sayHello("zhangsan" + i));
            System.out.println(helloService2.sayHello("lisi" + i));
        }
    }*/

    /**
     * 多版本测试
     */
    @Test
    public void testHello1() {
        for (int i = 0; i < 20; i++) {
            System.out.println(helloService.sayHello("zhangsan" + i));
        }
    }

    /**
     * 分组合并
     */
    @Test
    public void testHello2() {
        for (int i = 0; i < 20; i++) {
            System.out.println(helloService.sayHello("zhangsan" + i));
        }
    }

    /**
     * 参数校验
     */
    @Test
    public void testUser1() {
        User user = new User(1,"xxx", 5, new Date());
        boolean update = userService.update(user);
        System.out.println(update);
    }

    /**
     * 结果缓存
     */
    @Test
    public void testUser2() {
        System.out.println("start test...");
        for (int i = 0; i < 20; i++) {
            Integer random = userService.getRandom();
            System.out.println(random);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 泛化引用
     */
    @Test
    public void testUser3() {
        GenericService service = (GenericService)context.getBean("genericUserService");
        Map<String, Object> result = (Map)service.$invoke("getById", new String[]{"java.lang.Integer"}, new Object[]{3});
        System.out.println(result);
    }

    /**
     * 回声测试
     */
    @Test
    public void testHello3() {
        EchoService echoService = (EchoService)helloService;
        String status = (String)echoService.$echo("OK");
        System.out.println(status);
    }

    /**
     * RpcContext测试
     */
    @Test
    public void testHello4() {
        helloService.sayHello("zhangsan");
        System.out.println(RpcContext.getContext().isProviderSide());
        System.out.println(RpcContext.getContext().isConsumerSide());
    }

    /**
     * 异步测试
     */
    @Test
    public void testUser4() {
        System.out.println("start invoke 1...");
        User user = userService.getById(3);
        System.out.println("end invoke 1, user = " + user);

        Future<User> future = RpcContext.getContext().getFuture();
        try {
            User user1 = future.get();
            System.out.println("get from future user = " + user1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        /*System.out.println("wait");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end...");*/

        /*System.out.println("start invoke 2...");
        userService.deleteById(3);
        System.out.println("end invoke 2...");*/
    }

    /**
     * 事件通知测试
     */
    @Test
    public void testUser5() {
        userService.getById(1);

        userService.getById(100);
    }
}
