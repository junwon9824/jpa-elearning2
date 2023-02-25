package io.sample.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

 @EnableJpaAuditing

@SpringBootApplication
public class userRestApp   {

    public static void main(String[] args) {

        SpringApplication.run(userRestApp.class, args);


    }


}
