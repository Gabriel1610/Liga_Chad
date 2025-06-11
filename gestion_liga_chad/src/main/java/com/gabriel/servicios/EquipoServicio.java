package com.gabriel.servicios;

import com.gabriel.dominio.Equipo;
import com.gabriel.dominio.Liga;

public class EquipoServicio {
    public Equipo crearEquipo(String nombreEquipo, Liga laLiga) {
        if (nombreEquipo == null || nombreEquipo.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del equipo no puede estar vacío");
        }
        if (laLiga.buscarEquipoPorNombre(nombreEquipo) != null) {
            throw new IllegalArgumentException("Ese equipo ya se encuentra registrado");
        }
        laLiga.agregarEquipo(new Equipo(nombreEquipo));
    }

}
