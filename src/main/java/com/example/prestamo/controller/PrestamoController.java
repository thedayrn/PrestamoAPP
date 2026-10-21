package com.example.prestamo.controller;
import com.example.prestamo.dto.PrestamoRequest;
import com.example.prestamo.model.Prestamo;
import com.example.prestamo.service.PrestamoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService service;

    public PrestamoController(PrestamoService service) {
        this.service = service;
    }

    @PostMapping
    public Prestamo registrar(@RequestBody PrestamoRequest request) {
        return service.registrar(request);
    }

    @GetMapping
    public List<Prestamo> listar() {
        return service.listar();
    }

    @GetMapping("/herramienta/{id}")
    public List<Prestamo> listarPorHerramienta(@PathVariable Long id) {
        return service.listarPorHerramienta(id);
    }
}