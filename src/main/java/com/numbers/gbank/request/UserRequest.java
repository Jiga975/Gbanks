package com.numbers.gbank.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String firstName;
    private String lastName;
    private String userName;
    private String gender;
    private String password;
    private int age;
    private String email;
    private String role; // ROLE as String for simplicity in requests
}
