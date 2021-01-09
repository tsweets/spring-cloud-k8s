package org.beer30.springcloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class SpringGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringGatewayApplication.class,args);
    }
}
