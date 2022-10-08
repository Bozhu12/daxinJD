package com.sans;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sans.mapper")
public class DaxinJdApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaxinJdApplication.class, args);
    }

}
