package com.estadisticas.reportes.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidenciaDto {

    private Long id;
    private String titulo;
    private String descripcion;
    private Long tipoIncidenciaId;
    private LocalDateTime fechaReporte;
    private Long usuarioId;
    private Long ubicacionId;
    private Long departamentoId;
    private Long personalId;
    private String estado;
    private String alertaClima;
}