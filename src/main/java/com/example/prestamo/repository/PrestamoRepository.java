package com.example.prestamo.repository;
import com.example.prestamo.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    // Listar todos los préstamos: ya lo trae findAll()

    // Spring genera la consulta a partir del nombre del método
    List<Prestamo> findByHerramientaId(Long herramientaId);
}