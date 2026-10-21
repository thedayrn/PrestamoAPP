package com.example.prestamo.dto;

import java.time.LocalDate;

public class PrestamoRequest {

    private Long herramientaId;
    private String responsable;
    private LocalDate fechaPrestamo;
    private int cantidad;

    public PrestamoRequest() {
    }

    public Long getHerramientaId() {
        return herramientaId;
    }

    public void setHerramientaId(Long herramientaId) {
        this.herramientaId = herramientaId;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

