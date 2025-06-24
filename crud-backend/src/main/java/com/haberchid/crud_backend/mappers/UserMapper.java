package com.haberchid.crud_backend.mappers;

import com.haberchid.crud_backend.dto.UserDto;
import com.haberchid.crud_backend.entitys.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto userToDto(User user){
        return UserDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public User dtoToUser(UserDto userDto){
        return User.builder()
                .userId(userDto.userId())
                .name(userDto.name())
                .email(userDto.email())
                .password(userDto.password())
                .build();
    }
}
