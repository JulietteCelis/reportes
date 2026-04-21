package com.estadisticas.reportes.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.estadisticas.reportes.Dto.HistorialEstadoDto;

@Component
public class HistorialClient {

    private final RestClient restClient;

    public HistorialClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://localhost:8080")
                .build();
    }

    public List<HistorialEstadoDto> obtenerHistorial() {
        return restClient.get()
                .uri("/api/historial-estados")
                .retrieve()
                .body(new ParameterizedTypeReference<List<HistorialEstadoDto>>() {});
    }
}