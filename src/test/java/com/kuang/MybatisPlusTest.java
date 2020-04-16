package com.kuang;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class MybatisPlusTest {

    //继承了basemapper,所有的方法来自父类
    //也可以自己编写扩展方法
    @Autowired
    private UserMapper userMapper;

    //测试删除
    @Test
    void testDeleteById(){
        userMapper.deleteById(6l);  //配置逻辑删除后,删除操作为更新语句,将deleted更新为1
    }

    @Test
    void testDeleteByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","张三");
        userMapper.deleteByMap(map);
    }



    //分页
    @Test
    void testPage(){
        Page<User> userPage = new Page<>(1,5);
        IPage<User> page = userMapper.selectPage(userPage, null);
        List<User> records = page.getRecords();
        records.forEach(System.out::println);
        System.out.println(page.getTotal());

    }

    //多选
    @Test
    void testBatchSelect(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }


    @Test
    void testSelect(){
        User user = userMapper.selectById(1l);
        System.out.println(user);
    }

    //乐观锁
    @Test
    void testOptimistic(){
        User user = userMapper.selectById(1l);
        user.setName("cdd111");
        user.setAge(11);

        //模拟多线程有人插队
        User user2 = userMapper.selectById(1l);
        user2.setName("cdd222");
        user2.setAge(22);
        int up2 = userMapper.updateById(user2);
        System.out.println("up2"+up2);
        //插队完成

        int up1 = userMapper.updateById(user);
        System.out.println("up1"+up1);

    }


    @Test
    void testUpdate(){
        User user = new User();
//        user.setId(6l);
//        user.setName("狂神");
        user.setAge(18);
//        int i = userMapper.updateById(user);
        int i = userMapper.update(user, null);
        System.out.println(i);
    }

    @Test
    void testInsert(){
        User user = new User();
        user.setId(8L);
        user.setAge(11);
        user.setName("张三");
        user.setEmail("123");
        int insert = userMapper.insert(user);       //帮我们自动生成id 1250318645146284034
        System.out.println(insert);     //受影响的行数
        System.out.println(user);       //发现,id自动回填
    }

    @Test
    void testSelectList() {
        //参数是一个wrapper,条件构造器
        List<User> users = userMapper.selectList(null);
        /*for (User u : users) {
            System.out.println(u);
        }*/
        users.forEach(System.out::println);
    }
}
