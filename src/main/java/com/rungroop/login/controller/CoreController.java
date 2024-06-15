package com.rungroop.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rungroop.login.dto.Core2Dto;
import com.rungroop.login.service.CoreService;

@Controller

public class CoreController {
    @Autowired
    private CoreService coreService;

    @GetMapping("/core")
    public String home() {
        return "core";
    }

    @GetMapping("/resultadocore")
    public String obtenerReporte(Model model) {
        List<Core2Dto> reportes = coreService.obtenerReporte();
        model.addAttribute("reportes", reportes);
        return "reportescore";  // Asegúrate de que el nombre de la vista sea correcto
    }
}

