package com.sans.controller;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import com.sans.exception.BusinessException;
import com.sans.model.dto.UserLoginRequest;
import com.sans.model.enums.StateCode;
import com.sans.utils.BaseResult;
import com.sans.utils.JwtUtils;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    private final WxMaService wxMaService;

    @PostMapping(value = "/login", headers = "Authorization=token")
    public BaseResult login(@RequestBody UserLoginRequest userLoginRequest) {
        if (StringUtils.isBlank(userLoginRequest.getCode())) {
            throw new BusinessException(StateCode.NOT_FOUND_ERROR, "请退出重新登录!");
        }
        if (!wxMaService.switchover(userLoginRequest.getAppid())) {
            throw new BusinessException(StateCode.OPERATION_ERROR, "请使用正确的前端端口!");
        }

        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(userLoginRequest.getCode());
            String token = JwtUtils.generateToken(userLoginRequest.getCode());
            return BaseResult.ok()
                    .putData("data", session)
                    .putData("token", token);
        } catch (WxErrorException e) {
            throw new BusinessException(StateCode.NOT_FOUND_ERROR, "请退出重新登录!");
        } finally {
            //清理ThreadLocal
            WxMaConfigHolder.remove();
        }
    }

}

