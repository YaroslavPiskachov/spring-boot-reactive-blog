package com.yaroslav.reactiveposts.service.impl;

import com.yaroslav.reactiveposts.model.Post;
import com.yaroslav.reactiveposts.model.User;
import com.yaroslav.reactiveposts.repository.UserReactiveRepository;
import com.yaroslav.reactiveposts.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserReactiveRepository userRepository;

    public UserServiceImpl(UserReactiveRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<User> createUser(Mono<User> user) {
        System.out.println("Getting user: " + user.toString());
        return user.flatMap(userRepository::save);
    }

    @Override
    public Flux<User> getAllUsers() {
        System.out.println("Getting all users");
        return userRepository.findAll();
    }

    @Override
    public Mono<User> updateUser(String userId, Mono<User> user) {
        System.out.println("Updating user: " + user.toString() + " with id: " + userId);
        Mono<User> userToUpdate = user.map(u -> u = new User(userId, u.getName(), u.getPosts()));
        return userToUpdate.flatMap(userRepository::save);
    }

    @Override
    public Mono<User> getUserById(String userId) {
        System.out.println("Getting user with id: " + userId);
        return userRepository.findById(userId);
    }

    @Override
    public Mono<Void> deleteUser(String userId) {
        System.out.println("Deleting user with id: " + userId);
        return userRepository.deleteById(userId);
    }

    @Override
    public Flux<Post> getAllUserPosts(String userId) {
        return userRepository.getPostsById(userId);
    }

    @Override
    public Mono<Void> addPostsForUser(String userId, Mono<Post>... posts) {
        System.out.println("Adding posts: " + Arrays.toString(posts) + " to user with id: " + userId);

        return Mono.empty();
    }

    @Override
    public Mono<Void> deletePostsForUser(String userId, Mono<Post>... posts) {
        System.out.println("Adding posts: " + Arrays.toString(posts) + " for user with id: " + userId);

        return Mono.empty();
    }
}
