package com.gabriel.servicios;

import com.gabriel.dominio.*;

public class LigaServicio {
    private static final int EDAD_MÍNIMA = 6;
    private static final int MÍN_CANT_MINUTOS_JUGADOS = 0;
    private static final int MÍN_CANT_PARTIDOS_JUGADOS = 0;
    private static final int DURACIÓN_PARTIDOS = 90;
    
    public Equipo crearEquipo(String nombreEquipo, Liga laLiga) {
        if (nombreEquipo == null || nombreEquipo.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del equipo no puede estar vacío");
        }
        if (laLiga.buscarEquipoPorNombre(nombreEquipo) != null) {
            throw new IllegalArgumentException("Ese equipo ya se encuentra registrado");
        }
        return new Equipo(nombreEquipo);
    }

    public Equipo encontrarEquipo(String nombreEquipo, Liga laLiga) {
        Equipo equipoEncontrado;
        if (nombreEquipo == null || nombreEquipo.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del equipo no puede estar vacío");
        }
        equipoEncontrado = laLiga.buscarEquipoPorNombre(nombreEquipo);
        if (equipoEncontrado == null) {
            throw new IllegalArgumentException("Ese equipo no se encuentra registrado");
        }
        return equipoEncontrado;
    }

    public void registrarPartido(Equipo equipoLocal, Equipo equipoVisitante, Liga laLiga) {
        if(laLiga.obtenerPartido(equipoLocal, equipoVisitante) != null){
            throw new IllegalArgumentException("Ese partido ya fue registrado en la liga");
        }
        if(equipoLocal == equipoVisitante){
            throw new IllegalArgumentException("Un equipo no puede jugar contra sí mismo");
        }
        laLiga.agregarPartido(equipoLocal, equipoVisitante);
    }

    public void asignarGolesPartido(Equipo equipoLocal, Equipo equipoVisitante, String nombreJugador, int minuto, Liga laLiga) throws IllegalArgumentException {
        Partido partido;
        partido = laLiga.obtenerPartido(equipoLocal, equipoVisitante);
        if(partido == null){
            throw new IllegalArgumentException("Ese partido no existe en la liga");
        }
        if(partido.validarGol(nombreJugador, minuto)){
            partido.registrarGol(nombreJugador);
        }
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

    public void validarNombreJugador(String nombreJugador) {
        if (nombreJugador == null || nombreJugador.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del jugador no puede estar vacío");
        }
        return;
    }

    public void registrarEdadJugador(int edadJugador) {
        if (edadJugador < EDAD_MÍNIMA) {
            throw new IllegalArgumentException("El jugador no puede tener menos de " + EDAD_MÍNIMA + " años.");
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
        if (cantPartidosIngresados < MÍN_CANT_PARTIDOS_JUGADOS) {
            throw new IllegalArgumentException("El jugador no puede tener menos de " + MÍN_CANT_PARTIDOS_JUGADOS + " partidos jugados.");
        }
        return;
    }
}
