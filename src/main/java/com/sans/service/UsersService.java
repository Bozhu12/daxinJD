package com.sans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sans.model.entity.Users;

import javax.servlet.http.HttpServletRequest;

/**
* @author Sans
* @description 针对表【users】的数据库操作Service
* @createDate 2022-10-08 11:53:24
*/
public interface UsersService extends IService<Users> {

    Users userLogin(String userAccount, String userPassword, HttpServletRequest request);

    long userRegister(Users user);

}
