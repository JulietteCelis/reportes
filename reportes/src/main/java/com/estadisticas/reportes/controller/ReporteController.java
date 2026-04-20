package com.estadisticas.reportes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estadisticas.reportes.Dto.IncidenciasPorColoniaDto;
import com.estadisticas.reportes.Dto.TiempoPromedioResolucionDto;
import com.estadisticas.reportes.Dto.ZonaBacheReporteDto;
import com.estadisticas.reportes.Service.ReporteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {	

    private final ReporteService reporteService;

    @GetMapping("/ping")
    public String ping() {
        return "Microservicio de reportes funcionando";
    }

    @GetMapping("/zonas-mas-baches")
    public List<ZonaBacheReporteDto> obtenerZonasConMasBaches() {
        return reporteService.obtenerZonasConMasBaches();
    }

    @GetMapping("/incidencias-por-colonia")
    public List<IncidenciasPorColoniaDto> obtenerIncidenciasPorColonia() {
        return reporteService.obtenerIncidenciasPorColonia();
    }

    @GetMapping("/tiempo-promedio-resolucion")
    public TiempoPromedioResolucionDto obtenerTiempoPromedioResolucion() {
        return reporteService.obtenerTiempoPromedioResolucion();
    }
}