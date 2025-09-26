package com.tj.projetoTJ.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.tj.projetoTJ.model.enums.StatusProcesso;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroProcesso;

    private String vara;

    private String comarca;

    private String assunto;

    @Enumerated(EnumType.STRING)
    private StatusProcesso status;


}
