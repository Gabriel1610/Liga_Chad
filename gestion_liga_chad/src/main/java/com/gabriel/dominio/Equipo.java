package com.gabriel.dominio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class Equipo {
    private String nombre;
    private ArrayList<JugadorTitular> jugadoresTitulares;
    private ArrayList<JugadorSuplente> jugadoresSuplentes;

    public Equipo() {
        this.nombre = "";
        this.jugadoresSuplentes = new ArrayList<JugadorSuplente>();
        this.jugadoresTitulares = new ArrayList<JugadorTitular>();
    }

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.jugadoresSuplentes = new ArrayList<JugadorSuplente>();
        this.jugadoresTitulares = new ArrayList<JugadorTitular>();
    }

    public Equipo(String nombre, ArrayList<JugadorSuplente> jugadoresSuplentes, ArrayList<JugadorTitular> jugadoresTitulares) {
        this.nombre = nombre;
        this.jugadoresSuplentes = jugadoresSuplentes;
        this.jugadoresTitulares = jugadoresTitulares;
    }

    public boolean perteneceAlEquipo(String nombre) {
        boolean pertenece = false;
        if(this.buscarJugadorTitularPorNombre(nombre) != null){
            pertenece = true;
        }
        else if(this.buscarJugadorSuplentePorNombre(nombre) != null){
            pertenece = true;
        }
        return pertenece;
    }

    public void ordenarJugadoresTitularesPorNombre() {
        int n = this.jugadoresTitulares.size();
        JugadorTitular jugadorTemp;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (this.jugadoresTitulares.get(j).getNombre().compareToIgnoreCase(this.jugadoresTitulares.get(j + 1).getNombre()) > 0) {
                    jugadorTemp = this.jugadoresTitulares.get(j);
                    this.jugadoresTitulares.set(j, this.jugadoresTitulares.get(j + 1));
                    this.jugadoresTitulares.set(j + 1, jugadorTemp);
                }
            }
        }
    }

    public JugadorTitular buscarJugadorTitularPorNombre(String nombre) {
        int izquierda = 0;
        int derecha = this.jugadoresTitulares.size() - 1;
        int comparacion;
        JugadorTitular jugadorEncontrado = null;
        String nombreMedio;
        while (izquierda <= derecha && jugadorEncontrado == null) {
            int medio = (izquierda + derecha) / 2;
            nombreMedio = this.jugadoresTitulares.get(medio).getNombre();
            comparacion = nombreMedio.compareToIgnoreCase(nombre);
            if (comparacion == 0) {
                jugadorEncontrado = this.jugadoresTitulares.get(medio);
            } else if (comparacion < 0) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return jugadorEncontrado;
    }

    public void ordenarJugadoresSuplentesPorNombre() {
        int n = this.jugadoresSuplentes.size();
        JugadorSuplente jugadorTemp;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (this.jugadoresSuplentes.get(j).getNombre().compareToIgnoreCase(this.jugadoresSuplentes.get(j + 1).getNombre()) > 0) {
                    jugadorTemp = this.jugadoresSuplentes.get(j);
                    this.jugadoresSuplentes.set(j, this.jugadoresSuplentes.get(j + 1));
                    this.jugadoresSuplentes.set(j + 1, jugadorTemp);
                }
            }
        }
    }

    public JugadorSuplente buscarJugadorSuplentePorNombre(String nombre) {
        int izquierda = 0;
        int derecha = this.jugadoresSuplentes.size() - 1;
        int comparacion;
        JugadorSuplente jugadorEncontrado = null;
        String nombreMedio;
        while (izquierda <= derecha && jugadorEncontrado == null) {
            int medio = (izquierda + derecha) / 2;
            nombreMedio = this.jugadoresSuplentes.get(medio).getNombre();
            comparacion = nombreMedio.compareToIgnoreCase(nombre);
            if (comparacion == 0) {
                jugadorEncontrado = this.jugadoresSuplentes.get(medio);
            } else if (comparacion < 0) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return jugadorEncontrado;
    }

    public ArrayList<Jugador> obtenerJugadoresSinGoles(){
        ArrayList<Jugador> jugadoresSinGoles = new ArrayList<Jugador>();
        for(JugadorTitular jugador : this.getJugadoresTitulares()){
            if(jugador.getCantGoles() == 0){
                jugadoresSinGoles.add(jugador);
            }
        }
        for(JugadorSuplente jugador : this.getJugadoresSuplentes()){
            if(jugador.getCantGoles() == 0){
                jugadoresSinGoles.add(jugador);
            }
        }
        return jugadoresSinGoles;
    }

    public void sumarMinutosAJugadoresTitulares(){
        for(JugadorTitular jugadorTitular : this.getJugadoresTitulares()){
            jugadorTitular.setMinutosJugados(jugadorTitular.getMinutosJugados() + 90);
        }
    }

    public void realizarUnCambio(JugadorTitular jugadorTitular, JugadorSuplente jugadorSuplente, int minuto){
        jugadorSuplente.setCantPartidosIngresados(jugadorSuplente.getCantPartidosIngresados() + 1);
        jugadorTitular.setMinutosJugados(jugadorTitular.getMinutosJugados() - (90 - minuto));
    }

    public JugadorTitular obtenerTitularConMásMinutos(){
        JugadorTitular jugadorMásMinutos = null;
        for(JugadorTitular jugador : this.getJugadoresTitulares()){
            if(jugadorMásMinutos == null){
                jugadorMásMinutos = jugador;
            }
            else if(jugador.getMinutosJugados() > jugadorMásMinutos.getMinutosJugados()){
                jugadorMásMinutos = jugador;
            }
        }
        return jugadorMásMinutos;
    }

    public JugadorSuplente obtenerJugadorSuplenteMásUtilizado(){
        JugadorSuplente jugadorSuplente = null;
        for(JugadorSuplente jugador : this.getJugadoresSuplentes()){
            if(jugador == null){
                jugadorSuplente = jugador;
            }
            else if(jugador.getCantPartidosIngresados() < jugador.getCantPartidosIngresados()){
                jugadorSuplente = jugador;
            }
        }
        return jugadorSuplente;
    }

    public void exportarJugadoresACSV() {
        String nombreArchivo = "Jugadores de " + this.getNombre() + ".csv";
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(nombreArchivo), StandardCharsets.UTF_8)) {
            writer.append("Es titular");
            writer.append(",");
            writer.append("Nombre");
            writer.append(",");
            writer.append("Edad");
            writer.append(",");
            writer.append("Cantidad de goles");
            writer.append("\n");
            for (JugadorTitular titular : this.getJugadoresTitulares()) {
                writer.append("SÍ");
                writer.append(",");
                writer.append(titular.getNombre());
                writer.append(",");
                writer.append(String.valueOf(titular.getEdad()));
                writer.append(",");
                writer.append(String.valueOf(titular.getCantGoles()));
                writer.append("\n");
            }
            for (JugadorSuplente suplente : this.getJugadoresSuplentes()) {
                writer.append("NO");
                writer.append(",");
                writer.append(suplente.getNombre());
                writer.append(",");
                writer.append(String.valueOf(suplente.getEdad()));
                writer.append(",");
                writer.append(String.valueOf(suplente.getCantGoles()));
                writer.append("\n");
            }

            writer.flush();
            System.out.println("Archivo CSV generado correctamente: " + nombreArchivo);

        } catch (IOException e) {
            System.err.println("Error al generar el archivo CSV: " + e.getMessage());
        }
    }

    public void agregarJugadorTitular(JugadorTitular jugador){
        this.jugadoresTitulares.add(jugador);
        this.ordenarJugadoresTitularesPorNombre();
    }

    public void borrarJugadorTitular(JugadorTitular jugador){
        this.jugadoresTitulares.remove(jugador);
    }

    public void agregarJugadorSuplente(JugadorSuplente jugador){
        this.jugadoresSuplentes.add(jugador);
        this.ordenarJugadoresSuplentesPorNombre();
    }

    public void borrarJugadorSuplente(JugadorSuplente jugador){
        this.jugadoresSuplentes.remove(jugador);
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<JugadorTitular> getJugadoresTitulares() {
        return this.jugadoresTitulares;
    }

    public void setJugadoresTitulares(ArrayList<JugadorTitular> jugadoresTitulares) {
        this.jugadoresTitulares = jugadoresTitulares;
    }

    public ArrayList<JugadorSuplente> getJugadoresSuplentes() {
        return this.jugadoresSuplentes;
    }

    public void setJugadoresSuplentes(ArrayList<JugadorSuplente> jugadoresSuplentes) {
        this.jugadoresSuplentes = jugadoresSuplentes;
    }
}