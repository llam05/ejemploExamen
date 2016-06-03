package edu.ub.pis2016.mmanjarrez.ejemplo_examen;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by orla_ on 03/06/2016.
 */
public class listaPersonas implements Serializable {

    private ArrayList<Persona> personas;

    public listaPersonas() {

        this.personas = new ArrayList<Persona>();
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public void agregaPersona (String nom, String apellido, String edad) {
        personas.add(new Persona(nom, apellido, edad));
    }

    @Override
    public String toString() {
        String todos = "";
        for (int i=0; i<personas.size(); i++) {
            todos += i+" "+personas.get(i)+"\n";
        }
        return todos;
    }
}
