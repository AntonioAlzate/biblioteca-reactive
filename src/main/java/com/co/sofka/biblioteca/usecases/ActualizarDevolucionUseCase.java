package com.co.sofka.biblioteca.usecases;

import com.co.sofka.biblioteca.collections.Recurso;
import com.co.sofka.biblioteca.repositories.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.function.Function;

@Service
@Validated
public class ActualizarDevolucionUseCase implements Function<Mono<Recurso>, Mono<Recurso>> {

    private final RecursoRepository repository;

    public ActualizarDevolucionUseCase(RecursoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Recurso> apply(Mono<Recurso> recursoMono) {
        return recursoMono.flatMap(recurso -> {
            recurso.setEstaDisponible(true);
            return Mono.just(recurso);
        }).flatMap(recurso -> repository.save(recurso));
    }
}
