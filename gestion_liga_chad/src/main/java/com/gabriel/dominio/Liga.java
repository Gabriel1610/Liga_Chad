package com.gabriel.dominio;

import java.util.ArrayList;
import java.util.HashMap;

public class Liga {
    private ArrayList<Equipo> equipos;
    private ArrayList<Partido> partidos;

    public Liga() {
        this.equipos = new ArrayList<Equipo>();
        this.partidos = new ArrayList<Partido>();
    }

    public Liga(ArrayList<Equipo> equipos, ArrayList<Partido> partidos) {
        this.equipos = equipos;
        this.partidos = partidos;
    }

    public void mostrarJugadores() {
        System.out.printf("%-30s%-15s%s\n", "Jugador", "Tipo", "Equipo");
        for (Equipo equipo : this.getEquipos()) {
            for (JugadorTitular jugadorTitular : equipo.getJugadoresTitulares()) {
                System.out.printf("%-30s%-15s%s\n", jugadorTitular.getNombre(), "titular", equipo.getNombre());
            }
            for (JugadorSuplente jugadorSuplente : equipo.getJugadoresSuplentes()) {
                System.out.printf("%-30s%-15s%s\n", jugadorSuplente.getNombre(), "suplente", equipo.getNombre());
            }
        }
    }

    public Jugador obtenerGoleador() {
        Jugador goleador = null;
        for (Equipo equipo : this.getEquipos()) {
            for (JugadorTitular jugadorTitular : equipo.getJugadoresTitulares()) {
                if (goleador == null) {
                    goleador = (Jugador) jugadorTitular;
                } else if (goleador.getCantGoles() < jugadorTitular.getCantGoles()) {
                    goleador = (Jugador) jugadorTitular;
                }
            }
            for (JugadorSuplente jugadorSuplente : equipo.getJugadoresSuplentes()) {
                if (goleador == null) {
                    goleador = jugadorSuplente;
                } else if (goleador.getCantGoles() < jugadorSuplente.getCantGoles()) {
                    goleador = (Jugador) jugadorSuplente;
                }
            }
        }
        return goleador;
    }

    public boolean existeJugador(String nombre){
        int i = 0;
        boolean existe = false;
        while(!existe && i < this.getEquipos().size()){
            if(this.getEquipos().get(i).buscarJugadorTitularPorNombre(nombre) != null){
                existe = true;
            }
            else if(!existe && this.getEquipos().get(i).buscarJugadorSuplentePorNombre(nombre) != null){
                existe = true;
            }
            else{
                i++;
            }
        }
        return existe;
    }
    
    public Partido obtenerPartido(Equipo local, Equipo visitante){
        int i = 0;
        Partido partido = null;
        while(partido == null && i < this.getPartidos().size()){
            if(this.getPartidos().get(i).getLocal().getNombre().equals(local.getNombre()) &&
                this.getPartidos().get(i).getVisitante().getNombre().equals(visitante.getNombre())){
                partido = this.getPartidos().get(i);
            }
            else{
                i++;
            }
        }
        return partido;
    }

    public ArrayList<JugadorSuplente> obtenerSuplentesSinPartidos(){
        ArrayList<JugadorSuplente> suplentesSinPartidos = new ArrayList<>();
        for (Equipo equipo : this.getEquipos()) {
            for (JugadorSuplente jugadorSuplente : equipo.getJugadoresSuplentes()) {
                if (jugadorSuplente.getCantPartidosIngresados() == 0) {
                    suplentesSinPartidos.add(jugadorSuplente);
                }
            }
        }
        return suplentesSinPartidos;
    }

    public void agregarPartido(Equipo local, Equipo visitante){
        this.getPartidos().add(new Partido(local, visitante));
    }

    public void ordenarEquiposPorNombre() {
        int n = equipos.size();
        Equipo equipoTemp;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (equipos.get(j).getNombre().compareToIgnoreCase(equipos.get(j + 1).getNombre()) > 0) {
                    equipoTemp = equipos.get(j);
                    equipos.set(j, equipos.get(j + 1));
                    equipos.set(j + 1, equipoTemp);
                }
            }
        }
    }

    public Equipo buscarEquipoPorNombre(String nombre) {
        int izquierda = 0;
        int derecha = equipos.size() - 1;
        int comparacion;
        Equipo equipoEncontrado = null;
        String nombreMedio;
        while (izquierda <= derecha && equipoEncontrado == null) {
            int medio = (izquierda + derecha) / 2;
            nombreMedio = equipos.get(medio).getNombre();
            comparacion = nombreMedio.compareToIgnoreCase(nombre);
            if (comparacion == 0) {
                equipoEncontrado = equipos.get(medio);
            } else if (comparacion < 0) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return equipoEncontrado;
    }

    public void agregarEquipo(Equipo equipoNuevo){
        this.getEquipos().add(equipoNuevo);
        this.ordenarEquiposPorNombre();
    }

    public void mostrarRanking() {
        int i, j, cantEquiposAgregados = 0, cantidadGoles;
        boolean agregado;
        String[] nombresEquipos = new String[this.equipos.size()];
        int[] cantGoles = new int[this.equipos.size()];
        for (Equipo equipo : this.getEquipos()) {
            agregado = false;
            i = 0;
            cantidadGoles = this.obtenerCantGoles(equipo);
            while (!agregado) {
                if (cantEquiposAgregados == i) {
                    nombresEquipos[i] = equipo.getNombre();
                    cantGoles[i] = cantidadGoles;
                    cantEquiposAgregados++;
                    agregado = true;
                } else if (cantGoles[i] <= cantidadGoles) {
                    for (j = cantEquiposAgregados; j > i; j--) {
                        nombresEquipos[j] = nombresEquipos[j - 1];
                        cantGoles[j] = cantGoles[j - 1];
                    }
                    nombresEquipos[i] = equipo.getNombre();
                    cantGoles[i] = cantidadGoles;
                    cantEquiposAgregados++;
                    agregado = true;
                }
                i++;
            }
        }
        System.out.printf("%-50s %s\n", "Equipo", "Goles");
        for (i = 0; i < cantGoles.length; i++) {
            System.out.printf("%-50s %d\n", nombresEquipos[i], cantGoles[i]);
        }
    }

    public int obtenerCantidadDePartidos(){
        return this.getPartidos().size();
    }

    public int obtenerCantGoles(Equipo equipo){
        int cantidadGoles = 0;
        for (Partido partido : this.getPartidos()) {
            if (partido.getLocal().equals(equipo)) {
                cantidadGoles += partido.obtenerGolesLocal();
            } else if (partido.getVisitante().equals(equipo)) {
                cantidadGoles += partido.obtenerGolesVisitante();
            }
        }
        return cantidadGoles;
    }

    public void mostrarJugadorTitularConMásMinutos() {
        JugadorTitular jugadorConMásMinutos = null;
        for (Equipo equipo : this.getEquipos()) {
            for (JugadorTitular jugador : equipo.getJugadoresTitulares()) {
                if (jugadorConMásMinutos == null) {
                    jugadorConMásMinutos = jugador;
                } else if (jugador.getMinutosJugados() > jugadorConMásMinutos.getMinutosJugados()) {
                    jugadorConMásMinutos = jugador;
                }
            }
        }
        System.out.println("El jugador con más minutos jugados es " + jugadorConMásMinutos.getNombre());
    }

    public int obtenerTotalGoles(){
        int cantGolesTotales = 0;
        for (Equipo equipo : this.getEquipos()) {
            cantGolesTotales += this.obtenerCantGoles(equipo);
        }
        return cantGolesTotales;
    }

    public HashMap<Equipo, Double> obtenerPromGolesPorPartido(){
        HashMap<Equipo, Double> promediosGoles = new HashMap<>();
        int sumaGoles;
        int cantPartidos;
        for(Equipo equipo : this.getEquipos()){
            promediosGoles.put(equipo, 0.00);
        }
        for(Equipo equipo : promediosGoles.keySet()){
            sumaGoles = 0;
            cantPartidos = 0;
            for(Partido partido : this.getPartidos()){
                if(partido.getLocal().equals(equipo)){
                    sumaGoles += partido.obtenerGolesLocal();
                    cantPartidos++;
                }
                else if(partido.getVisitante().equals(equipo)){
                    sumaGoles += partido.obtenerGolesVisitante();
                    cantPartidos++;
                }
            }
            if(cantPartidos > 0){
                promediosGoles.put(equipo, new Double(sumaGoles / cantPartidos));
            }
        }
        return promediosGoles;
    }

    public Equipo obtenerEquipoConMásGoles(){
        Equipo equipoMásGoles = null;
        int cantGoles = 0, cantGolesEquipo;
        for (Equipo equipo : this.getEquipos()) {
            if(equipoMásGoles == null){
                equipoMásGoles = equipo;
                cantGoles = this.obtenerCantGoles(equipoMásGoles);
            }
            else{
                cantGolesEquipo = this.obtenerCantGoles(equipo);
                if(cantGolesEquipo > cantGoles){
                    equipoMásGoles = equipo;
                    cantGoles = cantGolesEquipo;
                }
            }
        }
        return equipoMásGoles;
    }

    public int obtenerCantidadDeEquipos(){
        return this.getEquipos().size();
    }

    public ArrayList<Equipo> getEquipos() {
        return this.equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    public ArrayList<Partido> getPartidos() {
        return this.partidos;
    }

    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }
}
