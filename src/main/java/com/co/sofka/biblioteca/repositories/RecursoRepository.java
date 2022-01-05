package com.co.sofka.biblioteca.repositories;

import com.co.sofka.biblioteca.collections.Recurso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecursoRepository extends ReactiveMongoRepository<Recurso, String>{
}
