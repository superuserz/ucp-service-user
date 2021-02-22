package com.nagp.ucp.user;


import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import feign.jackson.JacksonEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@ComponentScan({ "com.nagp.ucp" })
@Configuration
public class UcpServiceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcpServiceUserApplication.class, args);
    }
    
    /**
     * The jackson encoder used to encode JSON response.
     *
     * @return the jackson encoder
     */
    @Bean
    public JacksonEncoder jacksonEncoder() {
        return new JacksonEncoder(Arrays.<Module> asList(new JavaTimeModule()));
    }
    
    

}
