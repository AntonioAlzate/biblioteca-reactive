package com.co.sofka.biblioteca.usecases;

import com.co.sofka.biblioteca.dtos.RecursoDTO;
import com.co.sofka.biblioteca.mappers.MapperUtils;
import com.co.sofka.biblioteca.repositories.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CrearRecursoUseCase implements GuardarRecurso{

    private final RecursoRepository repository;
    private final MapperUtils mapperUtils;

    public CrearRecursoUseCase(RecursoRepository repository, MapperUtils mapperUtils) {
        this.repository = repository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<RecursoDTO> apply(RecursoDTO recursoDTO) {

        return repository
                .save(mapperUtils.mapToRecurso().apply(recursoDTO))
                .map(recurso -> mapperUtils.mapToRecursoDTO().apply(recurso));
    }
}
