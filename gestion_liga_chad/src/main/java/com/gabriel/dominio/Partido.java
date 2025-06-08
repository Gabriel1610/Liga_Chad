package com.gabriel.dominio;

import java.util.HashMap;
import java.util.Map;

public class Partido {
    private Equipo local;
    private Equipo visitante;
    private int cant_goles_local;
    private int cant_goles_visitante;
    private Map<String, int> goles_por_jugador;

    public Partido() {
        this.local = null;
        this.visitante = null;
        this.cant_goles_local = 0;
        this.cant_goles_visitante = 0;
        this.goles_por_jugador
    }

    public Partido(Equipo local, Equipo visitante, int cant_goles_local, int cant_goles_visitante) {
        this.local = local;
        this.visitante = visitante;
        this.cant_goles_local = cant_goles_local;
        this.cant_goles_visitante = cant_goles_visitante;
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

    public int getCantGolesLocal() {
        return this.cant_goles_local;
    }

    public void setCantGolesLocal(int cant_goles_local) {
        this.cant_goles_local = cant_goles_local;
    }

    public int getCantGolesVisitante() {
        return this.cant_goles_visitante;
    }

    public void setCantGolesVisitante(int cant_goles_visitante) {
        this.cant_goles_visitante = cant_goles_visitante;
    }

}
