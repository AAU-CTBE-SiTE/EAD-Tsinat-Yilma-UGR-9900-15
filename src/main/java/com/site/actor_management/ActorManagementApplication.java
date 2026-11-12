package com.site.actor_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ActorManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActorManagementApplication.class, args);
    }

}
