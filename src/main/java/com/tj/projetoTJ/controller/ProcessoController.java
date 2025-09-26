package com.tj.projetoTJ.controller;

import com.tj.projetoTJ.model.Processo;
import com.tj.projetoTJ.model.enums.StatusProcesso;
import com.tj.projetoTJ.service.ProcessoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/processos")
public class ProcessoController {

    private ProcessoService processoService;

    public ProcessoController(ProcessoService processoService) {
        this.processoService = processoService;
    }

    //Criar processo novo
    @PostMapping
    public ResponseEntity<Processo> criarProcesso(@RequestBody Processo processo){
        Processo salvo = processoService.criarProcesso(processo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo); // devolver a resposta 201 se for criado
    }

    @GetMapping
    public ResponseEntity<List<Processo>> listarProcessos(@RequestParam(required = false) StatusProcesso status,
                                                          @RequestParam(required = false) String comarca){

        List<Processo> listaProcessos = processoService.filtrarProcesso(status, comarca);
        return ResponseEntity.ok(listaProcessos);
    }
}
