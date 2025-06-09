package com.gabriel.servicios;

import com.gabriel.dominio.Partido;
import com.gabriel.dominio.Jugador;

import java.util.Map;

public class PartidoServicio {

    // Registra un gol para un jugador en un partido
    public void registrarGol(Partido partido, Jugador jugador) {
        Map<Jugador, Integer> golesPorJugador = partido.getGolesPorJugador();
        golesPorJugador.put(jugador, golesPorJugador.getOrDefault(jugador, 0) + 1);
    }

    // Obtiene la cantidad de goles anotados por un jugador en un partido
    public int obtenerGolesDeJugador(Partido partido, Jugador jugador) {
        return partido.getGolesPorJugador().getOrDefault(jugador, 0);
    }

    // Obtiene el total de goles anotados por el equipo local en el partido
    public int obtenerGolesLocal(Partido partido) {
        return partido.getGolesPorJugador().entrySet().stream()
                .filter(entry -> partido.getLocal().getJugadores().contains(entry.getKey()))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    // Obtiene el total de goles anotados por el equipo visitante en el partido
    public int obtenerGolesVisitante(Partido partido) {
        return partido.getGolesPorJugador().entrySet().stream()
                .filter(entry -> partido.getVisitante().getJugadores().contains(entry.getKey()))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
