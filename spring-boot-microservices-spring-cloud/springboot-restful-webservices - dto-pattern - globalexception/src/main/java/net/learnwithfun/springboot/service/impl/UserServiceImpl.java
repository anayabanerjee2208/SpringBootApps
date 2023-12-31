package net.learnwithfun.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.learnwithfun.springboot.dto.UserDto;
import net.learnwithfun.springboot.entity.User;
import net.learnwithfun.springboot.exception.EmailAlreadyExistsException;
import net.learnwithfun.springboot.exception.ResourceNotFoundException;
import net.learnwithfun.springboot.mapper.AutoUserMapper;
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
    //private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        //Convert UserDto into User JPA Entity
        //User user = UserMapper.mapToUser(userDto);

       // User user = modelMapper.map(userDto, User.class);

        User user = AutoUserMapper.MAPPER.mapToUserJpaEntity(userDto);

        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists for a user");
        }

         User savedUser = userRepository.save(user);
         //Convert User JPA entity to UserDto
        //UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

        //UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        //User user= optionalUser.get(); //get() method returns the user object
        //UserDto userDto = UserMapper.mapToUserDto(user);

        //UserDto userDto = modelMapper.map(user, UserDto.class);
        UserDto userDto = AutoUserMapper.MAPPER.mapToUserDto(user);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
       // return user.stream().map(UserMapper :: mapToUserDto).collect(Collectors.toList());

        //return users.stream().map((user) -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

        return users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    //Update ony firstName and Lastname
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", user.getId())
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        User updatedUser = userRepository.save(existingUser);
        //return UserMapper.mapToUserDto(updatedUser);
        //return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
           userRepository.deleteById(userId);
    }
}
