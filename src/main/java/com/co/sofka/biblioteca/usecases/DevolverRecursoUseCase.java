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
public class DevolverRecursoUseCase implements Function<String, Mono<String>> {

    private static final String RECURSO_DEVUELTO = "Recurso Devuelto correctamente";
    private static final String RECURSO_NO_PRESTADO = "El recurso no se encuentra prestado, por lo tanto no se puede devolver";
    private final RecursoRepository repository;
    private final MapperUtils mapperUtils;
    private final ActualizarDevolucionUseCase actualizarDevolucionUseCase;

    public DevolverRecursoUseCase(RecursoRepository repository, MapperUtils mapperUtils, ActualizarDevolucionUseCase actualizarDevolucionUseCase) {
        this.repository = repository;
        this.mapperUtils = mapperUtils;
        this.actualizarDevolucionUseCase = actualizarDevolucionUseCase;
    }

    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "El id del recurso es requerido");

        Mono<Recurso> recursoMono = repository.findById(id);

        Mono<Boolean> estaDisponible = recursoMono
                .flatMap(recurso -> Mono.just(recurso.getEstaDisponible()));

        Objects.requireNonNull(estaDisponible);


        return estaDisponible.flatMap(disponible -> {
            if (Boolean.FALSE.equals(disponible)) {
                return actualizarDevolucionUseCase.apply(recursoMono)
                        .flatMap(x -> Mono.just(RECURSO_DEVUELTO));
            }

            return Mono.just(RECURSO_NO_PRESTADO);
        });
    }
}