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

    public void asignarGolesPartido(Equipo equipoLocal, Equipo equipoVisitante, String nombreJugador, int minuto, Liga laLiga) {
        Jugador jugador = null;
        Partido partido;
        boolean esTitular = true;
        String mensaje = "";
        partido = laLiga.obtenerPartido(equipoLocal, equipoVisitante);
        if(partido == null){
            throw new IllegalArgumentException("Ese partido no existe en la liga");
        }
        jugador = (Jugador) equipoLocal.buscarJugadorTitularPorNombre(nombreJugador);
        if(jugador == null){
            jugador = (Jugador) equipoLocal.buscarJugadorSuplentePorNombre(nombreJugador);
            if(jugador == null){
                jugador = (Jugador) equipoVisitante.buscarJugadorTitularPorNombre(nombreJugador);
                if(jugador == null){
                    jugador = (Jugador) equipoVisitante.buscarJugadorSuplentePorNombre(nombreJugador);
                    if(jugador == null){
                        throw new IllegalArgumentException("El jugador no pertenece a ningún equipo del partido");
                    }
                    else{
                        esTitular = false;
                    }
                }
            }
            else{
                esTitular = false;
            }
        }
        if(minuto > DURACIÓN_PARTIDOS){
            throw new IllegalArgumentException("Un gol no puede haber sido hecho en el minuto " + minuto + " porque los partidos de fútbol tienen " + DURACIÓN_PARTIDOS + " minutos.");
        }
        if(minuto < 0){
            throw new IllegalArgumentException("Ese gol es imposible porque los minutos no pueden ser negativos");
        }
        if(partido.validarGol(nombreJugador, minuto, esTitular)){
            laLiga.obtenerPartido(equipoLocal, equipoVisitante).registrarGol((Jugador) jugador);
        }
        else{
            mensaje = "Ese gol no es válido porque el jugador es ";
            if(esTitular){
                mensaje += "titular y ya había sido cambiado";
            }
            else{
                mensaje += "suplente y todavía no había entrado al partido";
            }
            throw new IllegalArgumentException(mensaje);
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
