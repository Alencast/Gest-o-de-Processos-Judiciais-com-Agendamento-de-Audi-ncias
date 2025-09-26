package com.tj.projetoTJ.service;

import com.tj.projetoTJ.model.Audiencia;
import com.tj.projetoTJ.model.Processo;
import com.tj.projetoTJ.repository.AudienciaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AudienciaService {

    private final AudienciaRepository audienciaRepository;

    public AudienciaService(AudienciaRepository audienciaRepository) {
        this.audienciaRepository = audienciaRepository;
    }

    public List<Audiencia> findAll(){
        return audienciaRepository.findAll();
    }

    public Audiencia agendarAudiencia(Audiencia audiencia){

        if (audiencia.getDataHoraFim() == null) {
            audiencia.setDataHoraFim(audiencia.getDataHora().plusHours(1));
        }

        boolean conflitoCheck = audienciaRepository.existsByLocalAndDataHoraBetween(
                audiencia.getLocal(),
                audiencia.getDataHora(),
                audiencia.getDataHoraFim()
        );

        if(conflitoCheck){
            throw new IllegalArgumentException("Já existe audiência marcadanesse horário");
        }

        return audienciaRepository.save(audiencia);
    }

    public List<Audiencia> consultarAgenda(String comarca, LocalDate dia){

        LocalDateTime inicio = dia.atStartOfDay(); // 00:00
        LocalDateTime fim = dia.atTime(23, 59, 59); // 23:59:59
        return audienciaRepository.findByProcesso_ComarcaAndDataHoraBetween(comarca, inicio, fim);
    }
}
