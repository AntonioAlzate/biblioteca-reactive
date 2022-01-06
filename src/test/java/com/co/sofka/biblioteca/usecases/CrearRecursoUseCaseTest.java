package com.co.sofka.biblioteca.usecases;

import com.co.sofka.biblioteca.collections.Recurso;
import com.co.sofka.biblioteca.dtos.RecursoDTO;
import com.co.sofka.biblioteca.mappers.MapperUtils;
import com.co.sofka.biblioteca.repositories.RecursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CrearRecursoUseCaseTest {

    RecursoRepository repository;
    CrearRecursoUseCase crearRecursoUseCase;

    @BeforeEach
    public void setup(){
        MapperUtils mapperUtils = new MapperUtils();
        repository = mock(RecursoRepository.class);
        crearRecursoUseCase = new CrearRecursoUseCase(repository, mapperUtils);
    }

    @Test
    void crearRecursoValidacion(){
        String id = "1";
        Recurso recurso = new Recurso();
        recurso.setId("1");
        recurso.setTipo("libro");
        recurso.setTematica("carros");
        recurso.setEstaDisponible(true);

        RecursoDTO recursoDTOResult =
                new RecursoDTO("1", "libro", "carros", null, true);

        when(repository.save(any())).thenReturn(Mono.just(recurso));

        StepVerifier.create(crearRecursoUseCase.apply(recursoDTOResult))
                .expectNextMatches(recursoDTO -> {
                    assert recursoDTO.getId().equals(id);
                    assert recursoDTO.getTipo().equals("libro");
                    assert recursoDTO.getTematica().equals("carros");
                    assert recursoDTO.getDisponibilidad().equals(true);
                    return true;
                }).verifyComplete();

        verify(repository).save(any());
    }

}
