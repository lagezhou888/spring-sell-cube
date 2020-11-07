package com.cgz.user.mapper;

import com.cgz.user.model.User;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caogzh
 * @since 2020-10-22
 */

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
	
	User loginUser(User user);
	
}
