package com.co.sofka.biblioteca.usecases;

import com.co.sofka.biblioteca.collections.Recurso;
import com.co.sofka.biblioteca.mappers.MapperUtils;
import com.co.sofka.biblioteca.repositories.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class PrestarRecursoUseCase implements Function<String, Mono<String>> {

    private static final String RECURSO_PRESTADO = "Recurso prestado correctamente";
    private static final String RECURSO_NO_DISPONIBLE = "El recurso no se encuentra disponible";
    private final RecursoRepository repository;
    private final MapperUtils mapperUtils;
    private final ActualizarPrestamoUseCase actualizarPrestamoUseCase;

    public PrestarRecursoUseCase(RecursoRepository repository, MapperUtils mapperUtils, ActualizarPrestamoUseCase actualizarPrestamoUseCase) {
        this.repository = repository;
        this.mapperUtils = mapperUtils;
        this.actualizarPrestamoUseCase = actualizarPrestamoUseCase;
    }

    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "El id del recurso es requerido");

        Mono<Recurso> recursoMono = repository.findById(id);

        Mono<Boolean> estaDisponible = recursoMono
                .flatMap(recurso -> Mono.just(recurso.getEstaDisponible()));

        Objects.requireNonNull(estaDisponible);


        return estaDisponible.flatMap(disponible -> {
            if (Boolean.TRUE.equals(disponible)) {
                return actualizarPrestamoUseCase.apply(recursoMono)
                        .flatMap(x -> Mono.just(RECURSO_PRESTADO));
            }

            return Mono.just(RECURSO_NO_DISPONIBLE);
        });
    }
}
