package com.co.sofka.biblioteca.mappers;

import com.co.sofka.biblioteca.collections.Recurso;
import com.co.sofka.biblioteca.dtos.RecursoDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtils {

    public Function<RecursoDTO, Recurso> mapToRecurso(){
        return recursoDTO -> {
            Recurso recurso = new Recurso();
            recurso.setId(recursoDTO.getId());
            recurso.setTipo(recursoDTO.getTipo());
            recurso.setTematica(recursoDTO.getTematica());
            recurso.setEstaDisponible(recursoDTO.getDisponibilidad());
            recurso.setFechaPrestamo(recursoDTO.getFechaPrestamo());
            return recurso;
        };
    }

    public Function<Recurso, RecursoDTO> mapToRecursoDTO(){
        return recurso -> new RecursoDTO(
                recurso.getId(),
                recurso.getTipo(),
                recurso.getTematica(),
                recurso.getFechaPrestamo(),
                recurso.getEstaDisponible()
        );
    }
}
