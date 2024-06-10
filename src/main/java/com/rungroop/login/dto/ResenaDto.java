package com.rungroop.login.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResenaDto {
    private Long res_id;
    private Long jue_id;
    private Long cli_id;
    private Integer res_calificacion;
    private String res_comentario;
}
