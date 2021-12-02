package ar.edu.utn.materias;

public class Acta {

    private Curso curso;
    private Alumno alumno;
    private int nota;

    public Acta(Curso curso, Alumno alumno, int nota) {
        this.curso = curso;
        this.alumno = alumno;
        this.nota = nota;
    }

    public Curso getCurso() {
        return curso;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public int getNota() {
        return nota;
    }
}
