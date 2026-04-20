package com.estadisticas.reportes.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.estadisticas.reportes.Dto.IncidenciaDto;

@Component
public class IncidenciasClient {

    private final RestClient restClient;

    public IncidenciasClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://localhost:7070") // URL del microservicio de incidencias
                .build();
    }

    public List<IncidenciaDto> obtenerIncidencias() {
        return restClient.get()
                .uri("/api/incidencias")
                .retrieve()
                .body(new ParameterizedTypeReference<List<IncidenciaDto>>() {});
    }
}