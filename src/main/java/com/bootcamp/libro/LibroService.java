package com.bootcamp.libro;

import com.bootcamp.prestamo.PrestamoRepository;
import com.bootcamp.prestamo.Prestamo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final PrestamoRepository prestamoRepository;

    public LibroService(LibroRepository libroRepository, PrestamoRepository prestamoRepository) {
        this.libroRepository = libroRepository;
        this.prestamoRepository = prestamoRepository;
    }

    // GET todos los libros con estado
    @Transactional(readOnly = true)
    public Page<LibroDTO> getLibros(Pageable pageable) {
        Page<Libro> libros = libroRepository.findAll(pageable);

        List<LibroDTO> dtos = libros.getContent().stream().map(libro -> {
            Optional<Prestamo> prestamoActivo = prestamoRepository.findByLibroAndDevueltoFalse(libro);
            boolean disponible = prestamoActivo.isEmpty();
            String estudianteNombre = null;
            Long estudianteId = null;

            if (prestamoActivo.isPresent()) {
                var estudiante = prestamoActivo.get().getEstudiante();
                estudianteId = estudiante.getId();
                var nombre = estudiante.getNombre();
                if (nombre != null) {
                    estudianteNombre = nombre.getPrimerNombre() + " " + nombre.getPrimeroApellido();
                }
            }

            return new LibroDTO(libro, disponible, estudianteNombre, estudianteId);
        }).collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, libros.getTotalElements());
    }

    // GET libro por id
    @Transactional(readOnly = true)
    public Libro getLibro(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("El libro con id " + id + " no existe"));
    }

    // POST crear libro
    @Transactional
    public Long createLibro(Libro libro) {
        if (libro.getTitulo() == null || libro.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        if (libro.getAutor() == null || libro.getAutor().isBlank()) {
            throw new IllegalArgumentException("El autor no puede estar vacío");
        }
        if (libroRepository.existsByTitulo(libro.getTitulo())) {
            throw new IllegalArgumentException("Ya existe un libro con ese título");
        }
        return libroRepository.save(libro).getId();
    }

    // PUT actualizar libro
    @Transactional
    public Libro updateLibro(Long id, Libro libroActualizado) {
        Libro libroExistente = libroRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No existe un libro con id " + id));

        if (libroRepository.existsByTituloAndIdIsNot(libroActualizado.getTitulo(), id)) {
            throw new IllegalArgumentException("Ya existe otro libro con ese título");
        }

        libroExistente.setTitulo(libroActualizado.getTitulo());
        libroExistente.setAutor(libroActualizado.getAutor());

        return libroRepository.save(libroExistente);
    }

    // DELETE libro
    @Transactional
    public void deleteLibro(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new NoSuchElementException("No existe un libro con id " + id);
        }
        libroRepository.deleteById(id);
    }
}