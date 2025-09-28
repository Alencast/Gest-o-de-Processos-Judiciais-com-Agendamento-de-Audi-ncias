package com.tj.projetoTJ.dto;

import com.tj.projetoTJ.model.Processo;
import com.tj.projetoTJ.model.enums.TipoAudiencia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AudienciaRequestDTO {

    @NotNull
    private Long processoId;

    @NotBlank
    private String local;

    @NotNull
    private LocalDateTime dataHora;

    private LocalDateTime dataHoraFim;

    private TipoAudiencia tipoAudiencia;

}
