package com.estadisticas.reportes.Service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.estadisticas.reportes.Dto.HistorialEstadoDto;
import com.estadisticas.reportes.Dto.IncidenciaDto;
import com.estadisticas.reportes.Dto.IncidenciasPorColoniaDto;
import com.estadisticas.reportes.Dto.TiempoPromedioResolucionDto;
import com.estadisticas.reportes.Dto.UbicacionResponseDto;
import com.estadisticas.reportes.Dto.ZonaBacheReporteDto;
import com.estadisticas.reportes.client.HistorialClient;
import com.estadisticas.reportes.client.IncidenciasClient;
import com.estadisticas.reportes.client.UbicacionClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReporteService {

    private final IncidenciasClient incidenciasClient;
    private final UbicacionClient ubicacionClient;
    private final HistorialClient historialClient;

    public List<ZonaBacheReporteDto> obtenerZonasConMasBaches() {
        List<IncidenciaDto> incidencias = incidenciasClient.obtenerIncidencias();

        Map<String, Long> conteoPorColonia = incidencias.stream()
                .filter(i -> i.getTipoIncidenciaId() != null && i.getTipoIncidenciaId().equals(1L))
                .map(i -> ubicacionClient.obtenerUbicacionPorId(i.getUbicacionId()))
                .filter(Objects::nonNull)
                .map(UbicacionResponseDto::getColonia)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(colonia -> colonia, Collectors.counting()));

        return conteoPorColonia.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .map(e -> new ZonaBacheReporteDto(e.getKey(), e.getValue()))
                .toList();
    }

    public List<IncidenciasPorColoniaDto> obtenerIncidenciasPorColonia() {
        List<IncidenciaDto> incidencias = incidenciasClient.obtenerIncidencias();

        Map<String, Long> conteoPorColonia = incidencias.stream()
                .map(i -> ubicacionClient.obtenerUbicacionPorId(i.getUbicacionId()))
                .filter(Objects::nonNull)
                .map(UbicacionResponseDto::getColonia)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(colonia -> colonia, Collectors.counting()));

        return conteoPorColonia.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .map(e -> new IncidenciasPorColoniaDto(e.getKey(), e.getValue()))
                .toList();
    }

    public TiempoPromedioResolucionDto obtenerTiempoPromedioResolucion() {
        List<IncidenciaDto> incidencias = incidenciasClient.obtenerIncidencias();
        List<HistorialEstadoDto> historial = historialClient.obtenerHistorial();

        Map<Long, IncidenciaDto> incidenciasMap = incidencias.stream()
                .collect(Collectors.toMap(IncidenciaDto::getId, i -> i));

        List<Long> horas = historial.stream()
                .filter(h -> "RESUELTO".equalsIgnoreCase(h.getEstado()) || "CERRADO".equalsIgnoreCase(h.getEstado()))
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