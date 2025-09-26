package com.tj.projetoTJ.controller;

import com.tj.projetoTJ.model.Audiencia;
import com.tj.projetoTJ.service.AudienciaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/audiencias")
public class AudienciaController {

    private AudienciaService audienciaService;

    public AudienciaController(AudienciaService audienciaService) {
        this.audienciaService = audienciaService;
    }

    @PostMapping
    public ResponseEntity<Audiencia> agendarAudiencia(@RequestBody Audiencia audiencia){

        Audiencia salva = audienciaService.agendarAudiencia(audiencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @GetMapping
    public ResponseEntity<List<Audiencia>> listarAudiencias(){
        List<Audiencia> audiencias = audienciaService.findAll();
        return ResponseEntity.ok(audiencias);
    }

    @GetMapping("/agenda")
    public ResponseEntity<List<Audiencia>> consultarAgenda(
            @RequestParam String comarca,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data
    ){
        //da
        List<Audiencia> agenda = audienciaService.consultarAgenda(comarca, data);
        return ResponseEntity.ok(agenda);
    }
}
