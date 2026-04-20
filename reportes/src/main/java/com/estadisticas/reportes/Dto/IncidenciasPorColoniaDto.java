package com.estadisticas.reportes.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidenciasPorColoniaDto {
	
	  private String colonia;
	    private Long totalIncidencias;

}
