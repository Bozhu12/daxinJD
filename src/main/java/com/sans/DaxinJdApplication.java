package com.sans;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.sans.mapper")
@EnableScheduling
public class DaxinJdApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaxinJdApplication.class, args);
    }

    /**
     * http重定向到https
     */
    //@Bean
    //public TomcatServletWebServerFactory servletContainer() {
    //    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
    //        @Override
    //        protected void postProcessContext(Context context) {
    //            SecurityConstraint constraint = new SecurityConstraint();
    //            constraint.setUserConstraint("CONFIDENTIAL");
    //            SecurityCollection collection = new SecurityCollection();
    //            collection.addPattern("/*");
    //            constraint.addCollection(collection);
    //            context.addConstraint(constraint);
    //        }
    //    };
    //    tomcat.addAdditionalTomcatConnectors(httpConnector());
    //    return tomcat;
    //}
    //
    //@Bean
    //public Connector httpConnector() {
    //    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
    //    connector.setScheme("http");
    //    //Connector监听的http的端口号
    //    connector.setPort(8080);
    //    connector.setSecure(false);
    //    //监听到http的端口号后转向到的https的端口号
    //    connector.setRedirectPort(443);
    //    return connector;
    //}
}
