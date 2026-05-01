package com.bootcamp.estudiante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/estudiantes")
@PreAuthorize("hasAnyRole('COOR','ADMIN','BIBL')")
public class EstudianteController {

    private final EstudianteService estudianteService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    // GET todos
    @GetMapping
    public Page<Estudiante> getEstudiantes(Pageable pageable) {
        return estudianteService.getEstudiantes(pageable);
    }

    // GET uno
    @GetMapping("/{id}")
    public Estudiante getEstudiante(@PathVariable Long id) {
        return estudianteService.getEstudiante(id);
    }

    // POST crear
    @PostMapping
    public Long crearEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteService.crearEstudiante(estudiante);
    }

    // PUT actualizar
    @PutMapping("/{id}")
    public Estudiante actualizarEstudiante(@PathVariable Long id, @RequestBody Estudiante datos) {
        return estudianteService.actualizarEstudiante(id, datos);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminarEstudiante(id);
    }

    // PUT asignar libro
    @PutMapping("/{idEstudiante}/libros/{idLibro}")
    public Estudiante asignarLibro(@PathVariable Long idEstudiante, @PathVariable Long idLibro) {
        return estudianteService.asignarLibro(idEstudiante, idLibro);
    }

    // DELETE quitar libro
    @DeleteMapping("/{idEstudiante}/libros/{idLibro}")
    public Estudiante eliminarLibro(@PathVariable Long idEstudiante, @PathVariable Long idLibro) {
        return estudianteService.eliminarLibro(idEstudiante, idLibro);
    }
}