package com.shadow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName SimpleMIApplication
 * @Description TODO
 * @Author CareShadow
 * @Date 2023/11/24 22:51
 * @Version 1.0
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.shadow.mapper")
public class SimpleMIApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleMIApplication.class, args);
    }
}
