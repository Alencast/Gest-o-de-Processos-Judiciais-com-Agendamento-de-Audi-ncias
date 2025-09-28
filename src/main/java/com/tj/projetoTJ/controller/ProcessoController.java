package com.tj.projetoTJ.controller;

import com.tj.projetoTJ.mapper.ProcessoMapper;
import com.tj.projetoTJ.dto.ProcessoRequestDTO;
import com.tj.projetoTJ.dto.ProcessoResponseDTO;
import com.tj.projetoTJ.model.Processo;
import com.tj.projetoTJ.model.enums.StatusProcesso;
import com.tj.projetoTJ.service.ProcessoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/processos")
public class ProcessoController {

    private ProcessoService processoService;
    private ProcessoMapper processoMapper;

    public ProcessoController(ProcessoService processoService, ProcessoMapper processoMapper) {
        this.processoService = processoService;
        this.processoMapper = processoMapper;
    }

    @Operation(summary = "Criar novo processo", description = "Cria um processo com os dados informados")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Processo criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados")
    })

    @PostMapping
    public ResponseEntity<ProcessoResponseDTO> criarProcesso(@RequestBody @Valid ProcessoRequestDTO requestDTO){
        Processo processo = processoMapper.toEntity(requestDTO);
        Processo salvo = processoService.criarProcesso(processo);
        return ResponseEntity.status(HttpStatus.CREATED).body(processoMapper.respostaDTO(salvo)); // devolver a resposta 201 se for criado
    }


    @Operation(summary = "Listar processos", description = "Lista todos os processos, podendo filtrar por status e comarca")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de processos retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<ProcessoResponseDTO>> listarProcessos(@RequestParam(required = false) StatusProcesso status,
                                                                     @RequestParam(required = false) String comarca){

        List<Processo> listaProcessos = processoService.filtrarProcesso(status, comarca);

        List<ProcessoResponseDTO> listaDTOs = new ArrayList<>();
        for(Processo processo : listaProcessos){
            listaDTOs.add(processoMapper.respostaDTO(processo));
        }
        return ResponseEntity.ok(listaDTOs);
    }
}
