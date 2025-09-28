package com.tj.projetoTJ.dto;

import com.tj.projetoTJ.model.Audiencia;
import com.tj.projetoTJ.model.Processo;
import com.tj.projetoTJ.model.enums.TipoAudiencia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AudienciaResponseDTO {

    private Long processoId;

    private String local;

    private LocalDateTime dataHora;

    private LocalDateTime dataHoraFim;

    private TipoAudiencia tipoAudiencia;

    public AudienciaResponseDTO(Audiencia audiencia) {
        this.processoId = audiencia.getProcesso().getId();
        this.local = audiencia.getLocal();
        this.dataHora = audiencia.getDataHora();
        this.dataHoraFim = audiencia.getDataHoraFim();
        this.tipoAudiencia = audiencia.getTipoAudiencia();

    }
}
