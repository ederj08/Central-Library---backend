package com.bootcamp.estudiante;

import com.bootcamp.libro.Libro;
import com.bootcamp.libro.LibroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final LibroRepository libroRepository;

    public EstudianteService(EstudianteRepository estudianteRepository, LibroRepository libroRepository) {
        this.estudianteRepository = estudianteRepository;
        this.libroRepository = libroRepository;
    }

    // GET todos
    @Transactional(readOnly = true)
    public Page<Estudiante> getEstudiantes(Pageable pageable) {
        return estudianteRepository.findAll(pageable);
    }

    // GET uno
    @Transactional(readOnly = true)
    public Estudiante getEstudiante(Long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Estudiante con id " + id + " no existe"));
    }

    // POST crear
    @Transactional
    public Long crearEstudiante(Estudiante estudiante) {
        if (estudianteRepository.existsByEmail(estudiante.getEmail())) {
            throw new IllegalArgumentException("Ya existe un estudiante con ese email");
        }
        return estudianteRepository.save(estudiante).getId();
    }

    // PUT actualizar
    @Transactional
    public Estudiante actualizarEstudiante(Long id, Estudiante datos) {
        Estudiante existente = estudianteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Estudiante con id " + id + " no existe"));

        if (estudianteRepository.existsByEmailAndIdIsNot(datos.getEmail(), id)) {
            throw new IllegalArgumentException("Ya existe otro estudiante con ese email");
        }

        existente.setNombre(datos.getNombre());
        existente.setFechaNacimiento(datos.getFechaNacimiento());
        existente.setEmail(datos.getEmail());

        return estudianteRepository.save(existente);
    }

    // DELETE
    @Transactional
    public void eliminarEstudiante(Long id) {
        if (!estudianteRepository.existsById(id)) {
            throw new NoSuchElementException("Estudiante con id " + id + " no existe");
        }
        estudianteRepository.deleteById(id);
    }

    // PUT asignar libro
    @Transactional
    public Estudiante asignarLibro(Long idEstudiante, Long idLibro) {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new NoSuchElementException("Estudiante no encontrado"));
        Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(() -> new NoSuchElementException("Libro no encontrado"));

        libro.setEstudiante(estudiante);
        libroRepository.save(libro);
        return estudiante;
    }

    // DELETE quitar libro
    @Transactional
    public Estudiante eliminarLibro(Long idEstudiante, Long idLibro) {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new NoSuchElementException("Estudiante no encontrado"));
        Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(() -> new NoSuchElementException("Libro no encontrado"));

        libro.setEstudiante(null);
        libroRepository.save(libro);
        return estudiante;
    }
}