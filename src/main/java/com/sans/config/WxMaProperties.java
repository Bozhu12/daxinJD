package com.sans.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 配置实体
 * @author Sans
 */
@Data
@ConfigurationProperties(prefix = "wx.miniapp")
public class WxMaProperties {
    private List<Config> configs;

    @Data
    public static class Config{
        /**
         * 小程序app
         */
        private String appid;
        /**
         * 小程序Secret
         */
        private String secret;
    }
}
