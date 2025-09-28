package com.tj.projetoTJ.dto;

import com.tj.projetoTJ.model.Processo;
import com.tj.projetoTJ.model.enums.StatusProcesso;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoResponseDTO {

    private Long id;

    @NotBlank(message = "O número do processo é obrigatório")
    private String numeroProcesso;

    @NotBlank(message = "A vara é obrigatória")
    private String vara;

    @NotBlank(message = "A comarca é obrigatória")
    private String comarca;

    private String assunto;

    private StatusProcesso status;

    public ProcessoResponseDTO(Processo processo){
        this.id = processo.getId();
        this.numeroProcesso = processo.getNumeroProcesso();
        this.vara = processo.getVara();
        this.comarca = processo.getComarca();
        this.assunto = processo.getAssunto();
        this.status = processo.getStatus();
    }



}
