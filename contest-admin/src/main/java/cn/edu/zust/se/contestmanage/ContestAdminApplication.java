package cn.edu.zust.se.contestmanage;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class ContestAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContestAdminApplication.class, args);
    }

}
