package com.example.prestamo.controller;
import com.example.prestamo.model.Herramienta;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.prestamo.service.HerramientaService;
@RestController
@RequestMapping("/herramientas")
public class HerramientaController {

    private final HerramientaService service;

    public HerramientaController(HerramientaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Herramienta> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Herramienta buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Herramienta crear(@RequestBody Herramienta herramienta) {
        return service.guardar(herramienta);
    }

    @PutMapping("/{id}")
    public Herramienta actualizar(@PathVariable Long id, @RequestBody Herramienta herramienta) {
        herramienta.setId(id);
        return service.guardar(herramienta);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}