package com.yaroslav.reactiveposts;

import com.yaroslav.reactiveposts.handlers.UserRouter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@Import({UserRouter.class, MongoReactiveConfiguration.class})

@SpringBootApplication
@EnableWebFlux
public class ReactivePostsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactivePostsApplication.class, args);
    }

}
