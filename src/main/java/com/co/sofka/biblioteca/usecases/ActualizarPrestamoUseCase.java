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
public class ActualizarPrestamoUseCase implements Function<Mono<Recurso>, Mono<Recurso>> {

    private final RecursoRepository repository;

    public ActualizarPrestamoUseCase(RecursoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Recurso> apply(Mono<Recurso> recursoMono) {

        return recursoMono.flatMap(recurso -> {
            recurso.setEstaDisponible(false);
            recurso.setFechaPrestamo(LocalDateTime.now());
            return Mono.just(recurso);
        }).flatMap(recurso -> repository.save(recurso));
    }
}
