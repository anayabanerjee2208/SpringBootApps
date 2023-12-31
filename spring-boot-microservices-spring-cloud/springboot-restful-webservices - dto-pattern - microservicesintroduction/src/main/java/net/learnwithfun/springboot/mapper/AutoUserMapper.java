package net.learnwithfun.springboot.mapper;

import net.learnwithfun.springboot.dto.UserDto;
import net.learnwithfun.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
//In order to map two dto classes the field names should be same in both classes for both MapStruct and ModelMapper library
//If field names are different then we have specific annotation available in MapStruct library
public interface AutoUserMapper {

    //We cannot create object of the interface. MapStruct provides the implementation for the interface at compilation time.
    // Inorder to get the implementation we can use the Mappers utility class
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
    @Mapping(source="email", target = "emailAddress")
    UserDto mapToUserDto(User user);

    @Mapping(source="emailAddress", target = "email")
    User mapToUserJpaEntity(UserDto userDto);
}
