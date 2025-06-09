package com.gabriel.dominio;

import java.util.ArrayList;
import java.util.Iterator;

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

    public boolean perteneceAlEquipo(Jugador jugador) {
        boolean pertenece = false;
        Iterator<JugadorTitular> jugadorTitular = this.getJugadoresTitulares().iterator();
        Iterator<JugadorSuplente> jugadorSuplente = this.getJugadoresSuplentes().iterator();
        while(!pertenece && jugadorTitular.hasNext()){
            if (jugadorTitular.next().equals(jugador)) {
                pertenece = true;
            }
        }
        while(!pertenece && jugadorSuplente.hasNext()){
            if (jugadorSuplente.next().equals(jugador)) {
                pertenece = true;
            }
        }
        return pertenece;
    }

    public void agregarJugadorTitular(JugadorTitular jugador){
        this.jugadoresTitulares.add(jugador);
    }

    public void borrarJugadorTitular(JugadorTitular jugador){
        this.jugadoresTitulares.remove(jugador);
    }

    public void agregarJugadorSuplente(JugadorSuplente jugador){
        this.jugadoresSuplentes.add(jugador);
    }

    public void borrarJugadorSuplente(JugadorSuplente jugador){
        this.jugadoresSuplentes.remove(jugador);
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