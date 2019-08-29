package com.yaroslav.reactiveposts.handlers;

import com.yaroslav.reactiveposts.model.Post;
import com.yaroslav.reactiveposts.model.User;
import com.yaroslav.reactiveposts.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

    private UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("Hello, Spring!"));
    }

    public Mono<ServerResponse> getUserById(ServerRequest request) {
        String userId = getUserId(request);
        Mono<User> userMono = userService.getUserById(userId);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userMono, User.class);
    }

    public Mono<ServerResponse> getAllUsers(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.userService.getAllUsers(), User.class);
    }

    public Mono<ServerResponse> addUser(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.userService.createUser(userMono), User.class);
    }

    public Mono<ServerResponse> updateUser(ServerRequest request) {
        String userId = getUserId(request);
        Mono<User> userMono = request.bodyToMono(User.class);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.userService.updateUser(userId, userMono), User.class);
    }

    public Mono<ServerResponse> deleteUser(ServerRequest request) {
        String userId = getUserId(request);
        Mono<Void> voidMono = this.userService.deleteUser(userId);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(voidMono, Void.class);
    }


    public Mono<ServerResponse> getPostsForUser(ServerRequest request) {
        String userId = getUserId(request);
        Flux<Post> postFlux = this.userService.getAllUserPosts(userId);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(postFlux, Post.class);
    }

    public Mono<ServerResponse> addPostForUser(ServerRequest request) {
        String userId = getUserId(request);
        Mono<Post> postMono = request.bodyToMono(Post.class);
        Mono<Void> response = this.userService.addPostsForUser(userId, postMono);

        return ServerResponse.ok().build(response);
    }

    public Mono<ServerResponse> deletePostForUser(ServerRequest request) {
        String userId = getUserId(request);
        Mono<Post> postMono = request.bodyToMono(Post.class);
        Mono<Void> response = this.userService.deletePostsForUser(userId, postMono);

        return ServerResponse.ok().build(response);
    }

    private String getUserId(ServerRequest request) {
        return request.pathVariable("userId");
    }

}
