package com.rungroop.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;

import com.rungroop.login.dto.Core2Dto;
import com.rungroop.login.service.Core2Service;


@Controller
//@RequestMapping("/core2")
public class Core2Controller {
    @Autowired
    private  Core2Service core2Service;
    @GetMapping("/core2")
    public String home(){

        return "clients-lists";
    }
    @GetMapping("/resultadocore2")
    public String obtenerReporte(Model model) {
        List<Core2Dto> reportes = core2Service.obtenerReporte();
        model.addAttribute("reportes", reportes);
        return "reportescore2";
    }
    
}
