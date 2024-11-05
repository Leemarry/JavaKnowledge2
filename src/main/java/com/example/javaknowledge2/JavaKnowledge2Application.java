package com.example.javaknowledge2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//
//@SpringBootApplication
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class JavaKnowledge2Application {

    public static void main(String[] args) {
        SpringApplication.run(JavaKnowledge2Application.class, args);
    }

}
