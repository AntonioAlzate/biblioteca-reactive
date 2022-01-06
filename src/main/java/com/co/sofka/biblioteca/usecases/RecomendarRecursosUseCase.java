package com.co.sofka.biblioteca.usecases;

import com.co.sofka.biblioteca.dtos.RecursoDTO;
import com.co.sofka.biblioteca.mappers.MapperUtils;
import com.co.sofka.biblioteca.repositories.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

@Service
@Validated
public class RecomendarRecursosUseCase implements BiFunction<Optional<String>, Optional<String> ,Flux<RecursoDTO>> {

    private final RecursoRepository repository;
    private final MapperUtils mapperUtils;

    public RecomendarRecursosUseCase(RecursoRepository repository, MapperUtils mapperUtils) {
        this.repository = repository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Flux<RecursoDTO> apply(Optional<String> tipo, Optional<String> tematica) {
        String tipoSanetizado = tipo.orElse("");
        String tematicaSanetizado = tematica.orElse("");

        return repository.findAllByTipoAndAndTematica(tipoSanetizado, tematicaSanetizado)
                .map(recurso -> mapperUtils.mapToRecursoDTO().apply(recurso));
    }
}
