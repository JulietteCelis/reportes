package com.estadisticas.reportes.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TipoIncidenciaDto {
	
	 private Long id;
	    private String nombre;
	    private String prioridadBase;

}
