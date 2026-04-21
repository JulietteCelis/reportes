package com.estadisticas.reportes.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.estadisticas.reportes.Dto.UbicacionResponseDto;

@Component
public class UbicacionClient {

    private final RestClient restClient;

    public UbicacionClient(
            @Qualifier("loadBalancedRestClientBuilder") RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://UBICACION")
                .build();
    }

    public UbicacionResponseDto obtenerUbicacionPorId(Long id) {
        return restClient.get()
                .uri("/api/ubicaciones/{id}", id)
                .retrieve()
                .body(UbicacionResponseDto.class);
    }
}