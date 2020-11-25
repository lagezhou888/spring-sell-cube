package com.cgz.user.service.impl;

import com.cgz.user.model.User;
import com.cgz.user.mapper.UserMapper;
import com.cgz.user.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caogzh
 * @since 2020-10-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getLoginUser(User user) {
		User u = userMapper.loginUser(user);
		return u;
	}

	@Override
	public Integer getUserList() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		Integer count = userMapper.selectCount(queryWrapper);
		return count;
	}

}
