package com.osir.aiservice;

import com.osir.commonservice.config.FeignHeaderConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import(FeignHeaderConfig.class) // 引入feign配置类，自动传递header
@EnableFeignClients(basePackages = "com.osir.aiservice.feign")
@EnableDiscoveryClient
@EnableTransactionManagement // 事务
@SpringBootApplication
public class AiServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiServiceApplication.class, args);
    }

}
