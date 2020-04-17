package com.kuang.mybatis_plus.service.impl;

import com.kuang.mybatis_plus.pojo.User;
import com.kuang.mybatis_plus.mapper.UserMapper;
import com.kuang.mybatis_plus.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cdd
 * @since 2020-04-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
