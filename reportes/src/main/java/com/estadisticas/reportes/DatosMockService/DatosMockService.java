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

        IncidenciaDto i1 = new IncidenciaDto();
        i1.setId(1L);
        i1.setTitulo("Bache grande");
        i1.setDescripcion("Bache en avenida principal");
        i1.setTipoIncidenciaId(1L);
        i1.setEstado("ABIERTA");
        i1.setFechaReporte(LocalDateTime.now().minusDays(4));
        i1.setUsuarioId(1L);
        i1.setUbicacionId(1L);
        i1.setDepartamentoId(1L);
        i1.setPersonalId(1L);

        IncidenciaDto i2 = new IncidenciaDto();
        i2.setId(2L);
        i2.setTitulo("Bache mediano");
        i2.setDescripcion("Bache frente a escuela");
        i2.setTipoIncidenciaId(1L);
        i2.setEstado("RESUELTA");
        i2.setFechaReporte(LocalDateTime.now().minusDays(3));
        i2.setUsuarioId(2L);
        i2.setUbicacionId(1L);
        i2.setDepartamentoId(1L);
        i2.setPersonalId(1L);

        IncidenciaDto i3 = new IncidenciaDto();
        i3.setId(3L);
        i3.setTitulo("Lámpara apagada");
        i3.setDescripcion("No funciona alumbrado");
        i3.setTipoIncidenciaId(2L);
        i3.setEstado("RESUELTA");
        i3.setFechaReporte(LocalDateTime.now().minusDays(2));
        i3.setUsuarioId(3L);
        i3.setUbicacionId(2L);
        i3.setDepartamentoId(1L);
        i3.setPersonalId(1L);

        IncidenciaDto i4 = new IncidenciaDto();
        i4.setId(4L);
        i4.setTitulo("Fuga de agua");
        i4.setDescripcion("Fuga constante");
        i4.setTipoIncidenciaId(3L);
        i4.setEstado("ABIERTA");
        i4.setFechaReporte(LocalDateTime.now().minusDays(1));
        i4.setUsuarioId(4L);
        i4.setUbicacionId(3L);
        i4.setDepartamentoId(1L);
        i4.setPersonalId(1L);

        IncidenciaDto i5 = new IncidenciaDto();
        i5.setId(5L);
        i5.setTitulo("Otro bache");
        i5.setDescripcion("Bache profundo");
        i5.setTipoIncidenciaId(1L);
        i5.setEstado("RESUELTA");
        i5.setFechaReporte(LocalDateTime.now().minusHours(30));
        i5.setUsuarioId(5L);
        i5.setUbicacionId(2L);
        i5.setDepartamentoId(1L);
        i5.setPersonalId(1L);

        return List.of(i1, i2, i3, i4, i5);
    }

    public List<HistorialEstadoDto> obtenerHistorial() {
        return List.of(
                new HistorialEstadoDto(1L, 2L, "RESUELTA", LocalDateTime.now().minusHours(24), "Se reparó", 10L),
                new HistorialEstadoDto(2L, 3L, "RESUELTA", LocalDateTime.now().minusHours(10), "Se cambió luminaria", 11L),
                new HistorialEstadoDto(3L, 5L, "RESUELTA", LocalDateTime.now().minusHours(5), "Se tapó el bache", 12L)
        );
    }
}