package com.bootcamp.libro;

import com.bootcamp.estudiante.Estudiante;
import com.bootcamp.prestamo.Prestamo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    private String titulo;

    @NotBlank
    private String autor;

    @NotBlank
    @Column(unique = true)
    private  String isbn;

    // 📚 RELACIÓN MANY TO ONE (muchos libros pueden pertenecer a un estudiante)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "estudiante_id",
            referencedColumnName = "id_estudiante"
    )
    @JsonIgnore
    private Estudiante estudiante;

    // 📚 RELACIÓN ONE TO MANY (un libro puede tener muchos prestamos en el tiempo)
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Prestamo> prestamos = new ArrayList<>();

    // 🔹 Constructor vacío obligatorio para JPA
    public Libro() {
    }

    // 🔹 Constructor con datos
    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    // 🔹 GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    // 🔹 equals y hashCode (buena práctica con entidades JPA)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro)) return false;
        Libro libro = (Libro) o;
        return id != null && id.equals(libro.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // 🔹 toString

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
}