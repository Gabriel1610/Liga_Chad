package com.gabriel.dominio;

import java.util.ArrayList;

public class Equipo {
    private String nombre;
    private ArrayList<JugadorTitular> jugadores_titulares;
    private ArrayList<JugadorSuplente> jugadores_suplentes;

    public Equipo(){
        this.nombre = "";
        this.jugadores_suplentes = new ArrayList<JugadorSuplente>();
        this.jugadores_titulares = new ArrayList<JugadorTitular>();
    }

    public Equipo(String nombre){
        this.nombre = nombre;
        this.jugadores_suplentes = new ArrayList<JugadorSuplente>();
        this.jugadores_titulares = new ArrayList<JugadorTitular>();
    }

    public Equipo(String nombre, ArrayList<JugadorSuplente> jugadores_suplentes, ArrayList<JugadorTitular> jugadores_titulares){
        this.nombre = nombre;
        this.jugadores_suplentes = jugadores_suplentes;
        this.jugadores_titulares = new ArrayList<JugadorTitular>();
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public ArrayList<JugadorTitular> getJugadoresTitulares() {
        return this.jugadores_titulares;
    }

    public void setJugadoresTitulares(ArrayList<JugadorTitular> jugadores_titulares) {
        this.jugadores_titulares = jugadores_titulares;
    }

    public ArrayList<JugadorSuplente> getJugadoresSuplentes() {
        return this.jugadores_suplentes;
    }

    public void setJugadoresSuplentes(ArrayList<JugadorSuplente> jugadores_suplentes) {
        this.jugadores_suplentes = jugadores_suplentes;
    }


}