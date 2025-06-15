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
        this.jugadoresTitularesLocal = new ArrayList<JugadorTitular>();
        this.jugadoresSuplentesLocal = new ArrayList<JugadorSuplente>();
        this.jugadoresTitularesVisitante = new ArrayList<JugadorTitular>();
        this.jugadoresSuplentesVisitante = new ArrayList<JugadorSuplente>();
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

    public void registrarGol(Jugador jugador) {
        this.getGolesPorJugador().put(jugador, this.getGolesPorJugador().getOrDefault(jugador, 0) + 1);
        jugador.setCantGoles(jugador.getCantGoles() + 1);
        if(this.getLocal().perteneceAlEquipo(jugador.getNombre())){
            this.golesLocal++;
        }
        else{
            this.golesVisitante++;
        }
    }

    public boolean validarGol(String nombreJugador, int minuto, boolean esTitular){
        int i = 0;
        boolean analizado = false;
        boolean permitirGol;
        if(esTitular){
            permitirGol = true;
            while(i < this.cambios.size() && !analizado){
                if(this.cambios.get(i).getJugadorTitular().getNombre().equals(nombreJugador)){
                    analizado = true;
                    if(this.cambios.get(i).getMinuto() < minuto){
                        permitirGol = false;
                    }
                }
                else{
                    i++;
                }
            }
        }
        else{
            permitirGol = false;
            while(i < this.cambios.size() && !analizado){
                if(this.cambios.get(i).getJugadorSuplente().getNombre().equals(nombreJugador)){
                    analizado = true;
                    if(this.cambios.get(i).getMinuto() < minuto){
                        permitirGol = true;
                    }
                }
                else{
                    i++;
                }
            }
        }
        return permitirGol;
    }

    public void realizarCambio(JugadorTitular jugadorTitular, JugadorSuplente jugadorSuplente, int minuto){
        if(this.validarCambio(jugadorTitular, jugadorSuplente, minuto)){
            jugadorTitular.setMinutosJugados(jugadorTitular.getMinutosJugados() - (90 - minuto));
            jugadorSuplente.setCantPartidosIngresados(jugadorSuplente.getCantPartidosIngresados() + 1);
            this.cambios.add(new Cambio(jugadorTitular, jugadorSuplente, minuto));
        }
        else{
            throw new IllegalArgumentException("El cambio no se ha llevado a cabo ya sea porque los jugadores no pertenecen a un equipo del partido o no pertenecen al mismo equipo");
        }
    }

    public boolean validarCambio(JugadorTitular jugadorTitular, JugadorSuplente jugadorSuplente, int minuto){
        int j, i;
        boolean permitirCambio = false;
        boolean finalizarAnálisis = false;
        i = 0;
        if(minuto < 0 || minuto > this.DURACIÓN_PARTIDOS){
            finalizarAnálisis = true;
        }
        while(i < this.getCambios().size() && !finalizarAnálisis){
            if(this.getCambios().get(i).getJugadorTitular().equals(jugadorTitular)){
                finalizarAnálisis = true;
            }
            else{
                i++;
            }
        }
        i = 0;
        while(i < this.getCambios().size() && !finalizarAnálisis){
            if(this.getCambios().get(i).getJugadorSuplente().equals(jugadorSuplente)){
                finalizarAnálisis = true;
            }
            else{
                i++;
            }
        }
        i = 0;
        while(i < this.getJugadoresTitularesLocal().size() && !finalizarAnálisis){
            if(this.getJugadoresTitularesLocal().get(i).equals(jugadorTitular)){
                j = 0;
                while(j < this.getJugadoresSuplentesLocal().size() && !finalizarAnálisis){
                    if(this.getJugadoresSuplentesLocal().get(i).equals(jugadorSuplente)){
                        permitirCambio = true;
                    }
                    else{
                        j++;
                    }
                }
                finalizarAnálisis = true;
            }
            else{
                i++;
            }
        }
        i = 0;
        while(i < this.getJugadoresTitularesVisitante().size() && !finalizarAnálisis){
            if(this.getJugadoresTitularesVisitante().get(i).equals(jugadorTitular)){
                j = 0;
                while(j < this.getJugadoresSuplentesVisitante().size() && !finalizarAnálisis){
                    if(this.getJugadoresSuplentesVisitante().get(i).equals(jugadorSuplente)){
                        permitirCambio = true;
                    }
                    else{
                        j++;
                    }
                }
                finalizarAnálisis = true;
            }
            else{
                i++;
            }
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
