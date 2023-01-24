package com.sans.controller;

import com.sans.exception.BusinessException;
import com.sans.model.dto.UserLoginRequest;
import com.sans.model.dto.UserRegisterRequest;
import com.sans.model.entity.Users;
import com.sans.model.enums.StateCode;
import com.sans.service.UsersService;
import com.sans.utils.BaseResult;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sans
 */
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UsersController {

    @Resource
    private UsersService usersService;

    /**
     * 登录
     * @param userLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    public BaseResult login(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(StateCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(StateCode.PARAMS_ERROR);
        }
        Users user = usersService.userLogin(userAccount, userPassword, request);
        user = usersService.getSafetyUser(user);
        return BaseResult.ok().putData("user", user);
    }

    /**
     * 注册
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    public BaseResult register(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (StringUtils.isAnyBlank(
                userRegisterRequest.getUserName(),
                userRegisterRequest.getUserEmail(),
                userRegisterRequest.getUserPassword(),
                userRegisterRequest.getUserPhone())) {
            throw new BusinessException(StateCode.PARAMS_ERROR);
        }
        Users user = new Users();
        BeanUtils.copyProperties(userRegisterRequest, user);
        long userId = usersService.userRegister(user);
        return BaseResult.ok().putData("userId", userId);
    }


    @GetMapping("/curr")
    public BaseResult getCurrentUser(HttpServletRequest req) {
        Users user = usersService.getCurrentUser(req);
        return BaseResult.ok().putData("user",user);
    }
}

