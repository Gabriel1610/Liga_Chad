package com.gabriel.servicios;

import com.gabriel.dominio.Equipo;
import com.gabriel.dominio.Liga;

public class LigaServicio {
    private static final int EDAD_MÍNIMA = 6;
    private static final int MÍN_CANT_GOLES = 0;
    private static final int MÍN_CANT_MINUTOS_JUGADOS = 0;
    private static final int MÍN_CANT_PARTIDOS_JUGADOS = 0;

    public Equipo buscarEquipoPorNombre(String nombreEquipo, Liga laLiga){
        if (nombreEquipo == null || nombreEquipo.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del equipo no puede estar vacío");
        }
        return new Equipo(nombreEquipo);
    }
    
    public Equipo crearEquipo(String nombreEquipo, Liga laLiga) {
        if (nombreEquipo == null || nombreEquipo.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del equipo no puede estar vacío");
        }
        if (laLiga.buscarEquipoPorNombre(nombreEquipo) != null) {
            throw new IllegalArgumentException("Ese equipo ya se encuentra registrado");
        }
        return new Equipo(nombreEquipo);
    }

    public void registrarNombreJugador(String nombreJugador, Liga laLiga) {
        if (nombreJugador == null || nombreJugador.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del jugador no puede estar vacío");
        }
        if (laLiga.existeJugador(nombreJugador)) {
            throw new IllegalArgumentException("Ese jugador ya se encuentra registrado");
        }
        return;
    }

    public void registrarEdadJugador(int edadJugador) {
        if (edadJugador < EDAD_MÍNIMA) {
            throw new IllegalArgumentException("El jugador no puede tener menos de " + EDAD_MÍNIMA + " años.");
        }
        return;
    }

    public void registrarGolesJugador(int golesJugador) {
        if (golesJugador < MÍN_CANT_GOLES) {
            throw new IllegalArgumentException("El jugador no puede tener menos de " + MÍN_CANT_GOLES + " goles.");
        }
        return;
    }

    public void registrarMinutosJugador(int minutosJugador) {
        if (minutosJugador < MÍN_CANT_MINUTOS_JUGADOS) {
            throw new IllegalArgumentException("El jugador no puede tener menos de " + MÍN_CANT_MINUTOS_JUGADOS + " minutos jugados.");
        }
        return;
    }

    public void registrarPartidosJugadosJugador(int cantPartidosIngresados) {
        if (cantPartidosIngresados < MÍN_CANT_MINUTOS_JUGADOS) {
            throw new IllegalArgumentException("El jugador no puede tener menos de " + MÍN_CANT_MINUTOS_JUGADOS + " partidos jugados.");
        }
        return;
    }
}
