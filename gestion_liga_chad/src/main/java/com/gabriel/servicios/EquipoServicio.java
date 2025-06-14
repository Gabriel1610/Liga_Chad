package com.gabriel.servicios;

import com.gabriel.dominio.Equipo;
import com.gabriel.dominio.JugadorSuplente;
import com.gabriel.dominio.JugadorTitular;

public class EquipoServicio {
    
    public void transferirJugador(Equipo equipoOrigen, Equipo equipoDestino, String nombreJugador) {
        JugadorTitular jugadorTitular = null;
        JugadorSuplente jugadorSuplente = null;
        jugadorTitular = equipoOrigen.buscarJugadorTitularPorNombre(nombreJugador);
        if(jugadorTitular == null){
            jugadorSuplente = equipoOrigen.buscarJugadorSuplentePorNombre(nombreJugador);
            if(jugadorSuplente == null){
                throw new IllegalArgumentException("Ese jugador no pertenece al equipo de origen");
            }
            else{
                equipoOrigen.borrarJugadorSuplente(jugadorSuplente);
                equipoDestino.agregarJugadorSuplente(jugadorSuplente);
            }
        }
        else{
            equipoOrigen.borrarJugadorTitular(jugadorTitular);
            equipoDestino.agregarJugadorTitular(jugadorTitular);
        }
    }

}
