package com.gabriel.servicios;

import com.gabriel.dominio.Equipo;
import com.gabriel.dominio.Jugador;
import com.gabriel.dominio.JugadorTitular;
import com.gabriel.dominio.JugadorSuplente;
import java.util.Iterator;

public class EquipoServicio {

    public boolean perteneceAlEquipo(Equipo equipo, Jugador jugador) {
        boolean pertenece = false;
        Iterator<JugadorTitular> jugadorTitular = equipo.getJugadoresTitulares().iterator();
        Iterator<JugadorSuplente> jugadorSuplente = equipo.getJugadoresSuplentes().iterator();
        while(!pertenece && jugadorTitular.hasNext()){
            if (jugadorTitular.next().equals(jugador)) {
                pertenece = true;
            }
        }
        while(!pertenece && jugadorSuplente.hasNext()){
            if (jugadorSuplente.next().equals(jugador)) {
                pertenece = true;
            }
        }
        return pertenece;
    }
}

