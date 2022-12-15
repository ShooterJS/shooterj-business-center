package com.shooterj.sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.shooterj.*","com.shooterj.sys.*"})
@MapperScan(value = {"com.shooterj.sys.infrastructure.persistence.mapper","com.shooterj.sys.domain.customer_.mapper"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
