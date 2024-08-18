package com.winiardev.nails.security.controller.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestRegister {

    @Pattern(regexp = "^[a-zA-Z]+$", message = "firstName must contain only letters")
    @NotNull(message = "firstName cannot be empty")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "lastName must contain only letters")
    @NotNull(message = "lastName cannot be empty")
    private String lastName;

    private String email;
    private String password;
    private String phoneNumber;
}
