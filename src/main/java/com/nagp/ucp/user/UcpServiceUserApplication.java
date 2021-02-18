package com.nagp.ucp.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@ComponentScan({ "com.nagp.ucp" })
public class UcpServiceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcpServiceUserApplication.class, args);
    }

}
