package com.co.sofka.biblioteca.repositories;

import com.co.sofka.biblioteca.collections.Recurso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface RecursoRepository extends ReactiveMongoRepository<Recurso, String>{
    Flux<Recurso> findAllByTipoAndAndTematica(String tipo, String tematica);
}
