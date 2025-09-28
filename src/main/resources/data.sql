INSERT INTO processo (id, numero_processo, vara, comarca, assunto, status) VALUES
                                                                               (1, '0000001-01.2023.1.01.0001', 'Vara Cível', 'Natal', 'Divórcio', 'ATIVO'),
                                                                               (2, '0000002-02.2023.1.01.0002', 'Vara Criminal', 'Natal', 'Roubo', 'ATIVO');

INSERT INTO audiencia (id, processo_id, data_hora, data_hora_fim, tipo_audiencia, local) VALUES
                                                                                             (1, 1, '2025-09-26T09:00:00', '2025-09-26T10:00:00', 'CONCILIACAO', 'Sala 101'),
                                                                                             (2, 2, '2025-09-26T11:00:00', '2025-09-26T12:00:00', 'INSTRUCAO', 'Sala 102');
