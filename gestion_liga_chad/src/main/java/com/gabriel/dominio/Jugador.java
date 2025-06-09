package com.gabriel.dominio;

public class Jugador {
    private String nombre;
    private int edad;
    private int cantGoles;

    public Jugador() {
        this.nombre = "";
        this.edad = 0;
        this.cantGoles = 0;
    }

    public Jugador(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.cantGoles = 0;
    }

    public Jugador(String nombre, int edad, int cantGoles) {
        this.nombre = nombre;
        this.edad = edad;
        this.cantGoles = cantGoles;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantGoles() {
        return this.cantGoles;
    }

    public void setCantGoles(int cantGoles) {
        this.cantGoles = cantGoles;
    }
}
