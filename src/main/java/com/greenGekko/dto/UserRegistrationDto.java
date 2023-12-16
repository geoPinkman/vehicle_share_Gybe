package com.greenGekko.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegistrationDto {

    private String email;
    private String userPassword;
    transient private String confirmPassword;

}
