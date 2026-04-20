package com.estadisticas.reportes.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialEstadoDto {

    private Long id;
    private Long incidenciaId;
    private String estado;
    private LocalDateTime fechaCambio;
    private String comentarios;
    private Long usuarioModificador;
}