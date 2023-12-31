package net.learnwithfun.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.learnwithfun.springboot.dto.UserDto;
import net.learnwithfun.springboot.entity.User;
import net.learnwithfun.springboot.repository.UserRepository;
import net.learnwithfun.springboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        //Convert UserDto into User JPA Entity
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
         User savedUser = userRepository.save(user);
         //Convert User JPA entity to UserDto
        UserDto savedUserDto = new UserDto(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail()
        );
        return savedUserDto;
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.get(); //get() method returns the user object
    }

    @Override
    public List<User> getAllUsers() {
        List<User> user = userRepository.findAll();
        return user;
    }

    @Override
    //Update ony firstName and Lastname
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        User updatedUser = userRepository.save(existingUser);
        return updatedUser;
    }

    @Override
    public void deleteUser(Long userId) {
       if (userRepository.findById(userId).isPresent()){
           userRepository.deleteById(userId);
       }
    }
}
