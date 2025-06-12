package com.gabriel;

import java.util.Scanner;
import com.gabriel.dominio.*;
import com.gabriel.servicios.*;

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
        System.out.println("1) Registrar jugadores.");
        System.out.println("2) Crear equipos.");
        System.out.println("3) Registrar partidos entre equipos.");
        System.out.println("4) Asignar goles a jugadores durante un partido.");
        System.out.println("5) Mostrar listado de todos los jugadores y su tipo (titular o suplente).");
        System.out.println("6) Calcular y mostrar el goleador de la liga.");
        System.out.println("7) Mostrar el promedio de goles por partido de cada equipo.");
        System.out.println("8) Mostrar el ranking de equipos por cantidad de goles anotados.");
        System.out.println("9) Permitir transferir un jugador de un equipo a otro.");
        System.out.println("10) Mostrar jugadores suplentes que nunca hayan ingresado.");
        System.out.println("11) Mostrar el jugador titular con mayor cantidad de minutos jugados.");
        System.out.println("12) Exportar en un archivo .csv los jugadores (titulares y suplentes) de un equipo dado.");
        System.out.println("13) Listar reporte general de la liga.");
        System.out.println("14) Listar reporte de un equipo.");
        System.out.println("15) Salir.");
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

    public static Equipo solicitarNombreDeUnEquipoExistente(Scanner teclado, Liga laLiga){
        String nombreEquipo = null;
        Equipo nuevoEquipo = null;
        boolean repetir;
        LigaServicio ligaServicio = new LigaServicio();
        do{
            repetir = false;
            try {
                System.out.print("Ingrese el nombre del equipo: ");
                nombreEquipo = teclado.nextLine();
                nuevoEquipo = ligaServicio.buscarEquipoPorNombre(nombreEquipo, laLiga);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                repetir = true;
            }
        }while(repetir);
        return nuevoEquipo;
    }

    public static void exportarJugadoresACSV(Scanner teclado, Liga laLiga){
        Equipo equipo;
        equipo = solicitarNombreDeUnEquipoExistente(teclado, laLiga);
        equipo.exportarJugadoresACSV();
    }

    public static void registrarPartido(Scanner teclado, Liga laLiga){
        Equipo local, visitante;
        do{
            System.out.println("Comenzaremos ingresando el nombre del equipo que será local en el partido\n");
            local = solicitarNombreDeUnEquipoExistente(teclado, laLiga);
            System.out.println("A continuación ingresamos el nombre del equipo que será visitante en el partido\n");
            do{
                visitante = solicitarNombreDeUnEquipoExistente(teclado, laLiga);
                if(visitante.getNombre().equals(local.getNombre())){
                    System.out.println("El equipo visitante no puede ser el mismo que el equipo local. Intentemos nuevamente\n");
                }
            }while(visitante.getNombre().equals(local.getNombre()));
            if(laLiga.existePartido(local, visitante)){
                System.out.println("Ese partido ya existe. Intentemos nuevamente\n");
            }
        }while(visitante.getNombre().equals(local.getNombre()));
        laLiga.agregarPartido(local, visitante);
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
        equipo = solicitarNombreDeUnEquipoExistente(teclado, laLiga);
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
