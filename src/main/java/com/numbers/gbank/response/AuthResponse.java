package com.numbers.gbank.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.numbers.gbank.enums.ROLE;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponse {
    private String title;
    private String message;
    private ROLE role;
}