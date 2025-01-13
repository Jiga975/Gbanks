package com.numbers.gbank.mapper;


import com.numbers.gbank.entity.User;
import com.numbers.gbank.enums.ROLE;
import com.numbers.gbank.request.UserRequest;
import com.numbers.gbank.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User toEntity(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setUserName(userRequest.getUserName());
        user.setGender(userRequest.getGender());
        user.setPassword(userRequest.getPassword());
        user.setAge(userRequest.getAge());
        user.setEmail(userRequest.getEmail());
        user.setRole(ROLE.valueOf(userRequest.getRole()));
        return user;
    }

    public static UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setUserName(user.getUserName());
        response.setGender(user.getGender());
        response.setAge(user.getAge());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().toString());
        return response;
    }
}

