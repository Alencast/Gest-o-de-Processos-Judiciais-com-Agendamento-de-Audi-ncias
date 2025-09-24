package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.TipoAudiencia;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public static class Audiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "processo_id")
    private Processo processo;

    private LocalDateTime dataHora;

    private TipoAudiencia tipoAudiencia;

    private LocalDateTime dataHoraFim;
}