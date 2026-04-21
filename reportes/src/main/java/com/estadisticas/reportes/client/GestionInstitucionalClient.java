package com.estadisticas.reportes.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.estadisticas.reportes.Dto.ReporteGeneralDto;

@Component
public class GestionInstitucionalClient {

    private final RestClient restClient;

    public GestionInstitucionalClient(
            @Qualifier("loadBalancedRestClientBuilder") RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://SERVICIO-GESTION-INSTITUCIONAL")
                .build();
    }

    public ReporteGeneralDto obtenerPersonalPorId(Long id) {
        return restClient.get()
                .uri("/api/personal/{id}", id)
                .retrieve()
                .body(ReporteGeneralDto.class);
    }
}