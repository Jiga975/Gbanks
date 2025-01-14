package com.numbers.gbank.authService;

import com.numbers.gbank.entity.User;
import com.numbers.gbank.enums.ROLE;
import com.numbers.gbank.repository.UserRepository;
import com.numbers.gbank.request.LoginRequest;
import com.numbers.gbank.request.UserRequest;
import com.numbers.gbank.response.AuthResponse;
import com.numbers.gbank.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceDetailsImpl implements AuthUserDetails{
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsImpl customUserDetails;


    @Override
    public AuthResponse signIn(LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty() ? String.valueOf(ROLE.USER) : authorities.iterator().next().getAuthority();

        String jwt = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage("Login success");
        authResponse.setTitle(jwt);
        authResponse.setRole(ROLE.valueOf(role));

        return authResponse;
    }

    @Override
    public AuthResponse userSignup(UserRequest userSignUpRequest) throws Exception {
        User user = new User();
        Optional<User> existingUser = userRepository.findByEmail(userSignUpRequest.getEmail());

        if (existingUser.isPresent()) {
            throw new Exception(String.format("User with email %s already exists", userSignUpRequest.getEmail()));
        }

        user.setEmail(userSignUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userSignUpRequest.getPassword()));
        user.setFirstName(userSignUpRequest.getFirstName());
        user.setAge(userSignUpRequest.getAge());
        user.setGender(userSignUpRequest.getGender());
        user.setLastName(userSignUpRequest.getLastName());
        user.setUserName(userSignUpRequest.getUserName());

        ROLE role = ROLE.USER;
        user.setRole(role);

        User savedUser = userRepository.save(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setTitle("Welcome " + user.getEmail());
        authResponse.setMessage("Registration successful");
        authResponse.setRole(savedUser.getRole());

        return authResponse;
    }

    @Override
    public AuthResponse adminSignup(UserRequest userSignUpRequest) throws Exception {
        return null;
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);
        if(userDetails == null){
            throw new BadCredentialsException("Invalid username...");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password....");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
    }
}
