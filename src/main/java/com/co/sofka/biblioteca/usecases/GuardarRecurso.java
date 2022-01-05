package com.co.sofka.biblioteca.usecases;

import com.co.sofka.biblioteca.dtos.RecursoDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface GuardarRecurso {

    Mono<RecursoDTO> apply(@Valid RecursoDTO recursoDTO);
}
