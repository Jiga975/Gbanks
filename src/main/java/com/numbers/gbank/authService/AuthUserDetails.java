package com.numbers.gbank.authService;
import com.numbers.gbank.request.LoginRequest;
import com.numbers.gbank.request.UserRequest;
import com.numbers.gbank.response.AuthResponse;

public interface AuthUserDetails {
    AuthResponse signIn(LoginRequest loginRequest);
    AuthResponse userSignup(UserRequest userSignUpRequest) throws Exception;
    AuthResponse adminSignup(UserRequest userSignUpRequest) throws Exception;
}
