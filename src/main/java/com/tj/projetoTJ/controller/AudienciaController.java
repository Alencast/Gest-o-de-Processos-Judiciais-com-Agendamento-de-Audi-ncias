package com.tj.projetoTJ.controller;

import com.tj.projetoTJ.dto.AudienciaRequestDTO;
import com.tj.projetoTJ.dto.AudienciaResponseDTO;
import com.tj.projetoTJ.mapper.AudienciaMapper;
import com.tj.projetoTJ.model.Audiencia;
import com.tj.projetoTJ.service.AudienciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/audiencias")
public class AudienciaController {

    private AudienciaService audienciaService;
    private AudienciaMapper audienciaMapper;

    public AudienciaController(AudienciaService audienciaService, AudienciaMapper audienciaMapper) {
        this.audienciaService = audienciaService;
        this.audienciaMapper = audienciaMapper;
    }

    @Operation(summary = "Agendar nova audiência", description = "Cria uma nova audiência vinculada a um processo")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Audiência criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados")
    })
    @PostMapping
    public ResponseEntity<Audiencia> agendarAudiencia(@RequestBody @Valid AudienciaRequestDTO dto){

        Audiencia salva = audienciaService.agendarAudiencia(dto); //vai buscar o mapper lá no service pra evitar o bo do processo
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @Operation(summary = "Listar todas as audiências", description = "Retorna todas as audiências cadastradas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de audiências")
    })
    @GetMapping
    public ResponseEntity<List<AudienciaResponseDTO>> listarAudiencias(){
        List<Audiencia> audiencias = audienciaService.findAll();

        //converter os dtos loucões
        List<AudienciaResponseDTO> respostaLista = new ArrayList<>();
        for(Audiencia a : audiencias){
            respostaLista.add(audienciaMapper.respostaDTO(a));
        }
        return ResponseEntity.ok(respostaLista);
    }


    @Operation(summary = "Consultar agenda de audiências", description = "Retorna todas as audiências de uma comarca em um dia específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agenda retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    })

    @GetMapping("/agenda")
    public ResponseEntity<List<AudienciaResponseDTO>> consultarAgenda(
            @RequestParam String comarca,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data
    ){
        //da
        List<Audiencia> agenda = audienciaService.consultarAgenda(comarca, data);

        List<AudienciaResponseDTO>  respostaLista = new ArrayList<>();
        for(Audiencia a : agenda){
            respostaLista.add(audienciaMapper.respostaDTO(a));
        }
        return ResponseEntity.ok(respostaLista);
    }
}
