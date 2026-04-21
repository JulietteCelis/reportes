package com.estadisticas.reportes.Service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.estadisticas.reportes.Dto.IncidenciaDto;
import com.estadisticas.reportes.Dto.ReporteGeneralDto;
import com.estadisticas.reportes.Dto.UbicacionResponseDto;
import com.estadisticas.reportes.client.IncidenciasClient;
import com.estadisticas.reportes.client.UbicacionClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReporteService {

    private final IncidenciasClient incidenciasClient;
    private final UbicacionClient ubicacionClient;

    public List<ReporteGeneralDto> obtenerZonasConMasBaches() {
        List<IncidenciaDto> incidencias = incidenciasClient.obtenerIncidencias();

        return incidencias.stream()
                .filter(Objects::nonNull)
                .map(this::construirReporteGeneral)
                .toList();
    }

    public List<ReporteGeneralDto> obtenerIncidenciasPorColonia() {
        List<IncidenciaDto> incidencias = incidenciasClient.obtenerIncidencias();

        return incidencias.stream()
                .filter(Objects::nonNull)
                .map(this::construirReporteGeneral)
                .toList();
    }

    private ReporteGeneralDto construirReporteGeneral(IncidenciaDto incidencia) {
        UbicacionResponseDto ubicacion = null;

        if (incidencia.getUbicacionId() != null) {
            ubicacion = ubicacionClient.obtenerUbicacionPorId(incidencia.getUbicacionId());
        }

        ReporteGeneralDto dto = new ReporteGeneralDto();

        dto.setIncidenciaId(incidencia.getId());
        dto.setTitulo(incidencia.getTitulo());
        dto.setDescripcion(incidencia.getDescripcion());
        dto.setTipoIncidenciaId(incidencia.getTipoIncidenciaId());
        dto.setEstado(incidencia.getEstado());
        dto.setAlertaClima(incidencia.getAlertaClima());
        dto.setFechaReporte(incidencia.getFechaReporte());
        dto.setUsuarioId(incidencia.getUsuarioId());

        dto.setUbicacionId(incidencia.getUbicacionId());
        dto.setDepartamentoId(incidencia.getDepartamentoId());
        dto.setPersonalId(incidencia.getPersonalId());

        if (ubicacion != null) {
            dto.setLatitud(ubicacion.getLatitud());
            dto.setLongitud(ubicacion.getLongitud());
            dto.setDireccion(ubicacion.getDireccion());
            dto.setColonia(ubicacion.getColonia());
            dto.setCiudad(ubicacion.getCiudad());
        } else {
            dto.setColonia("No disponible");
        }

        return dto;
    }
}