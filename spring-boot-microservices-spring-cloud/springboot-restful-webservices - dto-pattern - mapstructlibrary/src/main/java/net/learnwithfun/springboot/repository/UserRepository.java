package net.learnwithfun.springboot.repository;

import net.learnwithfun.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
