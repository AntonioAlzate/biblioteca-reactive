package com.co.sofka.biblioteca.dtos;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class RecursoDTO {

    private String id;

    @NotBlank
    private String tipo;

    @NotBlank
    private String tematica;

    private LocalDateTime fechaPrestamo;

    private Boolean disponibilidad;

    public RecursoDTO() {
    }

    public RecursoDTO(String id, String tipo, String tematica, LocalDateTime fechaPrestamo, Boolean disponibilidad) {
        this.id = id;
        this.tipo = tipo;
        this.tematica = tematica;
        this.fechaPrestamo = fechaPrestamo;
        this.disponibilidad = disponibilidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public LocalDateTime getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDateTime fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
