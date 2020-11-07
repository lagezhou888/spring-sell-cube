package com.cgz.user.service;

import com.cgz.user.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caogzh
 * @since 2020-10-22
 */
public interface UserService extends IService<User> {

	public User getLoginUser(User user);

}
