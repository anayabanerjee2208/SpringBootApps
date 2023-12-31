package net.learnwithfun.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.learnwithfun.springboot.dto.UserDto;
import net.learnwithfun.springboot.entity.User;
import net.learnwithfun.springboot.mapper.UserMapper;
import net.learnwithfun.springboot.repository.UserRepository;
import net.learnwithfun.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    //Use modelmapper bean to map dto <-> jpa entity
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        //Convert UserDto into User JPA Entity
        //User user = UserMapper.mapToUser(userDto);

        User user = modelMapper.map(userDto, User.class);

         User savedUser = userRepository.save(user);
         //Convert User JPA entity to UserDto
        //UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user= optionalUser.get(); //get() method returns the user object
        //UserDto userDto = UserMapper.mapToUserDto(user);

        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
       // return user.stream().map(UserMapper :: mapToUserDto).collect(Collectors.toList());

        return users.stream().map((user) -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    //Update ony firstName and Lastname
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        User updatedUser = userRepository.save(existingUser);
        //return UserMapper.mapToUserDto(updatedUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(Long userId) {
       if (userRepository.findById(userId).isPresent()){
           userRepository.deleteById(userId);
       }
    }
}
