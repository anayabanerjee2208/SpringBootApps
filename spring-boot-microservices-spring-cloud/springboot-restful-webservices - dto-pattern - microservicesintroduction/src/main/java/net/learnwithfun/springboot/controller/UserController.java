package net.learnwithfun.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.learnwithfun.springboot.dto.UserDto;
import net.learnwithfun.springboot.entity.User;
import net.learnwithfun.springboot.exception.ErrorDetails;
import net.learnwithfun.springboot.exception.ResourceNotFoundException;
import net.learnwithfun.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Tag(
        name = "CRUD REST Apis for User Resource",
        description = "CRUD REST APIS-Create, update, Get, Delete User Api"
)
public class UserController {

    private UserService userService;

    //build create user REST API
    @PostMapping
    @Operation(
            summary = "Create user REST API",
            description = "API to save user in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    //ResponseEntity class is used to create a complete http response
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //build get user by id rest api
    @GetMapping("{id}")
    @Operation(
            summary = "Get user by ID REST API",
            description = "API to get a single user from the  database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    @Operation(
            summary = "Get all users by ID REST API",
            description = "API to get all users from the  database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Operation(
            summary = "update user REST API",
            description = "API to update a single user in the  database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody @Valid UserDto user){
        user.setId(userId);
       UserDto updatedUser = userService.updateUser(user);
        return  new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete user by ID REST API",
            description = "API to delete a single user from the  database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long userId){
         userService.deleteUser(userId);
        return new ResponseEntity<>(true, HttpStatus.OK );
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    //Use ExceptionHandler annotation to handle specific annotation
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
//                                                                        WebRequest webRequest){
//            ErrorDetails errorDetails = new ErrorDetails(
//                    LocalDateTime.now(),
//                    exception.getMessage(),
//                    webRequest.getDescription(false),
//                    "USER_NOT_FOUND"
//            );
//            return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }
}
