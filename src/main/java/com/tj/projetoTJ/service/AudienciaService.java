package com.tj.projetoTJ.service;

import com.tj.projetoTJ.dto.AudienciaRequestDTO;
import com.tj.projetoTJ.mapper.AudienciaMapper;
import com.tj.projetoTJ.model.Audiencia;
import com.tj.projetoTJ.model.Processo;
import com.tj.projetoTJ.model.enums.StatusProcesso;
import com.tj.projetoTJ.repository.AudienciaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AudienciaService {

    private final AudienciaRepository audienciaRepository;
    private ProcessoService processoService;
    private AudienciaMapper audienciaMapper;

    public AudienciaService(AudienciaRepository audienciaRepository, ProcessoService processoService, AudienciaMapper audienciaMapper) {
        this.audienciaRepository = audienciaRepository;
        this.processoService = processoService;
        this.audienciaMapper = audienciaMapper;
    }

    public List<Audiencia> findAll(){
        return audienciaRepository.findAll();
    }

    public Audiencia agendarAudiencia(AudienciaRequestDTO dto){

        Processo processo = processoService.buscarPorId(dto.getProcessoId());
        Audiencia audiencia = audienciaMapper.toEntity(dto, processo);
        if(audiencia.getProcesso() == null){
            throw new IllegalArgumentException("Audiência não está vinculada a um processo válido");
        }

        var processoStatus = audiencia.getProcesso().getStatus();

        if(processoStatus == StatusProcesso.ARQUIVADO || processoStatus == StatusProcesso.SUSPENSO){
            throw new IllegalArgumentException("Processo suspenso ou cancelado");
        }
        if (audiencia.getDataHoraFim() == null) {
            audiencia.setDataHoraFim(audiencia.getDataHora().plusHours(1));
        }

        int diaDaSemana = audiencia.getDataHora().getDayOfWeek().getValue();
        if(diaDaSemana == 6 || diaDaSemana == 7){
            throw new IllegalArgumentException("Audiência não pode ser marcada no fds");
        }

        boolean conflitoCheck = audienciaRepository.existsByLocalAndDataHoraLessThanEqualAndDataHoraFimGreaterThanEqual(
                audiencia.getLocal(),
                audiencia.getDataHoraFim(),
                audiencia.getDataHora()
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
