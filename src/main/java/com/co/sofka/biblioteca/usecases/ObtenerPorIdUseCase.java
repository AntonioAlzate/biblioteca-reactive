package com.co.sofka.biblioteca.usecases;

import com.co.sofka.biblioteca.dtos.RecursoDTO;
import com.co.sofka.biblioteca.mappers.MapperUtils;
import com.co.sofka.biblioteca.repositories.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class ObtenerPorIdUseCase implements Function<String, Mono<RecursoDTO>> {

    private final RecursoRepository repository;
    private final MapperUtils mapperUtils;

    public ObtenerPorIdUseCase(RecursoRepository repository, MapperUtils mapperUtils) {
        this.repository = repository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<RecursoDTO> apply(String id) {
        return repository.findById(id).map(recurso ->
                mapperUtils.mapToRecursoDTO().apply(recurso));
    }
}
