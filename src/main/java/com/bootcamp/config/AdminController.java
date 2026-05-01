package com.bootcamp.config;

import com.bootcamp.libro.LibroRepository;
import com.bootcamp.estudiante.EstudianteRepository;
import com.bootcamp.prestamo.Prestamo;
import com.bootcamp.prestamo.PrestamoRepository;
import com.bootcamp.prestamo.PrestamoDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final LibroRepository libroRepository;
    private final EstudianteRepository estudianteRepository;
    private final PrestamoRepository prestamoRepository;

    public AdminController(LibroRepository libroRepository,
                           EstudianteRepository estudianteRepository,
                           PrestamoRepository prestamoRepository) {
        this.libroRepository = libroRepository;
        this.estudianteRepository = estudianteRepository;
        this.prestamoRepository = prestamoRepository;
    }

    // GET estadísticas generales
    @GetMapping("/estadisticas")
    public Map<String, Object> getEstadisticas() {
        long totalLibros = libroRepository.count();
        long totalEstudiantes = estudianteRepository.count();
        long prestamosActivos = prestamoRepository.findByDevueltoFalse().size();
        long prestamosVencidos = prestamoRepository.findByDevueltoFalse()
                .stream()
                .filter(p -> p.getFechaDevolucion() != null &&
                        p.getFechaDevolucion().isBefore(LocalDate.now()))
                .count();

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalLibros", totalLibros);
        stats.put("totalEstudiantes", totalEstudiantes);
        stats.put("prestamosActivos", prestamosActivos);
        stats.put("prestamosVencidos", prestamosVencidos);
        return stats;
    }

    // GET préstamos activos
    @GetMapping("/prestamos-activos")
    public List<PrestamoDTO> getPrestamosActivos() {
        return prestamoRepository.findByDevueltoFalse()
                .stream()
                .map(PrestamoDTO::new)
                .collect(Collectors.toList());
    }

    // GET préstamos vencidos
    @GetMapping("/prestamos-vencidos")
    public List<Map<String, Object>> getPrestamosVencidos() {
        return prestamoRepository.findByDevueltoFalse()
                .stream()
                .filter(p -> p.getFechaDevolucion() != null &&
                        p.getFechaDevolucion().isBefore(LocalDate.now()))
                .map(p -> {
                    Map<String, Object> item = new HashMap<>();
                    item.put("id", p.getId());
                    item.put("tituloLibro", p.getLibro().getTitulo());
                    item.put("autorLibro", p.getLibro().getAutor());
                    item.put("fechaDevolucion", p.getFechaDevolucion());
                    item.put("diasRetraso", LocalDate.now().toEpochDay() -
                            p.getFechaDevolucion().toEpochDay());
                    var nombre = p.getEstudiante().getNombre();
                    String nombreEstudiante = nombre != null ?
                            nombre.getPrimerNombre() + " " + nombre.getPrimeroApellido() : "Sin nombre";
                    item.put("nombreEstudiante", nombreEstudiante);
                    item.put("estudianteId", p.getEstudiante().getId());
                    return item;
                })
                .collect(Collectors.toList());
    }

    // GET libros más prestados
    @GetMapping("/libros-mas-prestados")
    public List<Map<String, Object>> getLibrosMasPrestados() {
        return prestamoRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        p -> p.getLibro().getId(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<Long, Long>comparingByValue().reversed())
                .limit(5)
                .map(entry -> {
                    Prestamo p = prestamoRepository.findAll()
                            .stream()
                            .filter(pr -> pr.getLibro().getId().equals(entry.getKey()))
                            .findFirst().get();
                    Map<String, Object> item = new HashMap<>();
                    item.put("libroId", entry.getKey());
                    item.put("titulo", p.getLibro().getTitulo());
                    item.put("autor", p.getLibro().getAutor());
                    item.put("totalPrestamos", entry.getValue());
                    return item;
                })
                .collect(Collectors.toList());
    }
}