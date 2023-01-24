package com.sans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sans.exception.BusinessException;
import com.sans.model.entity.Users;
import com.sans.model.enums.StateCode;

import javax.servlet.http.HttpServletRequest;

import static com.sans.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author Sans
* @description 针对表【users】的数据库操作Service
* @createDate 2022-10-08 11:53:24
*/
public interface UsersService extends IService<Users> {

    /**
     * 用户登录
     * @param userAccount 账号/手机号
     * @param userPassword 密码
     * @param request 请求对象
     * @return 登录对象
     */
    Users userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户注册
      * @param user 用户
     * @return 用户id
     */
    long userRegister(Users user);

    /**
     * 检验管理员
     * @param request 请求对象
     * @return 是否为管理员
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * 获取当前登录用户
     * @param request 请求对象
     * @return 当前请求用户
     */
    Users getLoginUser(HttpServletRequest request);

    /**
     * 数据脱敏 (屏蔽敏感数据)
     * @param originUser
     * @return
     */
    Users getSafetyUser(Users originUser);

    /**
     * 获取当前用户
     * @param req
     * @return
     */
    Users getCurrentUser(HttpServletRequest req);
}
