package com.estadisticas.reportes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estadisticas.reportes.Dto.ReporteGeneralDto;
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
    public List<ReporteGeneralDto> obtenerZonasConMasBaches() {
        return reporteService.obtenerZonasConMasBaches();
    }

    @GetMapping("/incidencias-por-colonia")
    public List<ReporteGeneralDto> obtenerIncidenciasPorColonia() {
        return reporteService.obtenerIncidenciasPorColonia();
    }
}