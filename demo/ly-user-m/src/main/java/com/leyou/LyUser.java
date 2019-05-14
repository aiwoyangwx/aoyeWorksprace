package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
// 导入tk包
@MapperScan("com.leyou.mapper")
public class LyUser {
    public static void main(String[] args) {
        SpringApplication.run(LyUser.class, args);
    }
}
