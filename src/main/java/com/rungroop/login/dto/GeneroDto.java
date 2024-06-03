package com.rungroop.login.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeneroDto {
    private Long gen_id;
    private String gen_nombre;
    private String gen_descripcion;
}
