package com.rungroop.login.service;

import java.util.Date;
import java.util.List;

import com.rungroop.login.dto.Core2Dto;

public interface Core2Service {
    List<Core2Dto> obtenerReporte(Date fechaInicio, Date fechaFin);
}
