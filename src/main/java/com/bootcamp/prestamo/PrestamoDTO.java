package com.bootcamp.prestamo;

import java.time.LocalDate;

public class PrestamoDTO {

    private Long id;
    private Long libroId;
    private String tituloLibro;
    private String autorLibro;
    private Long estudianteId;
    private String nombreEstudiante;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private boolean devuelto;

    public PrestamoDTO(Prestamo p) {
        this.id = p.getId();
        this.devuelto = p.isDevuelto();
        this.fechaPrestamo = p.getFechaPrestamo();
        this.fechaDevolucion = p.getFechaDevolucion();

        if (p.getLibro() != null) {
            this.libroId = p.getLibro().getId();
            this.tituloLibro = p.getLibro().getTitulo();
            this.autorLibro = p.getLibro().getAutor();
        }

        if (p.getEstudiante() != null) {
            this.estudianteId = p.getEstudiante().getId();
            var nombre = p.getEstudiante().getNombre();
            if (nombre != null) {
                this.nombreEstudiante = String.join(" ",
                        nombre.getPrimerNombre(),
                        nombre.getPrimeroApellido()
                );
            }
        }
    }

    public Long getId() { return id; }
    public Long getLibroId() { return libroId; }
    public String getTituloLibro() { return tituloLibro; }
    public String getAutorLibro() { return autorLibro; }
    public Long getEstudianteId() { return estudianteId; }
    public String getNombreEstudiante() { return nombreEstudiante; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public boolean isDevuelto() { return devuelto; }
}