package net.learnwithfun.springboot.service;

import net.learnwithfun.springboot.dto.UserDto;
import net.learnwithfun.springboot.entity.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDo);

    User getUserById(Long userId);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long userId);
}
