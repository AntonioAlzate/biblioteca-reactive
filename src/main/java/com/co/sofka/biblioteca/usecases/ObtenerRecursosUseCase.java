package com.co.sofka.biblioteca.usecases;

import com.co.sofka.biblioteca.dtos.RecursoDTO;
import com.co.sofka.biblioteca.mappers.MapperUtils;
import com.co.sofka.biblioteca.repositories.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class ObtenerRecursosUseCase implements Supplier<Flux<RecursoDTO>> {
    private final RecursoRepository repository;
    private final MapperUtils mapperUtils;

    public ObtenerRecursosUseCase(RecursoRepository repository, MapperUtils mapperUtils) {
        this.repository = repository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Flux<RecursoDTO> get() {
        return repository.findAll().map(recurso ->
                mapperUtils.mapToRecursoDTO().apply(recurso));
    }
}
