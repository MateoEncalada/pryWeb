package com.rungroop.login.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
@Data
public class RegistrationDto {

    private Long id;
    @NotEmpty(message = "El nombre no puede ser vacio")
    private String username;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

}
