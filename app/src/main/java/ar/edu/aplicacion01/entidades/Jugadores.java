package ar.edu.aplicacion01.entidades;

public class Jugadores {
    private int id;
    private String cont_victorias;
    private String cont_derrotas;
    private String cont_empates;
    private String nombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCont_victorias() {
        return cont_victorias;
    }

    public void setCont_victorias(String cont_victorias) {
        this.cont_victorias = cont_victorias;
    }

    public String getCont_derrotas() {
        return cont_derrotas;
    }

    public void setCont_derrotas(String cont_derrotas) {
        this.cont_derrotas = cont_derrotas;
    }

    public String getCont_empates() {
        return cont_empates;
    }

    public void setCont_empates(String cont_empates) {
        this.cont_empates = cont_empates;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
