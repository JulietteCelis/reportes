package com.estadisticas.reportes.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteGeneralDto {

    private Long incidenciaId;
    private String titulo;
    private String descripcion;
    private Long tipoIncidenciaId;
    private String estado;
    private String alertaClima;
    private LocalDateTime fechaReporte;

    private Long usuarioId;

    private Long ubicacionId;
    private Double latitud;
    private Double longitud;
    private String direccion;
    private Long coloniaId;
    private String colonia;
    private String ciudad;

    private Long departamentoId;
    private String departamentoNombre;

    private Long cuadrillaId;
    private String cuadrillaNombre;

    private Long puestoId;
    private String puestoNombre;

    private Long personalId;
    private String personalNombre;
    private String personalEmail;
    private String personalTelefono;
    private Boolean personalDisponible;
}