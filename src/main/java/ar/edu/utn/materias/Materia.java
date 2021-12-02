package ar.edu.utn.materias;

import java.util.List;

public class Materia {

    private String nombre;
    private String descripcion;
    private List<Materia> correlativas;

//    public Materia(){
//
//    }

    public Materia(String nombre) {
        this.nombre = nombre;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



}
