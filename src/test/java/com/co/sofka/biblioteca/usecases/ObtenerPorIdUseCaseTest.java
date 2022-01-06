package com.co.sofka.biblioteca.usecases;

import com.co.sofka.biblioteca.collections.Recurso;
import com.co.sofka.biblioteca.mappers.MapperUtils;
import com.co.sofka.biblioteca.repositories.RecursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

public class ObtenerPorIdUseCaseTest {

    RecursoRepository repository;
    ObtenerPorIdUseCase obtenerPorIdUseCase;

    @BeforeEach
    public void setup() {
        MapperUtils mapperUtils = new MapperUtils();
        repository = mock(RecursoRepository.class);
        obtenerPorIdUseCase = new ObtenerPorIdUseCase(repository, mapperUtils);
    }

    @Test
    void obtenerPorIdValidar(){
        String id = "1";
        Recurso recurso = new Recurso();
        recurso.setId(id);
        recurso.setTipo("libro");
        recurso.setTematica("carros");
        recurso.setEstaDisponible(true);
        when(repository.findById(id)).thenReturn(Mono.just(recurso));

        StepVerifier.create(obtenerPorIdUseCase.apply(id))
                .expectNextMatches(recursoDTO -> {
                    assert recursoDTO.getId().equals(id);
                    assert recursoDTO.getTipo().equals("libro");
                    assert recursoDTO.getTematica().equals("carros");
                    assert recursoDTO.getDisponibilidad().equals(true);
                    return true;
                }).verifyComplete();

        verify(repository).findById(id);
    }
}
