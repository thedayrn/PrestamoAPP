package com.example.prestamo.service;
import com.example.prestamo.model.Herramienta;
import java.util.List;

public interface HerramientaService {

    List<Herramienta> listar();

    Herramienta buscarPorId(Long id);

    Herramienta guardar(Herramienta herramienta);

    void eliminar(Long id);
}