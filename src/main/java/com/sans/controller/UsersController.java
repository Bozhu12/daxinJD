package com.sans.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sans.exception.BusinessException;
import com.sans.model.dto.UserLoginRequest;
import com.sans.model.dto.UserRegisterRequest;
import com.sans.model.entity.Users;
import com.sans.model.enums.StateCode;
import com.sans.service.UsersService;
import com.sans.utils.BaseResult;
import com.sans.utils.JwtUtils;
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

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 登录
     * @param userLoginRequest
     * @param request
     * @return
     */
    @PostMapping(value = "/login", headers = "Authorization=token")
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
        user.setUserPassword(null);
        String userJson = null;
        try {
            userJson = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new BusinessException(StateCode.OPERATION_ERROR, "token生成异常!");
        }
        String token = JwtUtils.generateToken(userJson);
        return BaseResult.ok()
                .putData("user",user)
                .putData("token",token);
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
                userRegisterRequest.getUserPhone())){
            throw new BusinessException(StateCode.PARAMS_ERROR , "参数不能为空");
        }
        Users user = new Users();
        BeanUtils.copyProperties(userRegisterRequest,user);
        long userId = usersService.userRegister(user);
        return BaseResult.ok().putData("userId",userId);
    }

    @GetMapping("/get/login")
    public BaseResult getLoginUser(HttpServletRequest request) {
        return BaseResult.ok();
    }



}

