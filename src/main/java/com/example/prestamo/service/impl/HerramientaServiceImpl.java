package com.example.prestamo.service.impl;
import com.example.prestamo.model.Herramienta;
import com.example.prestamo.repository.HerramientaRepository;
import com.example.prestamo.service.HerramientaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HerramientaServiceImpl implements HerramientaService {

    private final HerramientaRepository repository;

    public HerramientaServiceImpl(HerramientaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Herramienta> listar() {
        return repository.findAll();
    }

    @Override
    public Herramienta buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Herramienta guardar(Herramienta herramienta) {
        return repository.save(herramienta);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}