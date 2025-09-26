package com.tj.projetoTJ.repository;

import com.tj.projetoTJ.model.Processo;
import com.tj.projetoTJ.model.enums.StatusProcesso;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {

    //  métodos de filtro por status e/ou comarca, número de processo é o plus
    List<Processo> findByStatus(StatusProcesso status);
    List<Processo> findByComarca(String comarca);
    List<Processo> findByStatusAndComarca(StatusProcesso status, String comarca);
    Optional<Processo> findByNumeroProcesso(String numeroProcesso);
}
