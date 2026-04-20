package com.estadisticas.reportes.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UbicacionResponseDto {

    private Long id;
    private Double latitud;
    private Double longitud;
    private String direccion;
    private String colonia;
    private String ciudad;
}