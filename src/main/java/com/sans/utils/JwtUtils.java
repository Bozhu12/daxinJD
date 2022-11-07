package com.sans.utils;

import com.alibaba.druid.support.json.JSONUtils;
import com.sans.exception.BusinessException;
import com.sans.model.enums.StateCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class JwtUtils {

    // 30天过期
    private static long expire = 1296000;
    // 秘钥
    private static String secret = "Sans";

    /**
     * 生成token
     * @param obj 用户
     * @return token
     */
    public static String generateToken(String obj) {
        Date now = new Date();
        // 过期时间
        Date expiration = new Date(now.getTime() + 1000 * expire);
        return Jwts.builder()
                .setHeaderParam("type","JWT")
                .setSubject(obj)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 解析token
     * @param token token
     * @return
     */
    public static Claims getClaimsByToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 解析token (提取对象)
     * @param token
     * @return 原型
     */
    public static Object getObjectByToken(String token) {
        try {
            Claims claims = getClaimsByToken(token);
            String userJson = String.valueOf(claims.get("sub"));
            if (StringUtils.isBlank(userJson)) throw new RuntimeException();
            return JSONUtils.parse(userJson);
        } catch (Exception e) {
            throw new BusinessException(StateCode.PARAMS_ERROR, "token失效");
        }
    }




}
