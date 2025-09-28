package com.tj.projetoTJ.dto;

import com.tj.projetoTJ.model.Processo;
import com.tj.projetoTJ.model.enums.StatusProcesso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoRequestDTO {

    private String numeroProcesso;
    private String vara;
    private String comarca;
    private String assunto;
    private StatusProcesso statusProcesso;



}
