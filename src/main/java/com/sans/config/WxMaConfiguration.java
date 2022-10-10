package com.sans.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import me.chanjar.weixin.common.error.WxRuntimeException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableConfigurationProperties(WxMaProperties.class)
public class WxMaConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "wx.miniapp.configs")
    public WxMaProperties.Config wxMaConfig() {
        return new WxMaProperties.Config();
    }

    @Bean
    public WxMaService wxMaService() {
        List<WxMaProperties.Config> configs = new ArrayList<>();
        WxMaProperties.Config wxconfig = this.wxMaConfig();
        if (wxconfig == null) {
            throw new WxRuntimeException("微信 参数配置有误");
        }
        configs.add(wxconfig);
        WxMaService maService = new WxMaServiceImpl();
        maService.setMultiConfigs(
                configs.stream()
                        .map(a -> {
                            WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
//                WxMaDefaultConfigImpl config = new WxMaRedisConfigImpl(new JedisPool());
                            // 使用上面的配置时，需要同时引入jedis-lock的依赖，否则会报类无法找到的异常
                            config.setAppid(a.getAppid());
                            config.setSecret(a.getSecret());
                            return config;
                        }).collect(Collectors.toMap(WxMaDefaultConfigImpl::getAppid, a -> a, (o, n) -> o)));
        return maService;
    }

}

