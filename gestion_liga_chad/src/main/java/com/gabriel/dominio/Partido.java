package com.gabriel.dominio;

import java.util.HashMap;
import java.util.Map;

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
