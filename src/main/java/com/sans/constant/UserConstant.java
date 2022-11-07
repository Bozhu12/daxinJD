package com.sans.constant;

/**
 * 用户常量
 *
 * @author Sans
 */
public interface UserConstant {

    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "userLoginState";

    /**
     * 系统用户 id（虚拟用户）
     */
    long SYSTEM_USER_ID = 0;

    //  region 权限

    /**
     * 默认权限
     */
    Integer DEFAULT_ROLE = 0;

    /**
     * 管理员权限
     */
    Integer ADMIN_ROLE = 1;

    // endregion
}
