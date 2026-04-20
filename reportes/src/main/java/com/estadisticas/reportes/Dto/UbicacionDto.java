package com.estadisticas.reportes.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UbicacionDto {
	

    private Long id;
    private Double latitud;
    private Double longitud;
    private String direccion;
    private Long coloniaId;

}
