package com.example.project;

import com.example.project.config.JpaConfig;
import com.example.project.config.S3Config;
import com.example.project.config.SecurityConfig;
import com.example.project.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@Import({SwaggerConfig.class, S3Config.class, JpaConfig.class, SecurityConfig.class})
@EnableJpaAuditing
public class ProjectApplication {
    static {
        System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

}
