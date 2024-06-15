package com.rungroop.login.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rungroop.login.dto.Core2Dto;
import com.rungroop.login.service.Core2Service;


@Controller
//@RequestMapping("/core2")
public class Core2Controller {
    @Autowired
    private  Core2Service core2Service;
    @GetMapping("/core2")
    public String home(){

        return "core2";
    }
    @GetMapping("/resultadocore2")
    public String obtenerReporte(
        @RequestParam ("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
        @RequestParam ("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin,
    Model model) {
        List<Core2Dto> reportes = core2Service.obtenerReporte(fechaInicio, fechaFin);
        model.addAttribute("reportes", reportes);
        return "reportescore2";
    }
    
}
