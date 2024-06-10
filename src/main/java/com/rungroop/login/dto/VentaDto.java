package com.rungroop.login.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class VentaDto {
    private Long vent_id;
    private Long cli_id;
    private Long jue_id;
    private Date vent_fecha;
    private Integer vent_cantidad;
    private Double vent_precio;
}
