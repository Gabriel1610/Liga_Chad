package com.gabriel;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import com.gabriel.dominio.*;
import com.gabriel.servicios.*;
import java.util.Scanner;
import com.gabriel.dominio.*;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final int CANT_OPCIONES = 15;
    private static final int OPCIÓN_REGISTRAR_JUGADORES = 1;
    private static final int OPCIÓN_CREAR_EQUIPO = 2;
    public static final int OPCIÓN_EXPORTAR_JUGADORES_CSV = 12;
    public static final int OPCIÓN_REGISTRAR_PARTIDO = 3;
    public static final int OPCIÓN_REPORTE_EQUIPO = 14;
    public static final int OPCIÓN_ASIGNAR_GOLES_PARTIDO = 4;
    public static final int OPCIÓN_MOSTRAR_JUGADORES_TIPO = 5;
    public static final int OPCIÓN_MOSTRAR_GOLEADOR_LIGA = 6;
    public static final int OPCIÓN_REPORTE_LIGA = 13;
    public static final int OPCIÓN_MOSTRAR_PROMEDIO_GOLES_POR_PARTIDO = 7;
    public static final int OPCIÓN_MOSTRAR_RANKING_EQUIPOS_POR_GOLES = 8;
    public static final int OPCIÓN_TRANSFERIR_JUGADOR = 9;
    public static final int OPCIÓN_MOSTRAR_SUPLENTES_NUNCA_INGRESADOS = 10;
    public static final int OPCIÓN_MOSTRAR_TITULAR_MÁX_MINUTOS = 11;
    public static void main( String[] args )
    {
        Liga ligaChad = new Liga();
        int opciónMenú;
        Equipo nuevoEquipo = null;
        Scanner teclado = new Scanner(System.in);
        opciónMenú = solicitarOpción(teclado);
        while(opciónMenú != CANT_OPCIONES){
            System.out.println("\n\n");
            switch (opciónMenú) {
                case OPCIÓN_REGISTRAR_JUGADORES:
                    if(ligaChad.obtenerCantidadDeEquipos() > 0){
                        registrarJugador(teclado, ligaChad);
                        System.out.println("\nJugador creado correctamente :D");
                    }
                    else{
                        System.out.println("Primero debe agregar equipos para poder registrar jugadores :(");
                    }
                    break;
                case OPCIÓN_ASIGNAR_GOLES_PARTIDO:
                    if(ligaChad.obtenerCantidadDePartidos() > 0){
                        asignarGolesPartido(teclado, ligaChad);
                    }
                    else{
                        System.out.println("Primero debe crear partidos para poder registrar goles :(");
                    }
                    break;
                case OPCIÓN_CREAR_EQUIPO:
                    nuevoEquipo = crearEquipo(teclado, ligaChad);
                    ligaChad.agregarEquipo(nuevoEquipo);
                    System.out.println("Equipo creado correctamente :D");
                    break;
                case OPCIÓN_EXPORTAR_JUGADORES_CSV:
                    if(ligaChad.obtenerCantidadDeEquipos() > 0){
                        exportarJugadoresACSV(teclado, ligaChad);
                    }
                    else{
                        System.out.println("Primero debe agregar equipos para poder exportar archivo CSV con los nombres de sus jugadores :(");
                    }
                    break;
                case OPCIÓN_REGISTRAR_PARTIDO:
                    if(ligaChad.obtenerCantidadDeEquipos() > 1){
                        registrarPartido(teclado, ligaChad);
                        System.out.println("\nPartido creado correctamente :D");
                    }
                    else{
                        System.out.println("Primero debe agregar por lo menos 2 equipos para poder registrar un partido :(");
                    }
                    break;
                    
                case OPCIÓN_MOSTRAR_JUGADORES_TIPO:
                    ligaChad.mostrarJugadores();
                    break;

                case OPCIÓN_MOSTRAR_GOLEADOR_LIGA:
                    mostrarGoleadorDeLaLiga(ligaChad);
                    break;

                case OPCIÓN_MOSTRAR_PROMEDIO_GOLES_POR_PARTIDO:
                    mostrarPromedioGolesPartido(ligaChad);
                    break;

                case OPCIÓN_MOSTRAR_RANKING_EQUIPOS_POR_GOLES:
                    ligaChad.mostrarRanking();
                    break;

                case OPCIÓN_TRANSFERIR_JUGADOR:
                    break;

                case OPCIÓN_MOSTRAR_SUPLENTES_NUNCA_INGRESADOS:
                    break;

                case OPCIÓN_MOSTRAR_TITULAR_MÁX_MINUTOS:
                    ligaChad.mostrarJugadorTitularConMásPartidos();
                    break;

                case OPCIÓN_REPORTE_LIGA:
                    break;

                case OPCIÓN_REPORTE_EQUIPO:
                    break;

                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
                    break;
            }
            if(opciónMenú >= OPCIÓN_REGISTRAR_JUGADORES &&
                opciónMenú < CANT_OPCIONES){
                System.out.print("\n\n\nPresione una tecla para continuar...");
                teclado.nextLine();
            }
            opciónMenú = solicitarOpción(teclado);
        }
        System.out.println("\n\n¡Adiós! :D");
    }

    public static int solicitarOpción(Scanner teclado){
        int opción = 0;
        Map<Integer, String> opciones = new HashMap<>();
        opciones.put(OPCIÓN_REGISTRAR_JUGADORES, "Registrar jugadores.");
        opciones.put(OPCIÓN_CREAR_EQUIPO, "Crear equipos.");
        opciones.put(OPCIÓN_REGISTRAR_PARTIDO, "Registrar partidos entre equipos.");
        opciones.put(OPCIÓN_EXPORTAR_JUGADORES_CSV, "Exportar en un archivo .csv los jugadores (titulares y suplentes) de un equipo dado.");
        opciones.put(OPCIÓN_ASIGNAR_GOLES_PARTIDO, "Asignar goles a jugadores durante un partido.");
        opciones.put(OPCIÓN_MOSTRAR_JUGADORES_TIPO, "Mostrar listado de todos los jugadores y su tipo (titular o suplente).");
        opciones.put(OPCIÓN_MOSTRAR_GOLEADOR_LIGA, "Calcular y mostrar el goleador de la liga.");
        opciones.put(OPCIÓN_MOSTRAR_PROMEDIO_GOLES_POR_PARTIDO, "Mostrar el promedio de goles por partido de cada equipo.");
        opciones.put(OPCIÓN_MOSTRAR_RANKING_EQUIPOS_POR_GOLES, "Mostrar el ranking de equipos por cantidad de goles anotados.");
        opciones.put(OPCIÓN_TRANSFERIR_JUGADOR, "Permitir transferir un jugador de un equipo a otro.");
        opciones.put(OPCIÓN_MOSTRAR_SUPLENTES_NUNCA_INGRESADOS, "Mostrar jugadores suplentes que nunca hayan ingresado.");
        opciones.put(OPCIÓN_MOSTRAR_TITULAR_MÁX_MINUTOS, "Mostrar el jugador titular con mayor cantidad de minutos jugados.");
        opciones.put(OPCIÓN_REPORTE_LIGA, "Listar reporte general de la liga.");
        opciones.put(OPCIÓN_REPORTE_EQUIPO, "Listar reporte de un equipo.");
        opciones.put(CANT_OPCIONES, "Salir.");
        for(int i = 1; i <= CANT_OPCIONES; i++){
            System.out.println(i + ") " + opciones.get(i));
        }
        System.out.print("\nElija una opción: ");
        opción = teclado.nextInt();
        while(opción < OPCIÓN_REGISTRAR_JUGADORES || opción > CANT_OPCIONES){
            System.out.print("La opción elegida no es válida. Inténtelo nuevamente: ");
            opción = teclado.nextInt();
        }
        teclado.nextLine();
        return opción;
    }

    public static Equipo crearEquipo(Scanner teclado, Liga laLiga){
        String nombreEquipo = null;
        Equipo nuevoEquipo = null;
        boolean repetir;
        LigaServicio ligaServicio = new LigaServicio();
        do{
            repetir = false;
            try {
                System.out.print("Ingrese el nombre del equipo: ");
                nombreEquipo = teclado.nextLine();
                nuevoEquipo = ligaServicio.crearEquipo(nombreEquipo, laLiga);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                repetir = true;
            }
        }while(repetir);
        return nuevoEquipo;
    }
    public static void mostrarGoleadorDeLaLiga(Liga laLiga){
        Jugador jugador;
        jugador = laLiga.obtenerGoleador();
        System.out.println("El goleador de la liga es " + jugador.getNombre());
    }

    public static void asignarGolesPartido(Scanner teclado, Liga laLiga){
        Equipo local, visitante;
        LigaServicio ligaServicio = new LigaServicio();
        String nombreJugador;
        boolean repetir;
        do{
            repetir = false;
            try{
                local = solicitarNombreDeUnEquipoExistente("Ingrese el nombre del equipo local del partido: ", teclado, laLiga);
                visitante = solicitarNombreDeUnEquipoExistente("Ingrese el nombre del equipo visitante del partido: ", teclado, laLiga);
                System.out.print("Ingrese el nombre del jugador: ");
                nombreJugador = teclado.nextLine();
                ligaServicio.validarNombreJugador(nombreJugador);
                ligaServicio.asignarGolesPartido(local, visitante, nombreJugador, laLiga);
            }    
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                repetir = true;
            }
        }while(repetir);
    }

    public static Equipo solicitarNombreDeUnEquipoExistente(String mensaje, Scanner teclado, Liga laLiga){
        String nombreEquipo = null;
        Equipo equipo = null;
        boolean repetir;
        LigaServicio ligaServicio = new LigaServicio();
        do{
            repetir = false;
            try {
                System.out.print(mensaje);
                nombreEquipo = teclado.nextLine();
                equipo = ligaServicio.encontrarEquipo(nombreEquipo, laLiga);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                repetir = true;
            }
        }while(repetir);
        return equipo;
    }

    public static void mostrarPromedioGolesPartido(Liga laLiga){
        double promedio;
        promedio = laLiga.obtenerPromGolesPorPartido();
        System.out.printf("El promedio de goles por partidos es %.2f", promedio);
    }

    public static void exportarJugadoresACSV(Scanner teclado, Liga laLiga){
        Equipo equipo;
        equipo = solicitarNombreDeUnEquipoExistente("Ingrese el nombre del equipo que quiere exportar sus datos: ", teclado, laLiga);
        equipo.exportarJugadoresACSV();
    }

    public static void registrarPartido(Scanner teclado, Liga laLiga){
        Equipo local, visitante;
        LigaServicio ligaServicio = new LigaServicio();
        boolean repetir;
        do{
            repetir = false;
            try{
                local = solicitarNombreDeUnEquipoExistente("Ingrese el nombre del equipo local: ", teclado, laLiga);
                visitante = solicitarNombreDeUnEquipoExistente("Ingrese el nombre del equipo visitante: ", teclado, laLiga);
                ligaServicio.registrarPartido(local, visitante, laLiga);
            }    
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                repetir = true;
            }
        }while(repetir);
    }

    public static void registrarJugador(Scanner teclado, Liga laLiga){
        String esTitular, nombre = null;
        int edad = 0;
        int cantGoles = 0;
        int minutosJugados = 0;
        int cantPartidosIngresados = 0;
        Equipo equipo;
        boolean repetir;
        LigaServicio ligaServicio = new LigaServicio();
        equipo = solicitarNombreDeUnEquipoExistente("Ingrese el nombre del equipo al que pertenecerá el jugador: ", teclado, laLiga);
        System.out.print("\n¿Se trata de un jugador titular? (S / N): ");
        esTitular = teclado.next();
        while(!esTitular.equalsIgnoreCase("s") && !esTitular.equalsIgnoreCase("n")){
            System.out.print("No ha respondido correctamente. Vuelva a intentarlo ¿se trata de un jugador titular? (S / N): ");
            esTitular = teclado.next();
        }
        teclado.nextLine();
        do{
            repetir = false;
            try{
                System.out.print("\nIngrese el nombre del jugador: ");
                nombre = teclado.nextLine();
                ligaServicio.registrarNombreJugador(nombre, laLiga);
            }
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                repetir = true;
            }
        }while(repetir);
        do{
            repetir = false;
            try{
                System.out.print("\nIngrese la edad del jugador: ");
                edad = teclado.nextInt();
                ligaServicio.registrarEdadJugador(edad);
            }
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                repetir = true;
            }
        }while(repetir);
        do{
            repetir = false;
            try{
                System.out.print("\nIngrese cantidad de goles del jugador: ");
                cantGoles = teclado.nextInt();
                ligaServicio.registrarGolesJugador(cantGoles);
            }
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                repetir = true;
            }
        }while(repetir);
        if(esTitular.equalsIgnoreCase("S")){
            do{
                repetir = false;
                try{
                    System.out.print("\nIngrese cantidad de minutos ya jugados por el jugador: ");
                    minutosJugados = teclado.nextInt();
                    ligaServicio.registrarMinutosJugador(minutosJugados);
                }
                catch(IllegalArgumentException e){
                    System.out.println(e.getMessage());
                    repetir = true;
                }
            }while(repetir);
            equipo.agregarJugadorTitular(new JugadorTitular(nombre, edad, cantGoles, minutosJugados));
        }
        else{
            do{
                repetir = false;
                try{
                    System.out.print("\nIngrese cantidad de partidos que lleva ya jugado el jugador: ");
                    cantPartidosIngresados = teclado.nextInt();
                    ligaServicio.registrarPartidosJugadosJugador(cantPartidosIngresados);
                }
                catch(IllegalArgumentException e){
                    System.out.println(e.getMessage());
                    repetir = true;
                }
            }while(repetir);
            equipo.agregarJugadorSuplente(new JugadorSuplente(nombre, edad, cantGoles, cantPartidosIngresados));
        }
        teclado.nextLine();
    }
}
