package com.rungroop.login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ClientDto {
    private Long id;
    @NotEmpty(message = "El nombre no puede ser vacio")
    private String nombre;
    @NotEmpty(message = "El apellido no puede ser vacio")
    private String apellido;
    private String pais;
    @Email(message = "Correo debe ser un email valido")
    private String correo;
    private String photoUrl;
    private Integer edad;

}
