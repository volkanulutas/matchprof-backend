package com.euv.proffme.model.dto.user;

import lombok.Data;

@Data
public class UserDto {
    private Long id;

    private String email;

    private String phoneNumber;

    private String password;

    private String profilePicture;

    private String name;

    private String surname;

    private boolean isEnabled;
}
