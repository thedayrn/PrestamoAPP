package com.example.prestamo.service.impl;

import com.example.prestamo.dto.PrestamoRequest;
import com.example.prestamo.model.Herramienta;
import com.example.prestamo.model.Prestamo;
import com.example.prestamo.repository.HerramientaRepository;
import com.example.prestamo.repository.PrestamoRepository;
import com.example.prestamo.service.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final HerramientaRepository herramientaRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository,
                               HerramientaRepository herramientaRepository) {
        this.prestamoRepository = prestamoRepository;
        this.herramientaRepository = herramientaRepository;
    }

    // Si algo falla, se deshace todo: no se descuenta stock ni se guarda el préstamo
    @Override
    @Transactional
    public Prestamo registrar(PrestamoRequest request) {

        if (request.getHerramientaId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debes enviar herramientaId");
        }
        if (request.getResponsable() == null || request.getResponsable().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El responsable es obligatorio");
        }
        if (request.getCantidad() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cantidad debe ser mayor que 0");
        }

        // 1. Validar que la herramienta exista
        Herramienta herramienta = herramientaRepository.findById(request.getHerramientaId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "La herramienta no existe"));

        // 2. Validar que el stock alcance
        if (request.getCantidad() > herramienta.getCantidad()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Stock insuficiente. Disponible: " + herramienta.getCantidad());
        }

        // 3. Descontar unidades
        herramienta.setCantidad(herramienta.getCantidad() - request.getCantidad());
        herramientaRepository.save(herramienta);

        // 4. Guardar el préstamo
        Prestamo prestamo = new Prestamo();
        prestamo.setHerramienta(herramienta);
        prestamo.setResponsable(request.getResponsable());
        prestamo.setCantidad(request.getCantidad());
        prestamo.setFechaPrestamo(
                request.getFechaPrestamo() != null ? request.getFechaPrestamo() : LocalDate.now());

        return prestamoRepository.save(prestamo);
    }

    @Override
    public List<Prestamo> listar() {
        return prestamoRepository.findAll();
    }

    @Override
    public List<Prestamo> listarPorHerramienta(Long herramientaId) {
        return prestamoRepository.findByHerramientaId(herramientaId);
    }
}
