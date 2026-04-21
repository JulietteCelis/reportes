package com.estadisticas.reportes.Dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteGeneralDto {

    // =========================
    // DATOS DE INCIDENCIA
    // =========================
    private Long incidenciaId;
    private String titulo;
    private String descripcion;
    private Long tipoIncidenciaId;
    private String estado;
    private String alertaClima;
    private LocalDateTime fechaReporte;
    private Long usuarioId;

    // =========================
    // DATOS DE UBICACION
    // =========================
    private Long ubicacionId;
    private Double latitud;
    private Double longitud;
    private String direccion;
    private String colonia;
    private String ciudad;

    // =========================
    // DATOS DE GESTION INSTITUCIONAL
    // =========================

    // Departamento
    private Long departamentoId;
    private String departamentoNombre;
    private String departamentoDescripcion;

    // Cuadrilla
    private Long cuadrillaId;
    private String cuadrillaNombre;

    // Puesto
    private Long puestoId;
    private String puestoNombre;
    private List<String> permisos;

    // Personal
    private Long personalId;
    private String personalNombre;
    private String personalEmail;
    private String personalTelefono;
    private Boolean personalDisponible;
}