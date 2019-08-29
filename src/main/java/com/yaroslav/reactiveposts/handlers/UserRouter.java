package com.yaroslav.reactiveposts.handlers;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> userRouting(UserHandler userHandler) {

        return RouterFunctions
                .route(RequestPredicates.GET("/hello"), userHandler::hello)
                .andRoute(RequestPredicates.GET("/user"), userHandler::getAllUsers)
                .andRoute(RequestPredicates.GET("/user/{userId}"), userHandler::getUserById)
                .andRoute(RequestPredicates.POST("/user"), userHandler::addUser)
                .andRoute(RequestPredicates.POST("/user/{userId}"), userHandler::updateUser);
    }

    @Bean
    public RouterFunction<ServerResponse> userPostsRouting(UserHandler userHandler) {

        return RouterFunctions
                .route(RequestPredicates.GET("/user/{userId}/post"), userHandler::getPostsForUser)
                .andRoute(RequestPredicates.POST("/user/{userId}/post"), userHandler::addPostForUser)
                .andRoute(RequestPredicates.DELETE("/user/{userId}/post/{postId}"), userHandler::addPostForUser);
    }

}
