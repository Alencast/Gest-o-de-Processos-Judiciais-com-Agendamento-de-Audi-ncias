package com.tj.projetoTJ.service;

import com.tj.projetoTJ.model.Processo;
import com.tj.projetoTJ.model.enums.StatusProcesso;
import com.tj.projetoTJ.repository.ProcessoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessoService {
    private ProcessoRepository processoRepository;

    public ProcessoService(ProcessoRepository processoRepository){
        this.processoRepository = processoRepository;
    }

    public Processo criarProcesso(Processo processo){

        //validação 1: boolean pra verificar se o número de processo é válido e retornar o throw se for
        if(!validarNumeroProcesso(processo.getNumeroProcesso())){
            throw new RuntimeException("Número inválido.");
        }

        //validação 2: validar se o processo é único(o número, no caso)
        validarNumeroUnico(processo.getNumeroProcesso()); //string

        //validação 3: Status obrigatório
        if(processo.getStatus() == null){
            throw new RuntimeException("Status obrigatório");
        }

        // validação 4: campos obg
        if(processo.getVara() == null || processo.getComarca() == null || processo.getAssunto()
        == null){
            throw new RuntimeException("Vara, comarca e assunto nulos");
        }

        return processoRepository.save(processo);

    }

    public List<Processo> filtrarProcesso(StatusProcesso status, String comarca){

        if(status != null && comarca != null){
            return processoRepository.findByStatusAndComarca(status,comarca);
        }
        else if(status != null){
            return processoRepository.findByStatus(status);
        }
        else if(comarca != null){
            return processoRepository.findByComarca(comarca);
        }
        else{
            return processoRepository.findAll();
        }
    }

    // regex pro: 0000000-00.0000.0.00.0000
    private boolean validarNumeroProcesso(String numeroProcesso) {
        String regex = "\\d{7}-\\d{2}\\.\\d{4}\\.\\d\\.\\d{2}\\.\\d{4}";
        return numeroProcesso != null && numeroProcesso.matches(regex);
    }

    private void validarNumeroUnico(String numeroProcesso) {
        if (processoRepository.findByNumeroProcesso(numeroProcesso).isPresent()) {
            throw new RuntimeException("Número de processo já existe");
        }
    }



}
