package com.gabriel.dominio;

public class JugadorSuplente extends Jugador {
    private int cantPartidosIngresados;

    public JugadorSuplente(String nombre, int edad, int cantGoles, int cantPartidosIngresados) {
        super(nombre, edad, cantGoles);
        this.cantPartidosIngresados = cantPartidosIngresados;
    }

    public JugadorSuplente(String nombre, int edad, int cantGoles) {
        super(nombre, edad, cantGoles);
        this.cantPartidosIngresados = 0;
    }

    public JugadorSuplente() {
        super();
        this.cantPartidosIngresados = 0;
    }

    public int getCantPartidosIngresados() {
        return this.cantPartidosIngresados;
    }

    public void setCantPartidosIngresados(int cantPartidosIngresados) {
        this.cantPartidosIngresados = cantPartidosIngresados;
    }
}
