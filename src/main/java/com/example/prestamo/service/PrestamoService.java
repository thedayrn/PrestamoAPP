package com.example.prestamo.service;
import com.example.prestamo.dto.PrestamoRequest;
import com.example.prestamo.model.Prestamo;
import java.util.List;

public interface PrestamoService {

    Prestamo registrar(PrestamoRequest request);

    List<Prestamo> listar();

    List<Prestamo> listarPorHerramienta(Long herramientaId);
}