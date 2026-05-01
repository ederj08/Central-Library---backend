package com.bootcamp.libro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    @Autowired
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // ✅ GET all
    @GetMapping
    public Page<LibroDTO> getLibros(Pageable pageable){

        return libroService.getLibros(pageable);
    }

    // ✅ GET one
    @GetMapping("/{libroId}")
    public Libro getLibro(@PathVariable Long libroId) {
        return libroService.getLibro(libroId);
    }

    // ✅ POST
    @PostMapping
    public Long createLibro(@Valid @RequestBody Libro libro) {
        return libroService.createLibro(libro);
    }

    // ✅ PUT
    @PutMapping("{libroId}")
    public Libro updateLibro(@PathVariable Long libroId, @RequestBody Libro libroActualizado) {
        return libroService.updateLibro(libroId, libroActualizado);
    }

    // ✅ DELETE
    @DeleteMapping("{libroId}")
    public void deleteLibro(@PathVariable Long libroId) {
        libroService.deleteLibro(libroId);
    }
}
