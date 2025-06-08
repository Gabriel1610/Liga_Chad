package com.gabriel.dominio;

public class Jugador
{
    private String nombre;
    private int edad;
    private int cant_goles;

    public Jugador(){
        this.nombre = "";
        this.edad = 0;
        this.cant_goles = 0;
    }

    public Jugador(String nombre, int edad){
        this.nombre = nombre;
        this.edad = edad;
        this.cant_goles = 0;
    }

    public Jugador(String nombre, int edad, int cant_goles){
        this.nombre = nombre;
        this.edad = edad;
        this.cant_goles = cant_goles;
    }

    public int getEdad(){
        return this.edad;
    }

    public void setEdad(int edad){
        this.edad = edad;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public int getCantGoles(){
        return this.cant_goles;
    }

    public void setCantGoles(int cant_goles){
        this.cant_goles = cant_goles;
    }
}
