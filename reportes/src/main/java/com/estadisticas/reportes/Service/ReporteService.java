package com.estadisticas.reportes.Service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.estadisticas.reportes.DatosMockService.DatosMockService;
import com.estadisticas.reportes.Dto.ColoniaDto;
import com.estadisticas.reportes.Dto.HistorialEstadoDto;
import com.estadisticas.reportes.Dto.IncidenciaDto;
import com.estadisticas.reportes.Dto.IncidenciasPorColoniaDto;
import com.estadisticas.reportes.Dto.TipoIncidenciaDto;
import com.estadisticas.reportes.Dto.TiempoPromedioResolucionDto;
import com.estadisticas.reportes.Dto.UbicacionDto;
import com.estadisticas.reportes.Dto.ZonaBacheReporteDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReporteService {

    private final DatosMockService datosMockService;

    public List<ZonaBacheReporteDto> obtenerZonasConMasBaches() {
        List<IncidenciaDto> incidencias = datosMockService.obtenerIncidencias();
        List<TipoIncidenciaDto> tipos = datosMockService.obtenerTiposIncidencia();
        List<UbicacionDto> ubicaciones = datosMockService.obtenerUbicaciones();
        List<ColoniaDto> colonias = datosMockService.obtenerColonias();

        Set<Long> tiposBache = tipos.stream()
                .filter(tipo -> "BACHE".equalsIgnoreCase(tipo.getNombre()))
                .map(TipoIncidenciaDto::getId)
                .collect(Collectors.toSet());

        Map<Long, Long> ubicacionColoniaMap = ubicaciones.stream()
                .collect(Collectors.toMap(UbicacionDto::getId, UbicacionDto::getColoniaId));

        Map<Long, String> coloniaNombreMap = colonias.stream()
                .collect(Collectors.toMap(ColoniaDto::getId, ColoniaDto::getNombre));

        Map<String, Long> conteoPorColonia = incidencias.stream()
                .filter(incidencia -> tiposBache.contains(incidencia.getTipoIncidenciaId()))
                .map(incidencia -> ubicacionColoniaMap.get(incidencia.getUbicacionId()))
                .filter(Objects::nonNull)
                .map(coloniaNombreMap::get)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(nombre -> nombre, Collectors.counting()));

        return conteoPorColonia.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .map(entry -> new ZonaBacheReporteDto(entry.getKey(), entry.getValue()))
                .toList();
    }

    public List<IncidenciasPorColoniaDto> obtenerIncidenciasPorColonia() {
        List<IncidenciaDto> incidencias = datosMockService.obtenerIncidencias();
        List<UbicacionDto> ubicaciones = datosMockService.obtenerUbicaciones();
        List<ColoniaDto> colonias = datosMockService.obtenerColonias();

        Map<Long, Long> ubicacionColoniaMap = ubicaciones.stream()
                .collect(Collectors.toMap(UbicacionDto::getId, UbicacionDto::getColoniaId));

        Map<Long, String> coloniaNombreMap = colonias.stream()
                .collect(Collectors.toMap(ColoniaDto::getId, ColoniaDto::getNombre));

        Map<String, Long> conteoPorColonia = incidencias.stream()
                .map(incidencia -> ubicacionColoniaMap.get(incidencia.getUbicacionId()))
                .filter(Objects::nonNull)
                .map(coloniaNombreMap::get)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(nombre -> nombre, Collectors.counting()));

        return conteoPorColonia.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .map(entry -> new IncidenciasPorColoniaDto(entry.getKey(), entry.getValue()))
                .toList();
    }

    public TiempoPromedioResolucionDto obtenerTiempoPromedioResolucion() {
        List<IncidenciaDto> incidencias = datosMockService.obtenerIncidencias();
        List<HistorialEstadoDto> historial = datosMockService.obtenerHistorial();

        Map<Long, IncidenciaDto> incidenciasMap = incidencias.stream()
                .collect(Collectors.toMap(IncidenciaDto::getId, incidencia -> incidencia));

        List<Long> horas = historial.stream()
                .filter(h -> "RESUELTA".equalsIgnoreCase(h.getEstadoNuevo()))
                .map(h -> {
                    IncidenciaDto incidencia = incidenciasMap.get(h.getIncidenciaId());

                    if (incidencia == null || incidencia.getFechaReporte() == null || h.getFechaCambio() == null) {
                        return null;
                    }

                    return Duration.between(incidencia.getFechaReporte(), h.getFechaCambio()).toHours();
                })
                .filter(Objects::nonNull)
                .toList();

        double promedio = horas.stream()
                .mapToLong(Long::longValue)
                .average()
                .orElse(0.0);

        return new TiempoPromedioResolucionDto(promedio, (long) horas.size());
    }
}