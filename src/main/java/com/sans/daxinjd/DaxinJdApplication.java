package com.sans.daxinjd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sans.daxinjd.mapper")
public class DaxinJdApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaxinJdApplication.class, args);
    }

}
