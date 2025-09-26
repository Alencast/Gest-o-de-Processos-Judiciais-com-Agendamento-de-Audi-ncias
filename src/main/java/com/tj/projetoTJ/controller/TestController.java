package com.tj.projetoTJ.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/")
    public String hello() {
        return "Sistema de Gestão de Processos Judiciais está funcionando!";
    }
}
