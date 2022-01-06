package com.co.sofka.biblioteca.usecases;

import com.co.sofka.biblioteca.mappers.MapperUtils;
import com.co.sofka.biblioteca.repositories.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class EliminarRecursoUseCase implements Function<String, Mono<Void>> {

    private final RecursoRepository repository;
    private final MapperUtils mapperUtils;

    public EliminarRecursoUseCase(RecursoRepository repository, MapperUtils mapperUtils) {
        this.repository = repository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<Void> apply(String id) {
        return repository.deleteById(id);
    }
}
