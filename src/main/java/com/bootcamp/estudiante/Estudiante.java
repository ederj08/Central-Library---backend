package com.bootcamp.estudiante;

import com.bootcamp.prestamo.Prestamo;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_estudiante")
    @SequenceGenerator(name = "sequence_estudiante", sequenceName = "sequence_estudiante", allocationSize = 50)
    @Column(name = "id_estudiante")
    private Long id;

    @Embedded
    private Nombre nombre;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "estudiante")
    @JsonIgnore
    private List<Prestamo> prestamos = new ArrayList<>();

    public Estudiante() {}

    public Long getId() { return id; }

    public Nombre getNombre() { return nombre; }
    public void setNombre(Nombre nombre) { this.nombre = nombre; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Prestamo> getPrestamos() { return prestamos; }
}