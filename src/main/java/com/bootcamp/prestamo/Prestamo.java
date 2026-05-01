package com.bootcamp.prestamo;

import com.bootcamp.libro.Libro;
import com.bootcamp.estudiante.Estudiante;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id", nullable = false)
    @JsonIgnore
    private Libro libro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id", nullable = false)
    @JsonIgnore
    private Estudiante estudiante;

    @Column(name = "fecha_prestamo", nullable = false)
    private LocalDate fechaPrestamo;

    @Column(name = "fecha_devolucion")
    private LocalDate fechaDevolucion;

    @Column(name = "devuelto", nullable = false)
    private boolean devuelto;

    public Prestamo() {}

    public Prestamo(Libro libro, Estudiante estudiante,
                    LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        this.libro = libro;
        this.estudiante = estudiante;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = false;
    }

    public void devolverLibro() {
        this.devuelto = true;
        this.fechaDevolucion = LocalDate.now();
    }

    public Long getId() { return id; }

    public Libro getLibro() { return libro; }
    public void setLibro(Libro libro) { this.libro = libro; }

    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }

    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(LocalDate fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }

    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(LocalDate fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }

    public boolean isDevuelto() { return devuelto; }
    public void setDevuelto(boolean devuelto) { this.devuelto = devuelto; }
}