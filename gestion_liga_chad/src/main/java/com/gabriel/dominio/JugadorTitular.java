package com.gabriel.dominio;

public class JugadorTitular extends Jugador{
    private int minutos_jugados;

    public JugadorTitular(String nombre, int edad, int cant_goles, int minutos_jugados){
        super(nombre, edad, cant_goles);
        this.minutos_jugados = minutos_jugados;
    }

    public JugadorTitular(String nombre, int edad, int cant_goles){
        super(nombre, edad, cant_goles);
        this.minutos_jugados = 0;
    }

    public JugadorTitular(){
        super();
        this.minutos_jugados = 0;
    }

    public int getMinutosJugados(){
        return this.minutos_jugados;
    }

    public void setMinutosJugados(int minutos_jugados){
        this.minutos_jugados = minutos_jugados;
    }
    
}
