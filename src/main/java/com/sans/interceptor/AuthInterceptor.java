package com.sans.interceptor;

import com.sans.exception.BusinessException;
import com.sans.model.entity.Users;
import com.sans.model.enums.StateCode;
import com.sans.utils.LogMsgUtils;
import com.sans.utils.UriDistinguishUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.baomidou.mybatisplus.core.assist.ISqlRunner.COUNT;
import static com.sans.constant.UserConstant.USER_LOGIN_STATE;
import static com.sans.utils.UriDistinguishUtils.*;


/**
 * 权限鉴定
 * @author Sans
 */
public class AuthInterceptor implements HandlerInterceptor {

    /**
     * 登录
     * @param req
     * @param resp
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp, @NotNull Object handler) throws Exception {
        String uri = req.getRequestURI().substring(4);

        if (uri.contains("fileauth.txt")) return true;

        switch (uri) {
            case "/user/login":
            case "/user/register":
                return true;
            default:
        }

        // 用户
        Object attribute = req.getSession().getAttribute(USER_LOGIN_STATE);
        if (attribute == null) throw new BusinessException(StateCode.NOT_LOGIN_ERROR);
        Users user = (Users) attribute;

        // 日志
        // 执行人 --> 操作人 ==> token获取
        String userName = "[" + user.getId() + " | " + user.getUserName() + "]";
        LogMsgUtils.logMessage.put("userName", userName);
        // 执行方法 --> 操作方式 ==> uri获取
        String typeName = UriDistinguishUtils.oneLevelAnalysis(uri);
        String operationName = UriDistinguishUtils.twoLevelAnalysis(uri);
        LogMsgUtils.logMessage.put("typeName", typeName);
        LogMsgUtils.logMessage.put("operationName", operationName);

        // 权限鉴定
        // 0游客,1销售,2派送,3管理
        Integer role = user.getUserRole();
        switch (role) {
            case 0:
                if (uri.matches(FIND)) return true;
                if (uri.matches(SEARCH)) return true;
                if (uri.matches(LIST)) return true;
                if (uri.matches(COUNT)) return true;
                if (uri.matches(CURRENT)) return true;
            case 1:
            case 2:
                if (uri.matches(FIND)) return true;
                if (uri.matches(SEARCH)) return true;
                if (uri.matches(LIST)) return true;
                if (uri.matches(COUNT)) return true;
                if (uri.matches(CURRENT)) return true;
                if (uri.matches(EDIT)) return true;
                if (uri.matches(ADD)) return true;
                if (uri.matches(CREATE)) return true;
            case 3:
                return true;
            default:
        }
        return false;
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp, @NotNull Object handler, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp, @NotNull Object handler, Exception ex) throws Exception {

    }

}
