package com.yaroslav.reactiveposts.repository;

import com.yaroslav.reactiveposts.model.Post;
import com.yaroslav.reactiveposts.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserReactiveRepository extends ReactiveMongoRepository<User, String> {

    Flux<Post> getPostsById(String userId);

}
