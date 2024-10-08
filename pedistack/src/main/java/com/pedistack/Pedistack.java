package com.pedistack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@EnableJpaRepositories
@SpringBootApplication
public class Pedistack {

  public static void main(String[] args) {
    SpringApplication.run(Pedistack.class, args);
  }
}
