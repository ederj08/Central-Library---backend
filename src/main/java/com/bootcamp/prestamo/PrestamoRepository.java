package com.bootcamp.prestamo;

import com.bootcamp.libro.Libro;
import com.bootcamp.estudiante.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    List<Prestamo> findByDevueltoFalse();

    boolean existsByLibroAndDevueltoFalse(Libro libro);

    Optional<Prestamo> findByLibroAndDevueltoFalse(Libro libro);

    List<Prestamo> findByEstudiante(Estudiante estudiante); //
}