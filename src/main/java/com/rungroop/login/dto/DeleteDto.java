package com.rungroop.login.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;



@Data
@Builder
public class DeleteDto {
    private Long jue_id;
    private String jue_Titulo;
    private String jue_Descripcion;
    private Date jue_FechaLanzamiento;
    private Double jue_Precio;
    private Integer jue_Inventario;
    
    
}
