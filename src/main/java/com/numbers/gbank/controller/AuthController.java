package com.numbers.gbank.controller;

import com.numbers.gbank.authService.AuthUserDetails;
import com.numbers.gbank.request.LoginRequest;
import com.numbers.gbank.request.UserRequest;
import com.numbers.gbank.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/")
public class AuthController {
    private final AuthUserDetails authUserDetails;

    @PostMapping("signup")
    public AuthResponse userSignup(@RequestBody UserRequest userSignUpRequest) throws Exception {
        return authUserDetails.userSignup(userSignUpRequest);
    }

    @PostMapping("login")
    public AuthResponse signIn(@RequestBody LoginRequest loginRequest){
        return authUserDetails.signIn(loginRequest);
    }
}
