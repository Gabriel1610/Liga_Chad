package com.gabriel.dominio;

public class Cambio {
    private JugadorTitular jugadorTitular;
    private JugadorSuplente jugadorSuplente;
    private int minuto;

    public Cambio() {
        this.jugadorTitular = null;
        this.jugadorSuplente = null;
        this.minuto = -1;
    }

    public Cambio(JugadorTitular jugadorTitular, JugadorSuplente jugadorSuplente, int minuto) {
        this.jugadorTitular = jugadorTitular;
        this.jugadorSuplente = jugadorSuplente;
        this.minuto = minuto;
    }

    public JugadorTitular getJugadorTitular() {
        return jugadorTitular;
    }

    public void setJugadorTitular(JugadorTitular jugadorTitular) {
        this.jugadorTitular = jugadorTitular;
    }

    public JugadorSuplente getJugadorSuplente() {
        return jugadorSuplente;
    }

    public void setJugadorSuplente(JugadorSuplente jugadorSuplente) {
        this.jugadorSuplente = jugadorSuplente;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }
    
}
