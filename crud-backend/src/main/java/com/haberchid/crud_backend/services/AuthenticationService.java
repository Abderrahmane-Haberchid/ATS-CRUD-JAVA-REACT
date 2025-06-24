package com.haberchid.crud_backend.services;


import com.haberchid.crud_backend.config.UserAuthenticationProvider;
import com.haberchid.crud_backend.dto.AuthenticationResponseDto;
import com.haberchid.crud_backend.dto.CredentialsDto;
import com.haberchid.crud_backend.dto.UserDto;
import com.haberchid.crud_backend.mappers.UserMapper;
import com.haberchid.crud_backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthenticationProvider userAuthenticationProvider;

    public UserDto authenticate(CredentialsDto credentialsDto){
        var userDto = findByEmail(credentialsDto.email());
        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), userDto.password()))
            return userDto;
        else
            throw new RuntimeException("Password invalid");

    }

    public UserDto findByEmail(String email){
        var user = userRepository.findByEmail(email);
        if (user != null)
            return userMapper.userToDto(user);
        else
            throw new RuntimeException("Invalid Logins");
    }

    public AuthenticationResponseDto register(UserDto userDto){

        var user = userRepository.save(userMapper.dtoToUser(userDto));

        return AuthenticationResponseDto.builder()
                .email(user.getEmail())
                .jwt(userAuthenticationProvider.createToken(user.getEmail()))
                .build();
    }
}
