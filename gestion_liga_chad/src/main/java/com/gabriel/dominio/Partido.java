package com.gabriel.dominio;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Partido {
    private final int DURACIÓN_PARTIDOS = 90;
    private Equipo local;
    private Equipo visitante;
    private int golesLocal;
    private int golesVisitante;
    private Map<Jugador, Integer> golesPorJugador;
    private ArrayList<Cambio> cambios;
    private ArrayList<JugadorTitular> jugadoresTitularesLocal;
    private ArrayList<JugadorSuplente> jugadoresSuplentesLocal;
    private ArrayList<JugadorTitular> jugadoresTitularesVisitante;
    private ArrayList<JugadorSuplente> jugadoresSuplentesVisitante;

    public Partido() {
        this.local = null;
        this.visitante = null;
        this.golesLocal = 0;
        this.golesVisitante = 0;
        this.golesPorJugador = new HashMap<Jugador, Integer>();
        this.cambios = new ArrayList<Cambio>();
        this.jugadoresTitularesLocal = new ArrayList<JugadorTitular>();
        this.jugadoresSuplentesLocal = new ArrayList<JugadorSuplente>();
        this.jugadoresTitularesVisitante = new ArrayList<JugadorTitular>();
        this.jugadoresSuplentesVisitante = new ArrayList<JugadorSuplente>();
    }

    public Partido(Equipo local, Equipo visitante) {
        this.local = local;
        this.visitante = visitante;
        this.golesLocal = 0;
        this.golesVisitante = 0;
        this.golesPorJugador = new HashMap<Jugador, Integer>();
        this.sumarMinutosAJugadores();
        this.cambios = new ArrayList<Cambio>();
        this.jugadoresTitularesLocal = new ArrayList<JugadorTitular>(this.local.getJugadoresTitulares());
        this.jugadoresSuplentesLocal = new ArrayList<JugadorSuplente>(this.local.getJugadoresSuplentes());
        this.jugadoresTitularesVisitante = new ArrayList<JugadorTitular>(this.visitante.getJugadoresTitulares());
        this.jugadoresSuplentesVisitante = new ArrayList<JugadorSuplente>(this.visitante.getJugadoresSuplentes());
    }    

    public void sumarMinutosAJugadores(){
        ArrayList<Equipo> equiposDelPartido = new ArrayList<Equipo>();
        equiposDelPartido.add(this.getLocal());
        equiposDelPartido.add(this.getVisitante());
        for(Equipo equipo : equiposDelPartido){
            for(JugadorTitular jugadorTitular : equipo.getJugadoresTitulares()){
                jugadorTitular.setMinutosJugados(jugadorTitular.getMinutosJugados() + 90);
            }
        }
    }

    public void registrarGol(String nombreJugador) {
        Jugador jugador;
        jugador = this.buscarJugadorTitularLocalPorNombre(nombreJugador);
        if(jugador == null){
            jugador = this.buscarJugadorSuplenteLocalPorNombre(nombreJugador);
            if(jugador == null){
                jugador = this.buscarJugadorSuplenteVisitantePorNombre(nombreJugador);
                if(jugador == null){
                    jugador = this.buscarJugadorTitularVisitantePorNombre(nombreJugador);
                }
            }
        }
        this.getGolesPorJugador().put(jugador, this.getGolesPorJugador().getOrDefault(jugador, 0) + 1);
        jugador.setCantGoles(jugador.getCantGoles() + 1);
        if(this.getLocal().perteneceAlEquipo(jugador.getNombre())){
            this.golesLocal++;
        }
        else{
            this.golesVisitante++;
        }
    }

    public JugadorSuplente buscarJugadorSuplenteLocalPorNombre(String nombre) {
        int izquierda = 0;
        int derecha = this.getJugadoresSuplentesLocal().size() - 1;
        int comparacion;
        JugadorSuplente jugadorEncontrado = null;
        String nombreMedio;
        while (izquierda <= derecha && jugadorEncontrado == null) {
            int medio = (izquierda + derecha) / 2;
            nombreMedio = this.getJugadoresSuplentesLocal().get(medio).getNombre();
            comparacion = nombreMedio.compareToIgnoreCase(nombre);
            if (comparacion == 0) {
                jugadorEncontrado = this.getJugadoresSuplentesLocal().get(medio);
            } else if (comparacion < 0) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return jugadorEncontrado;
    }

    public JugadorTitular buscarJugadorTitularLocalPorNombre(String nombre) {
        int izquierda = 0;
        int derecha = this.getJugadoresTitularesLocal().size() - 1;
        int comparacion;
        JugadorTitular jugadorEncontrado = null;
        String nombreMedio;
        while (izquierda <= derecha && jugadorEncontrado == null) {
            int medio = (izquierda + derecha) / 2;
            nombreMedio = this.getJugadoresTitularesLocal().get(medio).getNombre();
            comparacion = nombreMedio.compareToIgnoreCase(nombre);
            if (comparacion == 0) {
                jugadorEncontrado = this.getJugadoresTitularesLocal().get(medio);
            } else if (comparacion < 0) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return jugadorEncontrado;
    }

    public JugadorTitular buscarJugadorTitularVisitantePorNombre(String nombre) {
        int izquierda = 0;
        int derecha = this.getJugadoresTitularesVisitante().size() - 1;
        int comparacion;
        JugadorTitular jugadorEncontrado = null;
        String nombreMedio;
        while (izquierda <= derecha && jugadorEncontrado == null) {
            int medio = (izquierda + derecha) / 2;
            nombreMedio = this.getJugadoresTitularesVisitante().get(medio).getNombre();
            comparacion = nombreMedio.compareToIgnoreCase(nombre);
            if (comparacion == 0) {
                jugadorEncontrado = this.getJugadoresTitularesVisitante().get(medio);
            } else if (comparacion < 0) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return jugadorEncontrado;
    }

    public JugadorSuplente buscarJugadorSuplenteVisitantePorNombre(String nombre) {
        int izquierda = 0;
        int derecha = this.getJugadoresSuplentesVisitante().size() - 1;
        int comparacion;
        JugadorSuplente jugadorEncontrado = null;
        String nombreMedio;
        while (izquierda <= derecha && jugadorEncontrado == null) {
            int medio = (izquierda + derecha) / 2;
            nombreMedio = this.getJugadoresSuplentesVisitante().get(medio).getNombre();
            comparacion = nombreMedio.compareToIgnoreCase(nombre);
            if (comparacion == 0) {
                jugadorEncontrado = this.getJugadoresSuplentesVisitante().get(medio);
            } else if (comparacion < 0) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return jugadorEncontrado;
    }

    public boolean validarGol(String nombreJugador, int minuto){
        int i = 0;
        boolean analizado = false;
        boolean permitirGol;
        i = 0;
        permitirGol = true;
        if(minuto > DURACIÓN_PARTIDOS){
            throw new IllegalArgumentException("Un gol no puede haber sido hecho en el minuto " + minuto + " porque los partidos de fútbol tienen " + DURACIÓN_PARTIDOS + " minutos.");
        }
        if(minuto < 0){
            throw new IllegalArgumentException("Ese gol es imposible porque los minutos no pueden ser negativos");
        }
        while(i < this.cambios.size() && !analizado){
            if(this.cambios.get(i).getJugadorTitular().getNombre().equals(nombreJugador)){
                analizado = true;
                if(this.cambios.get(i).getMinuto() < minuto){
                    throw new IllegalArgumentException("El gol no es válido porque el jugador titular " + nombreJugador + " ya había sido cambiado con anterioridad por el jugador suplente " + 
                    this.cambios.get(i).getJugadorSuplente().getNombre() + " a los " + this.cambios.get(i).getMinuto() + " minutos del partido.");
                }
            }
            else{
                i++;
            }
        }
        permitirGol = false;
        if(this.buscarJugadorTitularLocalPorNombre(nombreJugador) != null){
            permitirGol = true;
        }
        else{
            if(this.buscarJugadorTitularVisitantePorNombre(nombreJugador) != null){
                permitirGol = true;
            }
        }
        if(!permitirGol){
            i = 0;
            while(i < this.cambios.size() && !analizado){
                if(this.cambios.get(i).getJugadorSuplente().getNombre().equals(nombreJugador)){
                    analizado = true;
                    if(this.cambios.get(i).getMinuto() < minuto){
                        permitirGol = true;
                    }
                    else{
                        throw new IllegalArgumentException("El gol no es válido porque el jugador suplente " + nombreJugador +
                         " todavía no había ingresado al campo de juego. Ingresó recién a los " + this.cambios.get(i).getMinuto() +
                         " minutos del partido por el jugador titular " + this.cambios.get(i).getJugadorTitular().getNombre());
                    }
                }
                else{
                    i++;
                }
            }
            if(!permitirGol){
                if(this.buscarJugadorSuplenteLocalPorNombre(nombreJugador) != null || 
                this.buscarJugadorSuplenteVisitantePorNombre(nombreJugador) != null){
                    throw new IllegalArgumentException("El gol no es válido porque el jugador suplente " + nombreJugador +
                    " no entró en ese partido.");
                }
                throw new IllegalArgumentException("El gol no es válido porque el jugador " + nombreJugador +
                " no jugó ese partido.");
            }
        }
        return permitirGol;
    }

    public void realizarCambio(JugadorTitular jugadorTitular, JugadorSuplente jugadorSuplente, int minuto) throws IllegalArgumentException {
        if(this.validarCambio(jugadorTitular, jugadorSuplente, minuto)){
            jugadorTitular.setMinutosJugados(jugadorTitular.getMinutosJugados() - (90 - minuto));
            jugadorSuplente.setCantPartidosIngresados(jugadorSuplente.getCantPartidosIngresados() + 1);
            this.cambios.add(new Cambio(jugadorTitular, jugadorSuplente, minuto));
        }
    }

    public boolean validarCambio(JugadorTitular jugadorTitular, JugadorSuplente jugadorSuplente, int minuto){
        int j, i;
        i = 0;
        boolean permitirCambio = false;
        if(minuto < 0 || minuto > this.DURACIÓN_PARTIDOS){
            throw new IllegalArgumentException("El cambio no se ha llevado a cabo porque el tiempo no es correcto");
        }
        while(i < this.getCambios().size()){
            if(this.getCambios().get(i).getJugadorTitular().equals(jugadorTitular)){
                throw new IllegalArgumentException("El cambio no se ha llevado a cabo porque ese jugador titular ya había salido al minuto " + this.getCambios().get(i).getMinuto() + " reemplazado por el jugador " + this.getCambios().get(i).getJugadorSuplente().getNombre());
            }
            else{
                i++;
            }
        }
        i = 0;
        while(i < this.getCambios().size()){
            if(this.getCambios().get(i).getJugadorSuplente().equals(jugadorSuplente)){
                throw new IllegalArgumentException("El cambio no se ha llevado a cabo porque ese jugador suplente ya había ingresado al minuto " + this.getCambios().get(i).getMinuto() + " reemplazando al jugador " + this.getCambios().get(i).getJugadorTitular().getNombre());
            }
            else{
                i++;
            }
        }
        i = 0;
        while(i < this.getJugadoresTitularesLocal().size() && !permitirCambio){
            if(this.getJugadoresTitularesLocal().get(i).equals(jugadorTitular)){
                j = 0;
                while(j < this.getJugadoresSuplentesLocal().size() && !permitirCambio){
                    if(this.getJugadoresSuplentesLocal().get(j).equals(jugadorSuplente)){
                        permitirCambio = true;
                    }
                    else{
                        j++;
                    }
                }
                if(!permitirCambio){
                    throw new IllegalArgumentException("El cambio no se puede llevar a cabo porque si bien el jugador titular " + jugadorTitular.getNombre() + " pertenece al equipo local, el jugador suplente " + jugadorSuplente.getNombre() + " no.");    
                }
            }
            else{
                i++;
            }
        }
        i = 0;
        while(i < this.getJugadoresTitularesVisitante().size() && !permitirCambio){
            if(this.getJugadoresTitularesVisitante().get(i).equals(jugadorTitular)){
                j = 0;
                while(j < this.getJugadoresSuplentesVisitante().size() && !permitirCambio){
                    if(this.getJugadoresSuplentesVisitante().get(j).equals(jugadorSuplente)){
                        permitirCambio = true;
                    }
                    else{
                        // System.out.println("Los jugadores " + jugadorSuplente.getNombre() + " y " + this.getJugadoresSuplentesVisitante().get(i).getNombre() + " son diferentes.");
                        j++;
                    }
                }
                if(!permitirCambio){
                    throw new IllegalArgumentException("El cambio no se puede llevar a cabo porque si bien el jugador titular " + jugadorTitular.getNombre() + " pertenece al equipo visitante, el jugador suplente " + jugadorSuplente.getNombre() + " no.");
                }  
            }
            else{
                i++;
            }
        }
        if(!permitirCambio){
            throw new IllegalArgumentException("El cambio no se puede llevar a cabo porque el jugador titular " + jugadorTitular.getNombre() + " no pertenece a ningún equipo del partido.");
        }
        return permitirCambio;
    }

    public int obtenerGolesDeJugador(Jugador jugador) {
        return this.getGolesPorJugador().getOrDefault(jugador, 0);
    }

    public int obtenerGolesLocal() {
        return this.golesLocal;
    }

    public int obtenerGolesVisitante() {
        return this.golesVisitante;
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

    public Map<Jugador, Integer> getGolesPorJugador() {
        return this.golesPorJugador;
    }

    public void setGolesPorJugador(Map<Jugador, Integer> golesPorJugador) {
        this.golesPorJugador = golesPorJugador;
    }

    public ArrayList<Cambio> getCambios() {
        return this.cambios;
    }

    public void setCambios(ArrayList<Cambio> cambios) {
        this.cambios = cambios;
    }

    public ArrayList<JugadorTitular> getJugadoresTitularesLocal() {
        return jugadoresTitularesLocal;
    }

    public ArrayList<JugadorSuplente> getJugadoresSuplentesLocal() {
        return jugadoresSuplentesLocal;
    }

    public ArrayList<JugadorTitular> getJugadoresTitularesVisitante() {
        return jugadoresTitularesVisitante;
    }

    public ArrayList<JugadorSuplente> getJugadoresSuplentesVisitante() {
        return jugadoresSuplentesVisitante;
    }

    public void setJugadoresTitularesLocal(ArrayList<JugadorTitular> jugadoresTitularesLocal) {
        this.jugadoresTitularesLocal = jugadoresTitularesLocal;
    }

    public void setJugadoresSuplentesLocal(ArrayList<JugadorSuplente> jugadoresSuplentesLocal) {
        this.jugadoresSuplentesLocal = jugadoresSuplentesLocal;
    }

    public void setJugadoresTitularesVisitante(ArrayList<JugadorTitular> jugadoresTitularesVisitante) {
        this.jugadoresTitularesVisitante = jugadoresTitularesVisitante;
    }

    public void setJugadoresSuplentesVisitante(ArrayList<JugadorSuplente> jugadoresSuplentesVisitante) {
        this.jugadoresSuplentesVisitante = jugadoresSuplentesVisitante;
    }
}
