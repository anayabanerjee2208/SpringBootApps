package net.learnwithfun.springboot.repository;

import net.learnwithfun.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //We have to specify the query in spring data jpa if it is a custom query, means query is not based on primary key

    Optional<User> findByEmail(String email);
}
