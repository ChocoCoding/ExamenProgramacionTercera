package boletin.ej7;

import java.io.Serializable;

public class Alumno implements Serializable {
    private String nombre;
    private String apellidos;
    private int edad;

    public Alumno() {
        this.nombre = "nombre";
        this.apellidos = "apellidos";
        this.edad = 0;
    }

    public Alumno(String nombre, String apellidos, int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Alumno{");
        sb.append("nombre='").append(nombre).append('\'');
        sb.append("apellidos='").append(apellidos).append('\'');
        sb.append("edad=").append(edad).append('\'');;
        return sb.toString();
    }
}
