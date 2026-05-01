package com.bootcamp.libro;

public class LibroDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponible;
    private String estudianteNombre;
    private Long estudianteId;

    public LibroDTO(Libro libro, boolean disponible, String estudianteNombre, Long estudianteId) {
        this.id = libro.getId();
        this.titulo = libro.getTitulo();
        this.autor = libro.getAutor();
        this.isbn = libro.getIsbn();
        this.disponible = disponible;
        this.estudianteNombre = estudianteNombre;
        this.estudianteId = estudianteId;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public boolean isDisponible() { return disponible; }
    public String getEstudianteNombre() { return estudianteNombre; }
    public Long getEstudianteId() { return estudianteId; }
}