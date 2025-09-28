package com.tj.projetoTJ.repository;

import com.tj.projetoTJ.model.Audiencia;
import com.tj.projetoTJ.model.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AudienciaRepository extends JpaRepository<Audiencia, Long> {

    boolean existsByLocalAndDataHoraBetween(String local, LocalDateTime inicio, LocalDateTime fim);
    List<Audiencia> findByProcesso_ComarcaAndDataHoraBetween(String comarca, LocalDateTime inicio, LocalDateTime fim);
    boolean existsByLocalAndDataHoraLessThanEqualAndDataHoraFimGreaterThanEqual(
            String local, LocalDateTime dataHoraFim, LocalDateTime dataHoraInicio
    );
}


