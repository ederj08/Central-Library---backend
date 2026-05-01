package com.bootcamp.prestamo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prestamos")
@PreAuthorize("hasAnyRole('ADMIN','BIBL','COOR')")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    // GET todos
    @GetMapping
    public List<PrestamoDTO> getPrestamos() {
        return prestamoService.getAllPrestamos();
    }

    // GET activos
    @GetMapping("/activos")
    public List<PrestamoDTO> getPrestamosActivos() {
        return prestamoService.getPrestamosActivos();
    }

    // GET por estudiante
    @GetMapping("/estudiante/{estudianteId}")
    public List<PrestamoDTO> getPrestamosPorEstudiante(@PathVariable Long estudianteId) {
        return prestamoService.getPrestamosPorEstudiante(estudianteId);
    }

    // POST prestar
    @PostMapping
    public PrestamoDTO prestarLibro(@RequestParam Long libroId,
                                    @RequestParam Long estudianteId) {
        return prestamoService.prestarLibro(libroId, estudianteId);
    }

    // PUT devolver
    @PutMapping("/{prestamoId}/devolver")
    public PrestamoDTO devolverLibro(@PathVariable Long prestamoId) {
        return prestamoService.devolverLibro(prestamoId);
    }
}