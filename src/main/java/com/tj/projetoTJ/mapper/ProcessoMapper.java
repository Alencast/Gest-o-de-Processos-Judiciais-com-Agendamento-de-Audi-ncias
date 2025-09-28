package com.tj.projetoTJ.mapper;

import com.tj.projetoTJ.dto.ProcessoRequestDTO;
import com.tj.projetoTJ.dto.ProcessoResponseDTO;
import com.tj.projetoTJ.model.Processo;
import org.springframework.stereotype.Component;

@Component
public class ProcessoMapper {

    // DTO -> Entidade
    public Processo toEntity(ProcessoRequestDTO dto){
        if(dto == null){
            return null;
        }

        Processo processo = new Processo();
        processo.setNumeroProcesso(dto.getNumeroProcesso());
        processo.setVara(dto.getVara());
        processo.setComarca(dto.getComarca());
        processo.setAssunto(dto.getAssunto());
        processo.setStatus(dto.getStatusProcesso());
        return processo;
    }

    // Entidade -> DTO
    public ProcessoResponseDTO respostaDTO(Processo processo){

        if(processo == null){
            return null;
        }

        return new ProcessoResponseDTO(processo);

    }

}
