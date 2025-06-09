package com.gabriel.dominio;

import java.util.ArrayList;

public class Equipo {
    private String nombre;
    private ArrayList<JugadorTitular> jugadoresTitulares;
    private ArrayList<JugadorSuplente> jugadoresSuplentes;

    public Equipo() {
        this.nombre = "";
        this.jugadoresSuplentes = new ArrayList<JugadorSuplente>();
        this.jugadoresTitulares = new ArrayList<JugadorTitular>();
    }

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.jugadoresSuplentes = new ArrayList<JugadorSuplente>();
        this.jugadoresTitulares = new ArrayList<JugadorTitular>();
    }

    public Equipo(String nombre, ArrayList<JugadorSuplente> jugadoresSuplentes, ArrayList<JugadorTitular> jugadoresTitulares) {
        this.nombre = nombre;
        this.jugadoresSuplentes = jugadoresSuplentes;
        this.jugadoresTitulares = jugadoresTitulares;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<JugadorTitular> getJugadoresTitulares() {
        return this.jugadoresTitulares;
    }

    public void setJugadoresTitulares(ArrayList<JugadorTitular> jugadoresTitulares) {
        this.jugadoresTitulares = jugadoresTitulares;
    }

    public ArrayList<JugadorSuplente> getJugadoresSuplentes() {
        return this.jugadoresSuplentes;
    }

    public void setJugadoresSuplentes(ArrayList<JugadorSuplente> jugadoresSuplentes) {
        this.jugadoresSuplentes = jugadoresSuplentes;
    }
}