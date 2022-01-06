package com.co.sofka.biblioteca.usecases;

import com.co.sofka.biblioteca.collections.Recurso;
import com.co.sofka.biblioteca.mappers.MapperUtils;
import com.co.sofka.biblioteca.repositories.RecursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ObtenerRecursosUseCaseTest {

    RecursoRepository repository;
    ObtenerRecursosUseCase obtenerRecursosUseCase;

    @BeforeEach
    public void setup(){
        MapperUtils mapperUtils = new MapperUtils();
        repository = mock(RecursoRepository.class);
        obtenerRecursosUseCase = new ObtenerRecursosUseCase(repository, mapperUtils);
    }

    @Test
    void obtenerRecursosValidar(){
        Recurso recurso = new Recurso();
        recurso.setId("1");
        recurso.setTipo("libro");
        recurso.setTematica("carros");
        recurso.setEstaDisponible(true);
        when(repository.findAll()).thenReturn(Flux.just(recurso));

        StepVerifier.create(obtenerRecursosUseCase.get())
                .expectNextMatches(recursoDTO -> {
                    assert recursoDTO.getId().equals("1");
                    assert recursoDTO.getTipo().equals("libro");
                    assert recursoDTO.getTematica().equals("carros");
                    assert recursoDTO.getDisponibilidad().equals(true);
                    return true;
                }).verifyComplete();

        verify(repository).findAll();
    }

}