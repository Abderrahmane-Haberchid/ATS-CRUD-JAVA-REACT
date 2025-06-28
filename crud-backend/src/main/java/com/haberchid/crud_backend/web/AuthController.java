package com.haberchid.crud_backend.web;

import com.haberchid.crud_backend.services.JwtService;
import com.haberchid.crud_backend.dto.AuthenticationResponseDto;
import com.haberchid.crud_backend.dto.CredentialsDto;
import com.haberchid.crud_backend.dto.UserDto;
import com.haberchid.crud_backend.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("signIn")
    public ResponseEntity<AuthenticationResponseDto> signIn(@RequestBody CredentialsDto credentialsDto){
        var response = authenticationService.authenticate(credentialsDto);
        if (response != null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("signUp")
    public ResponseEntity<AuthenticationResponseDto> signUp(@RequestBody UserDto userDto){
        var response = authenticationService.register(userDto);
        if (response != null)
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
