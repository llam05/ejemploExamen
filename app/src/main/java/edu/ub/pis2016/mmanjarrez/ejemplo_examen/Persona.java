package edu.ub.pis2016.mmanjarrez.ejemplo_examen;

import java.io.Serializable;

/**
 * Created by orla_ on 03/06/2016.
 */
public class Persona implements Serializable{
    private String nombre, apellido, edad;

    public Persona(String nombre, String apellido, String edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEdad() {
        return edad;
    }

    @Override
    public String toString() {
        return  "n=" + nombre +
                ", a=" + apellido +
                ", =" + edad;
    }
}
