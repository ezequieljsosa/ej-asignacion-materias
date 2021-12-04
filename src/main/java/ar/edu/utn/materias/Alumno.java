package ar.edu.utn.materias;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class Alumno {


    private String nombre;
    private String apellido;

    private LocalDate ingreso;
    private Collection<Curso> cursadas;
    private Collection<Acta> actas;

    public Alumno() {
    }

    public Alumno(String nombre, String apellido, LocalDate ingreso) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.ingreso = ingreso;
        this.cursadas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getIngreso() {
        return ingreso;
    }

    public void setIngreso(LocalDate ingreso) {
        this.ingreso = ingreso;
    }

    public Collection<Curso> getCursadas() {
        return  new ArrayList<>(cursadas) ;
    }

    public void anotarEn(Curso curso){
        //TODO Validar que se puede anotar
        this.cursadas.add(curso);
    }


    public void registrarActa(Acta acta){
        //TODO Validar que se puede anotar
        this.actas.add(acta);
    }

}
