package com.gabriel.dominio;

import java.util.HashMap;
import java.util.Map;

import com.gabriel.servicios.EquipoServicio;

public class Partido {
    private Equipo local;
    private Equipo visitante;
    private Map<Jugador, Integer> golesPorJugador;

    public Partido() {
        this.local = null;
        this.visitante = null;
        this.golesPorJugador = new HashMap<Jugador, Integer>();
    }

    public Partido(Equipo local, Equipo visitante, Map<Jugador, Integer> golesPorJugador) {
        this.local = local;
        this.visitante = visitante;
        this.golesPorJugador = golesPorJugador;
    }

    public void registrarGol(Jugador jugador) {
        this.getGolesPorJugador().put(jugador, this.getGolesPorJugador().getOrDefault(jugador, 0) + 1);
    }

    public int obtenerGolesDeJugador(Jugador jugador) {
        return this.getGolesPorJugador().getOrDefault(jugador, 0);
    }

    public int obtenerGolesLocal() {
        int cant_goles_local = 0;
        for (Jugador jugador : this.getGolesPorJugador().keySet()) {
            if(this.getLocal().perteneceAlEquipo(jugador)){
                cant_goles_local++;
            }
        }
        return cant_goles_local;
    }

    public int obtenerGolesVisitante() {
        int cant_goles_visitante = 0;
        for (Jugador jugador : this.getGolesPorJugador().keySet()) {
            if(this.getVisitante().perteneceAlEquipo(jugador)){
                cant_goles_visitante++;
            }
        }
        return cant_goles_visitante;
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
}
