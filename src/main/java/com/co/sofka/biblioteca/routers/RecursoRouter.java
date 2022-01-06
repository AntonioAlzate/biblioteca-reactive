package com.co.sofka.biblioteca.routers;

import com.co.sofka.biblioteca.dtos.RecursoDTO;
import com.co.sofka.biblioteca.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.net.URI;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RecursoRouter {

    @Bean
    public RouterFunction<ServerResponse> crearRecurso(CrearRecursoUseCase crearRecursoUseCase) {

        return route(
                POST("/crear").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RecursoDTO.class)
                        .flatMap(recursoDTO -> crearRecursoUseCase.apply(recursoDTO)
                                .flatMap(result -> ServerResponse.created(URI.create("/recurso/" + result.getId()))
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result)))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> obtenerRecursos(ObtenerRecursosUseCase obtenerRecursosUseCase) {
        return route(GET("/recursos"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(obtenerRecursosUseCase.get(), RecursoDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> obtenerPorId(ObtenerPorIdUseCase obtenerPorIdUseCase) {
        return route(GET("/recurso/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                obtenerPorIdUseCase.apply(request.pathVariable("id")),
                                RecursoDTO.class)
                        )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> actualizarRecurso(ActualizarRecursoUseCase actualizarRecursoUseCase){
        return route(PUT("/recurso/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RecursoDTO.class)
                        .flatMap(recursoDTO -> actualizarRecursoUseCase.apply(recursoDTO))
                        .flatMap(result -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> eleminarRecurso(EliminarRecursoUseCase eliminarRecursoUseCase){
        return route(DELETE("/recurso/{id}"),
                request -> ServerResponse.noContent()
                        .build(eliminarRecursoUseCase.apply(request.pathVariable("id")))
        );
    }

}
