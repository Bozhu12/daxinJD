package com.sans.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sans.exception.BusinessException;
import com.sans.model.entity.Users;
import com.sans.model.enums.StateCode;
import com.sans.utils.JwtUtils;
import com.sans.utils.LogMsgUtils;
import com.sans.utils.UriDistinguishUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 权限鉴定
 * @author Sans
 */
public class AuthInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper =  new ObjectMapper();

    /**
     * 登录 和 权限鉴定
     * @param req
     * @param resp
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp, @NotNull Object handler) throws Exception {
        String uri = req.getRequestURI();
        switch (uri){
            case "/user/login" :
            case "/user/register" :
                return true;
            default:
        }
        String accessToken = req.getHeader("AccessToken");
        if (accessToken == null) throw new BusinessException(StateCode.NOT_LOGIN_ERROR);
        Object parse = JwtUtils.getObjectByToken(accessToken);
        Users user = objectMapper.convertValue(parse, Users.class);
        if (user == null) throw new BusinessException(StateCode.NO_AUTH_ERROR);

        // 执行人 --> 操作人 ==> token获取
        String userName = "["+user.getId() +" | "+ user.getUserName()+"]";
        LogMsgUtils.logMessage.put("userName",userName);

        // 执行方法 --> 操作方式 ==> uri获取
        String typeName = UriDistinguishUtils.oneLevelAnalysis(uri);
        String operationName = UriDistinguishUtils.twoLevelAnalysis(uri);
        LogMsgUtils.logMessage.put("typeName",typeName);
        LogMsgUtils.logMessage.put("operationName",operationName);
        if ("查找".equals(operationName)) return true;

        return true;
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp, @NotNull Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(req, resp, handler, modelAndView);
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp, @NotNull Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(req, resp, handler, ex);
    }

}
