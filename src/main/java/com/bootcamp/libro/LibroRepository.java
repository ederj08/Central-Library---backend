package com.bootcamp.libro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Validar si ya existe un libro con ese título
    boolean existsByTitulo(String titulo);

    // Validar si existe ese título pero con otro ID (para actualizaciones)
    boolean existsByTituloAndIdIsNot(String titulo, Long id);

    // Buscar libro por ISBN
    Optional<Libro> findByIsbn(String isbn);

    // Buscar libros por título (útil para búsquedas)
    List<Libro> findByTituloContainingIgnoreCase(String titulo);

}