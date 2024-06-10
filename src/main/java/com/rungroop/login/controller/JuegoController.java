package com.rungroop.login.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.rungroop.login.service.JuegoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import com.rungroop.login.dto.JuegoDto;
import com.rungroop.login.models.Juego;

@Controller
public class JuegoController {
    private JuegoService juegoService;

    public JuegoController(JuegoService juegoService) {
        this.juegoService = juegoService;
    }

    @GetMapping("/juegos")
    public String listJuegos(Model model) { 
        List<JuegoDto> juegos = juegoService.findAllJuegos();
        model.addAttribute("juegos", juegos);
        return "juegos-lists";
    }
    @GetMapping("/juegos/{id}")
    public String getJuegoById(@PathVariable("juegoid") Long juegoid, Model model) {
        JuegoDto juego = juegoService.findJuegoById(juegoid);
        model.addAttribute("juego", juego);
        return "juegos-detail";
    }
    @GetMapping("/juegos/new")
    public String createJuegoForm(Model model) {
        Juego juego = new Juego();
        model.addAttribute("juego", juego);
        return "juegos-create";
    }
    @GetMapping("/juegos/{juegoid}/delete")
    public String deleteJuego(@PathVariable("juegoid") Long juegoid) {
        juegoService.deleteJuego(juegoid);
        return "redirect:/juegos";
    }
    @PostMapping("/juegos/new")
    public String saveJuego(@Valid @ModelAttribute("juego") JuegoDto juegoDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("juego", juegoDto);
            return "juegos-create";
        }
        juegoService.saveJuego(juegoDto);
        return "redirect:/juegos";
    }
    




}
