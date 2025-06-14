package com.gabriel.dominio;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Partido {
    private Equipo local;
    private Equipo visitante;
    private int golesLocal;
    private int golesVisitante;
    private Map<Jugador, Integer> golesPorJugador;
    private ArrayList<Cambio> cambios;

    public Partido() {
        this.local = null;
        this.visitante = null;
        this.golesLocal = 0;
        this.golesVisitante = 0;
        this.golesPorJugador = new HashMap<Jugador, Integer>();
        this.cambios = new ArrayList<Cambio>();
    }

    public Partido(Equipo local, Equipo visitante) {
        this.local = local;
        this.visitante = visitante;
        this.golesLocal = 0;
        this.golesVisitante = 0;
        this.golesPorJugador = new HashMap<Jugador, Integer>();
        this.sumarMinutosAJugadores();
        this.cambios = new ArrayList<Cambio>();
    }    

    public void sumarMinutosAJugadores(){
        ArrayList<Equipo> equiposDelPartido = new ArrayList<Equipo>();
        equiposDelPartido.add(this.getLocal());
        equiposDelPartido.add(this.getVisitante());
        for(Equipo equipo : equiposDelPartido){
            for(JugadorTitular jugadorTitular : equipo.getJugadoresTitulares()){
                jugadorTitular.setMinutosJugados(jugadorTitular.getMinutosJugados() + 90);
            }
        }
    }

    public void registrarGol(Jugador jugador) {
        this.getGolesPorJugador().put(jugador, this.getGolesPorJugador().getOrDefault(jugador, 0) + 1);
        jugador.setCantGoles(jugador.getCantGoles() + 1);
        if(this.getLocal().perteneceAlEquipo(jugador.getNombre())){
            this.golesLocal++;
        }
        else{
            this.golesVisitante++;
        }
    }

    public boolean validarGol(String nombreJugador, int minuto, boolean esTitular){
        int i = 0;
        boolean analizado = false;
        boolean permitirGol;
        if(esTitular){
            permitirGol = true;
            while(i < this.cambios.size() && !analizado){
                if(this.cambios.get(i).getJugadorTitular().getNombre().equals(nombreJugador)){
                    analizado = true;
                    if(this.cambios.get(i).getMinuto() < minuto){
                        permitirGol = false;
                    }
                }
                else{
                    i++;
                }
            }
        }
        else{
            permitirGol = false;
            while(i < this.cambios.size() && !analizado){
                if(this.cambios.get(i).getJugadorSuplente().getNombre().equals(nombreJugador)){
                    analizado = true;
                    if(this.cambios.get(i).getMinuto() < minuto){
                        permitirGol = true;
                    }
                }
                else{
                    i++;
                }
            }
        }
        return permitirGol;
    }

    public void realizarCambio(JugadorTitular jugadorTitular, JugadorSuplente jugadorSuplente, int minuto){
        jugadorTitular.setMinutosJugados(jugadorTitular.getMinutosJugados() - (90 - minuto));
        jugadorSuplente.setCantPartidosIngresados(jugadorSuplente.getCantPartidosIngresados() + 1);
        this.cambios.add(new Cambio(jugadorTitular, jugadorSuplente, minuto));
    }

    public int obtenerGolesDeJugador(Jugador jugador) {
        return this.getGolesPorJugador().getOrDefault(jugador, 0);
    }

    public int obtenerGolesLocal() {
        return this.golesLocal;
    }

    public int obtenerGolesVisitante() {
        return this.golesVisitante;
    }

    public Equipo getLocal() {
        return this.local;
    }

    public void setLocal(Equipo local) {
        this.local = local;
    }

    public Equipo getVisitante() {
        return this.visitante;
    }

    public void setVisitante(Equipo visitante) {
        this.visitante = visitante;
    }

    public Map<Jugador, Integer> getGolesPorJugador() {
        return this.golesPorJugador;
    }

    public void setGolesPorJugador(Map<Jugador, Integer> golesPorJugador) {
        this.golesPorJugador = golesPorJugador;
    }

    public ArrayList<Cambio> getCambios() {
        return this.cambios;
    }

    public void setCambios(ArrayList<Cambio> cambios) {
        this.cambios = cambios;
    }
}
