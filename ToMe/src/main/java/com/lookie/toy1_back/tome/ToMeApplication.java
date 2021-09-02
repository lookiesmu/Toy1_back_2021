package com.lookie.toy1_back.tome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.lookie.toy1_back.tome.repository")
@EntityScan("com.lookie.toy1_back.tome.domain")
@SpringBootApplication
public class ToMeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToMeApplication.class, args);
    }

}
