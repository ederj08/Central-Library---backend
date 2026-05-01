package com.bootcamp.estudiante;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;

import java.util.Objects;

@Embeddable
public class Nombre {
    @Column(name = "primer_nombre",length = 300,nullable = false)

    private String primerNombre;

    @Column (name = "segundoNombre")
    private String segundoNombre;

    @Column(name = "primerApellido")
    private String primeroApellido;

    @Column(name = "segundoApellido")
    private String segundoApellido;

    public Nombre() {
    }


    public Nombre(String primerNombre, String segundoNombre, String primeroApellido, String segundoApellido) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primeroApellido = primeroApellido;
        this.segundoApellido = segundoApellido;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimeroApellido() {
        return primeroApellido;
    }

    public void setPrimeroApellido(String primeroApellido) {
        this.primeroApellido = primeroApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Nombre nombre = (Nombre) o;
        return Objects.equals(primerNombre, nombre.primerNombre) && Objects.equals(segundoNombre, nombre.segundoNombre) && Objects.equals(primeroApellido, nombre.primeroApellido) && Objects.equals(segundoApellido, nombre.segundoApellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primerNombre, segundoNombre, primeroApellido, segundoApellido);
    }

    @Override
    public String toString() {
        return "Nombre{" +
                "primerNombre='" + primerNombre + '\'' +
                ", segundoNombre='" + segundoNombre + '\'' +
                ", primeroApellido='" + primeroApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                '}';
    }
}

