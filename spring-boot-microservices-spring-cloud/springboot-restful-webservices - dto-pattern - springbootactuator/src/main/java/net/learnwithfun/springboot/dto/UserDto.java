package net.learnwithfun.springboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    //User first name should not be null or empty
    @NotEmpty(message = "User FirstName should not be null or empty")
    private String firstName;
    //User last name should not be null or empty
    @NotEmpty(message = "User LastName should not be null or empty")
    private String lastName;
    //User email should not be null or empty and should be valid
    @Email (message = "Email address should be valid")
    @NotEmpty(message = "User Email should not be null or empty")
    private String emailAddress;
}
