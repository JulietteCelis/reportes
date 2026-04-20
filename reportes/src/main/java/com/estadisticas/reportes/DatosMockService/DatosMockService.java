package com.estadisticas.reportes.DatosMockService;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.estadisticas.reportes.Dto.ColoniaDto;
import com.estadisticas.reportes.Dto.HistorialEstadoDto;
import com.estadisticas.reportes.Dto.IncidenciaDto;
import com.estadisticas.reportes.Dto.TipoIncidenciaDto;
import com.estadisticas.reportes.Dto.UbicacionDto;

@Service

public class DatosMockService {
	

    public List<TipoIncidenciaDto> obtenerTiposIncidencia() {
        return List.of(
                new TipoIncidenciaDto(1L, "BACHE", "ALTA"),
                new TipoIncidenciaDto(2L, "ALUMBRADO", "MEDIA"),
                new TipoIncidenciaDto(3L, "FUGA", "ALTA")
        );
    }

    public List<ColoniaDto> obtenerColonias() {
        return List.of(
                new ColoniaDto(1L, "Centro", 1L),
                new ColoniaDto(2L, "San Mateo", 1L),
                new ColoniaDto(3L, "Morelos", 1L)
        );
    }

    public List<UbicacionDto> obtenerUbicaciones() {
        return List.of(
                new UbicacionDto(1L, 17.55, -99.50, "Calle Juárez", 1L),
                new UbicacionDto(2L, 17.56, -99.51, "Av. Guerrero", 2L),
                new UbicacionDto(3L, 17.57, -99.52, "Calle Hidalgo", 3L)
        );
    }

    public List<IncidenciaDto> obtenerIncidencias() {
        return List.of(
                new IncidenciaDto(1L, "Bache grande", "Bache en avenida principal", 1L, "ABIERTA",
                        LocalDateTime.now().minusDays(4), 1L, 1L, 1L, 1L),

                new IncidenciaDto(2L, "Bache mediano", "Bache frente a escuela", 1L, "RESUELTA",
                        LocalDateTime.now().minusDays(3), 2L, 1L, 1L, 1L),

                new IncidenciaDto(3L, "Lámpara apagada", "No funciona alumbrado", 2L, "RESUELTA",
                        LocalDateTime.now().minusDays(2), 3L, 2L, 1L, 1L),

                new IncidenciaDto(4L, "Fuga de agua", "Fuga constante", 3L, "ABIERTA",
                        LocalDateTime.now().minusDays(1), 4L, 3L, 1L, 1L),

                new IncidenciaDto(5L, "Otro bache", "Bache profundo", 1L, "RESUELTA",
                        LocalDateTime.now().minusHours(30), 5L, 2L, 1L, 1L)
        );
    }

    public List<HistorialEstadoDto> obtenerHistorial() {
        return List.of(
                new HistorialEstadoDto(1L, 2L, "RESUELTA", LocalDateTime.now().minusHours(24), "Se reparó", 10L),
                new HistorialEstadoDto(2L, 3L, "RESUELTA", LocalDateTime.now().minusHours(10), "Se cambió luminaria", 11L),
                new HistorialEstadoDto(3L, 5L, "RESUELTA", LocalDateTime.now().minusHours(5), "Se tapó el bache", 12L)
        );
    }

}
