package net.learnwithfun.springboot.controller;

import lombok.AllArgsConstructor;
import net.learnwithfun.springboot.dto.UserDto;
import net.learnwithfun.springboot.entity.User;
import net.learnwithfun.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    //build create user REST API
    @PostMapping
    //ResponseEntity class is used to create a complete http response
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //build get user by id rest api
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody UserDto user){
        user.setId(userId);
       UserDto updatedUser = userService.updateUser(user);
        return  new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long userId){
         userService.deleteUser(userId);
        return new ResponseEntity<>(true, HttpStatus.OK );
    }
}
