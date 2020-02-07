package com.mforum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mforum.mapper")
public class MforumApplication {

    public static void main(String[] args) {
        SpringApplication.run(MforumApplication.class, args);
    }

}
