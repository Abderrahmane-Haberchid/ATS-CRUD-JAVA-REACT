package com.haberchid.crud_backend.services;


import com.haberchid.crud_backend.dto.AuthenticationResponseDto;
import com.haberchid.crud_backend.dto.CredentialsDto;
import com.haberchid.crud_backend.dto.UserDto;
import com.haberchid.crud_backend.mappers.UserMapper;
import com.haberchid.crud_backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthenticationResponseDto authenticate(CredentialsDto credentialsDto){
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(credentialsDto.email());
            if (passwordEncoder.matches(credentialsDto.password(), userDetails.getPassword())){
                return AuthenticationResponseDto.builder()
                        .userDetails(userDetails)
                        .jwt(jwtService.generateToken(userDetails))
                        .build();
            }
            else
                return null;
        } catch (UsernameNotFoundException e) {
            throw new BadCredentialsException("Invalid credentials", e);
        }

    }

    public AuthenticationResponseDto register(UserDto userDto){

        UserDetails userDetails = userRepository.save(userMapper.dtoToUser(userDto));
        return AuthenticationResponseDto.builder()
                .userDetails(userDetails)
                .jwt(jwtService.generateToken(userDetails))
                .build();
    }
}
