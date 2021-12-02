package ar.edu.utn.materias;

public class Curso {

    private Materia materia;
    private int anio;
    private int cuatrimestr;
    private TipoMateria tipoMateria;

//    public Curso(){
//
//    }

    public Curso(Materia materia, int anio, TipoMateria tipoMateria) {
        this.materia = materia;
        this.anio = anio;
        this.tipoMateria = tipoMateria;
        this.cuatrimestr = 0;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getCuatrimestr() {
        return cuatrimestr;
    }

    public void setCuatrimestr(int cuatrimestr) {
        this.cuatrimestr = cuatrimestr;
    }

    public TipoMateria getTipoMateria() {
        return tipoMateria;
    }

    public void setTipoMateria(TipoMateria tipoMateria) {
        this.tipoMateria = tipoMateria;
    }
}
