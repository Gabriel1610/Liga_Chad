package com.gabriel.servicios;

import com.gabriel.dominio.Partido;
import com.gabriel.dominio.Jugador;
import com.gabriel.servicios.EquipoServicio;

public class PartidoServicio {

    public void registrarGol(Partido partido, Jugador jugador) {
        partido.getGolesPorJugador().put(jugador, partido.getGolesPorJugador().getOrDefault(jugador, 0) + 1);
    }

    public int obtenerGolesDeJugador(Partido partido, Jugador jugador) {
        return partido.getGolesPorJugador().getOrDefault(jugador, 0);
    }

    public int obtenerGolesLocal(Partido partido) {
        int cant_goles_local = 0;
        EquipoServicio servicioEquipo = new EquipoServicio();
        for (Jugador jugador : partido.getGolesPorJugador().keySet()) {
            if(servicioEquipo.perteneceAlEquipo(partido.getLocal(), jugador)){
                cant_goles_local++;
            }
        }
        return cant_goles_local;
    }

    public int obtenerGolesVisitante(Partido partido) {
        int cant_goles_visitante = 0;
        EquipoServicio servicioEquipo = new EquipoServicio();
        for (Jugador jugador : partido.getGolesPorJugador().keySet()) {
            if(servicioEquipo.perteneceAlEquipo(partido.getVisitante(), jugador)){
                cant_goles_visitante++;
            }
        }
        return cant_goles_visitante;
    }
}
