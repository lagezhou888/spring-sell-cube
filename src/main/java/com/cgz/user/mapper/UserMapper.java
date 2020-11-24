package com.cgz.user.mapper;

import com.cgz.conf.MybatisPlusRedisCache;
import com.cgz.user.model.User;

import org.apache.ibatis.annotations.CacheNamespace;
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


@Mapper
@Repository 
@CacheNamespace(implementation= MybatisPlusRedisCache.class,eviction=MybatisPlusRedisCache.class)
public interface UserMapper extends BaseMapper<User> {
	
	User loginUser(User user);
	
}
