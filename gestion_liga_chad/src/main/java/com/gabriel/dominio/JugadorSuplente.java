package com.gabriel.dominio;

public class JugadorSuplente extends Jugador {
    private int cantPartidosIngresados;

    public JugadorSuplente(String nombre, int edad) {
        super(nombre, edad);
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
