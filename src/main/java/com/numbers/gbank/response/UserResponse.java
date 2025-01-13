package com.numbers.gbank.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String firstName;
    private String lastName;
    private String userName;
    private String gender;
    private int age;
    private String email;
    private String role; // ROLE as String for user responses
}
