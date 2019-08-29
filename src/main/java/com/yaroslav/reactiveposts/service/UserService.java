package com.yaroslav.reactiveposts.service;

import com.yaroslav.reactiveposts.model.Post;
import com.yaroslav.reactiveposts.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> createUser(Mono<User> user);

    Flux<User> getAllUsers();

    Mono<User> updateUser(String userId, Mono<User> user);

    Mono<User> getUserById(String userId);

    Mono<Void> deleteUser(String userId);

    Flux<Post> getAllUserPosts(String userId);

    Mono<Void> addPostsForUser(String userId, Mono<Post> ... posts);

    Mono<Void> deletePostsForUser(String userId, Mono<Post> ... posts);

}
