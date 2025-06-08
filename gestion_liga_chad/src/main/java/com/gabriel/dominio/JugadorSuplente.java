package com.gabriel.dominio;

public class JugadorSuplente extends Jugador{
    private int cant_partidos_ingresados;

    public JugadorSuplente(String nombre, int edad, int cant_goles, int cant_partidos_ingresados){
        super(nombre, edad, cant_goles);
        this.cant_partidos_ingresados = cant_partidos_ingresados;
    }

    public JugadorSuplente(String nombre, int edad, int cant_goles){
        super(nombre, edad, cant_goles);
        this.cant_partidos_ingresados = 0;
    }

    public JugadorSuplente(){
        super();
        this.cant_partidos_ingresados = 0;
    }

    public int getCantPartidosIngresados(){
        return this.cant_partidos_ingresados;
    }

    public void setCantPartidosIngresados(int cant_partidos_ingresados){
        this.cant_partidos_ingresados = cant_partidos_ingresados;
    }
}
