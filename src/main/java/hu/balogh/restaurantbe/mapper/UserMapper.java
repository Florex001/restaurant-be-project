package hu.balogh.restaurantbe.mapper;

import hu.balogh.restaurantbe.auth.controller.dto.SignupRequest;
import hu.balogh.restaurantbe.auth.controller.dto.UserDto;
import hu.balogh.restaurantbe.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "userRole", target = "userRole")
    User toEntity(UserDto userDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "userRole", target = "userRole")
    UserDto toDto(User user);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    User toEntityBySignup(SignupRequest signupRequest);

}
