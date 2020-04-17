package com.kuang;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInSql(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id","select id from user where id < 4");
        userMapper.selectObjs(wrapper).forEach(System.out::println);
    }

    @Test
    void testSelectCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .between("age", 18, 22)
                .orderByDesc("id");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
//        Integer count = userMapper.selectCount(wrapper);
//        System.out.println(count);
    }

    @Test
    void testSelectOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "Tom");  //查询名字为Tom的用户
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    void testSelectList() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .isNotNull("email")
                .isNotNull("name")
                .ge("age", 18);  //年龄>=18
        userMapper.selectList(wrapper).forEach(System.out::println);
    }
}
