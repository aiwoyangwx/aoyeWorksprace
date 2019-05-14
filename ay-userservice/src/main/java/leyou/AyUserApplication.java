
package leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
// 导入tk包
@MapperScan("leyou.mapper")
public class AyUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(AyUserApplication.class, args);
    }
}
