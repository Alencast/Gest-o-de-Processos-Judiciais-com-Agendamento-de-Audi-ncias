package com.tj.projetoTJ.mapper;

import com.tj.projetoTJ.dto.AudienciaRequestDTO;
import com.tj.projetoTJ.dto.AudienciaResponseDTO;
import com.tj.projetoTJ.model.Audiencia;
import com.tj.projetoTJ.model.Processo;
import com.tj.projetoTJ.service.ProcessoService;
import org.springframework.stereotype.Component;

@Component
public class AudienciaMapper {

    //DTO -> entidade
    public Audiencia toEntity(AudienciaRequestDTO dto, Processo processo){

        if(dto == null || processo == null){
            return null;
        }

        Audiencia audiencia = new Audiencia();
        audiencia.setProcesso(processo);
        audiencia.setLocal(dto.getLocal());
        audiencia.setDataHora(dto.getDataHora());
        audiencia.setDataHoraFim(dto.getDataHoraFim());
        audiencia.setTipoAudiencia(dto.getTipoAudiencia());

        return audiencia;
    }

    //entidade -> DTO
    public AudienciaResponseDTO respostaDTO(Audiencia audiencia){
        if(audiencia == null){
            return null;
        }

        return new AudienciaResponseDTO(audiencia);
    }


}
