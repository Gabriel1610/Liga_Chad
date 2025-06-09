package com.gabriel.dominio;

import java.util.ArrayList;

public class Liga {
    private ArrayList<Equipo> equipos;
    private ArrayList<Partido> partidos;

    public Liga() {
        this.equipos = new ArrayList<Equipo>();
        this.partidos = new ArrayList<Partido>();
    }

    public Liga(ArrayList<Equipo> equipos, ArrayList<Partido> partidos) {
        this.equipos = equipos;
        this.partidos = partidos;
    }

    public void mostrarJugadores(){
        System.out.printf("%-50s %s\n", "Jugador", "Tipo");
        for(Equipo equipo : this.getEquipos()){
            for(JugadorTitular jugadorTitular : equipo.getJugadoresTitulares()){
                System.out.printf("%-50s %s\n", jugadorTitular.getNombre(), "titular");
            }
            for(JugadorSuplente jugadorSuplente : equipo.getJugadoresSuplentes()){
                System.out.printf("%-50s %s\n", jugadorSuplente.getNombre(), "suplente");
            }
        }
    }

    public Jugador obtenerGoleador(){
        Jugador goleador = null;
        for(Equipo equipo : this.getEquipos()){
            for(JugadorTitular jugadorTitular : equipo.getJugadoresTitulares()){
                if(goleador == null){
                    goleador = jugadorTitular;
                }
                else if(goleador.getCantGoles() < jugadorTitular.getCantGoles()){
                    goleador = jugadorTitular;
                }
            }
            for(JugadorSuplente jugadorSuplente : equipo.getJugadoresSuplentes()){
                if(goleador == null){
                    goleador = jugadorSuplente;
                }
                else if(goleador.getCantGoles() < jugadorSuplente.getCantGoles()){
                    goleador = jugadorSuplente;
                }
            }
        }
        return goleador;
    }

    public void mostrarRanking(){
        int i, cant_equipos_agregados = 0;
        String[] nombres_equipos = new String[this.equipos.size()];
        int[] cant_goles = new int[this.equipos.size()];
        for(Equipo equipo : this.getEquipos()){
            if(cant_equipos_agregados == 0){
                nombres_equipos[0] = equipo.getNombre();
            }
            else{
                i = 0;

            }
        } 
    }

    public void mostrarPromGolesPorEquipo(){
        int cant_partidos, cant_goles;
        double promedio;
        System.out.printf("%-50s %s\n", "Equipo", "Promedio");
        for(Equipo equipo : this.getEquipos()){
            cant_goles = 0;
            cant_partidos = 0;
            for(Partido partido : this.getPartidos()){
                if(partido.getLocal().equals(equipo)){
                    cant_goles += partido.obtenerGolesLocal();
                    cant_partidos++;
                }
                else if(partido.getVisitante().equals(equipo)){
                    cant_goles += partido.obtenerGolesVisitante();
                    cant_partidos++;
                }
            }
            System.out.printf("%-50s", equipo.getNombre());
            if(cant_partidos > 0){
                promedio = cant_goles / cant_partidos;
                System.out.printf("%.2f\n", promedio);
            }
            else{
                System.out.printf("%.2f\n", 0);
            }
        }
    }

    public ArrayList<Equipo> getEquipos() {
        return this.equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    public ArrayList<Partido> getPartidos() {
        return this.partidos;
    }

    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }
}
