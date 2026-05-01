package com.bootcamp.prestamo;

import com.bootcamp.libro.Libro;
import com.bootcamp.libro.LibroRepository;
import com.bootcamp.estudiante.Estudiante;
import com.bootcamp.estudiante.EstudianteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;
    private final EstudianteRepository estudianteRepository;

    public PrestamoService(PrestamoRepository prestamoRepository,
                           LibroRepository libroRepository,
                           EstudianteRepository estudianteRepository) {
        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
        this.estudianteRepository = estudianteRepository;
    }

    // GET todos los préstamos
    @Transactional(readOnly = true)
    public List<PrestamoDTO> getAllPrestamos() {
        return prestamoRepository.findAll()
                .stream()
                .map(PrestamoDTO::new)
                .collect(Collectors.toList());
    }

    // GET préstamos activos
    @Transactional(readOnly = true)
    public List<PrestamoDTO> getPrestamosActivos() {
        return prestamoRepository.findByDevueltoFalse()
                .stream()
                .map(PrestamoDTO::new)
                .collect(Collectors.toList());
    }

    // GET préstamos por estudiante
    @Transactional(readOnly = true)
    public List<PrestamoDTO> getPrestamosPorEstudiante(Long estudianteId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new NoSuchElementException("Estudiante no encontrado"));
        return prestamoRepository.findByEstudiante(estudiante)
                .stream()
                .map(PrestamoDTO::new)
                .collect(Collectors.toList());
    }

    // POST prestar libro
    @Transactional
    public PrestamoDTO prestarLibro(Long libroId, Long estudianteId) {
        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new NoSuchElementException("Libro no encontrado"));

        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new NoSuchElementException("Estudiante no encontrado"));

        if (prestamoRepository.existsByLibroAndDevueltoFalse(libro)) {
            throw new IllegalArgumentException("El libro ya está prestado");
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setLibro(libro);
        prestamo.setEstudiante(estudiante);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucion(LocalDate.now().plusDays(7));
        prestamo.setDevuelto(false);

        return new PrestamoDTO(prestamoRepository.save(prestamo));
    }

    // PUT devolver libro
    @Transactional
    public PrestamoDTO devolverLibro(Long prestamoId) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new NoSuchElementException("Préstamo no encontrado"));

        if (prestamo.isDevuelto()) {
            throw new IllegalArgumentException("Este libro ya fue devuelto");
        }

        prestamo.setDevuelto(true);
        prestamo.setFechaDevolucion(LocalDate.now());

        return new PrestamoDTO(prestamoRepository.save(prestamo));
    }
}