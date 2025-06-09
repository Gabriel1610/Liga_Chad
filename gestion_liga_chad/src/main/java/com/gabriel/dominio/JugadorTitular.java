package com.gabriel.dominio;

public class JugadorTitular extends Jugador {
    private int minutosJugados;

    public JugadorTitular(String nombre, int edad, int cantGoles, int minutosJugados) {
        super(nombre, edad, cantGoles);
        this.minutosJugados = minutosJugados;
    }

    public JugadorTitular(String nombre, int edad, int cantGoles) {
        super(nombre, edad, cantGoles);
        this.minutosJugados = 0;
    }

    public JugadorTitular(String nombre, int edad) {
        super(nombre, edad);
        this.minutosJugados = 0;
    }

    public JugadorTitular() {
        super();
        this.minutosJugados = 0;
    }

    public int getMinutosJugados() {
        return this.minutosJugados;
    }

    public void setMinutosJugados(int minutosJugados) {
        this.minutosJugados = minutosJugados;
    }
}
