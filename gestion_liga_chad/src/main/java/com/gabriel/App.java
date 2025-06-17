package com.gabriel;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import com.gabriel.dominio.*;
import com.gabriel.servicios.*;
import java.util.Scanner;
import com.gabriel.dominio.*;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final int CANT_OPCIONES = 13;
    private static final int OPCIÓN_REGISTRAR_JUGADORES = 1;
    private static final int OPCIÓN_CREAR_EQUIPO = 2;
    public static final int OPCIÓN_EXPORTAR_JUGADORES_CSV = 12;
    public static final int OPCIÓN_REGISTRAR_PARTIDO = 3;
    public static final int OPCIÓN_ASIGNAR_GOLES_PARTIDO = 4;
    public static final int OPCIÓN_MOSTRAR_JUGADORES_TIPO = 5;
    public static final int OPCIÓN_MOSTRAR_GOLEADOR_LIGA = 6;
    public static final int OPCIÓN_MOSTRAR_PROMEDIO_GOLES_POR_PARTIDO = 7;
    public static final int OPCIÓN_MOSTRAR_RANKING_EQUIPOS_POR_GOLES = 8;
    public static final int OPCIÓN_TRANSFERIR_JUGADOR = 9;
    public static final int OPCIÓN_MOSTRAR_SUPLENTES_NUNCA_INGRESADOS = 10;
    public static final int OPCIÓN_MOSTRAR_TITULAR_MÁX_MINUTOS = 11;
    public static final int CANT_INTENTOS_GOLES = 50;
    public static final int DURACIÓN_PARTIDOS = 90;
    public static void main( String[] args )
    {
        Liga ligaChad = new Liga();
        int opciónMenú;
        Equipo nuevoEquipo = null;
        Scanner teclado = new Scanner(System.in);
        LigaServicio ligaServicio = new LigaServicio();
        /*Random random = new Random();
        Equipo boca, river, independiente, vélez;
        Equipo equipoLocalSorteado, equipoVisitanteSorteado;
        Jugador jugadorGoleador;
        int númeroSorteo, minutoGol, máxCantPartidos = 0, minutoCambio;
        Partido partidoSorteado;
        JugadorTitular jugadorSale;
        JugadorSuplente jugadorEntra;
        ligaChad.agregarEquipo(new Equipo("Boca"));
        ligaChad.agregarEquipo(new Equipo("River"));
        ligaChad.agregarEquipo(new Equipo("Vélez"));
        ligaChad.agregarEquipo(new Equipo("Independiente"));

        boca = ligaChad.buscarEquipoPorNombre("Boca");
        boca.agregarJugadorTitular(new JugadorTitular("Agustín Rossi", 25));
        boca.agregarJugadorTitular(new JugadorTitular("Marcos Rojo", 32));
        boca.agregarJugadorSuplente(new JugadorSuplente("Darío Benedetto", 33));
        boca.agregarJugadorSuplente(new JugadorSuplente("Sebastián Villa", 27));

        river = ligaChad.buscarEquipoPorNombre("River");
        river.agregarJugadorTitular(new JugadorTitular("Franco Armani", 35));
        river.agregarJugadorTitular(new JugadorTitular("Enzo Pérez", 36));
        river.agregarJugadorSuplente(new JugadorSuplente("Julián Álvarez", 23));
        river.agregarJugadorSuplente(new JugadorSuplente("Esequiel Barco", 24));

        vélez = ligaChad.buscarEquipoPorNombre("Vélez");
        vélez.agregarJugadorTitular(new JugadorTitular("Luca Orellano", 21));
        vélez.agregarJugadorTitular(new JugadorTitular("Matías Vargas", 25));
        vélez.agregarJugadorSuplente(new JugadorSuplente("Thiago Almada", 21));
        vélez.agregarJugadorSuplente(new JugadorSuplente("Lucas Pratto", 34));

        independiente = ligaChad.buscarEquipoPorNombre("Independiente");
        independiente.agregarJugadorTitular(new JugadorTitular("Silvio Romero", 33));
        independiente.agregarJugadorTitular(new JugadorTitular("Domingo Blanco", 28));
        independiente.agregarJugadorSuplente(new JugadorSuplente("Andrés Roa", 26));
        independiente.agregarJugadorSuplente(new JugadorSuplente("Alan Soñora", 24));

        for(int p = 1; p < ligaChad.getEquipos().size(); p++){
            máxCantPartidos += p;
        }
        for(int k = 0; k < máxCantPartidos * 1.5; k++){
            equipoLocalSorteado = ligaChad.getEquipos().get(random.nextInt(ligaChad.getEquipos().size()));
            equipoVisitanteSorteado = ligaChad.getEquipos().get(random.nextInt(ligaChad.getEquipos().size()));
            System.out.println("\n\nSe intentará registrar el partido " + equipoLocalSorteado.getNombre() + " vs. " + equipoVisitanteSorteado.getNombre());
            try{
                ligaServicio.registrarPartido(equipoLocalSorteado, equipoVisitanteSorteado, ligaChad);
                System.out.println("El partido fue registrado correctamente");
            }
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        for(int j = 0; j < CANT_INTENTOS_GOLES; j++){
            númeroSorteo = random.nextInt(CANT_INTENTOS_GOLES);
            if(númeroSorteo % 4 == 0){
                equipoLocalSorteado = ligaChad.getEquipos().get(random.nextInt(ligaChad.getEquipos().size()));
                equipoVisitanteSorteado = ligaChad.getEquipos().get(random.nextInt(ligaChad.getEquipos().size()));
            }
            else{
                partidoSorteado = ligaChad.getPartidos().get(random.nextInt(ligaChad.getPartidos().size()));
                equipoLocalSorteado = partidoSorteado.getLocal();
                equipoVisitanteSorteado = partidoSorteado.getVisitante();
            }
            if(númeroSorteo % 2 == 0 && númeroSorteo >= 3 * númeroSorteo / 4){
                jugadorGoleador = (Jugador) equipoLocalSorteado.getJugadoresTitulares().get(random.nextInt(equipoLocalSorteado.getJugadoresTitulares().size()));
            }
            else if(númeroSorteo % 2 == 0 && númeroSorteo >= númeroSorteo / 2){
                jugadorGoleador = (Jugador) equipoLocalSorteado.getJugadoresSuplentes().get(random.nextInt(equipoLocalSorteado.getJugadoresSuplentes().size()));
            }
            if(númeroSorteo % 2 == 0 && númeroSorteo >= númeroSorteo / 4){
                jugadorGoleador = (Jugador) equipoVisitanteSorteado.getJugadoresTitulares().get(random.nextInt(equipoVisitanteSorteado.getJugadoresTitulares().size()));
            }
            else if(númeroSorteo % 2 == 0){
                jugadorGoleador = (Jugador) equipoVisitanteSorteado.getJugadoresSuplentes().get(random.nextInt(equipoVisitanteSorteado.getJugadoresSuplentes().size()));
            }
            else if(númeroSorteo >= númeroSorteo / 2){
                jugadorGoleador = (Jugador) ligaChad.getEquipos().get(random.nextInt(ligaChad.getEquipos().size())).getJugadoresTitulares().get(random.nextInt(equipoVisitanteSorteado.getJugadoresTitulares().size()));
            }
            else{
                jugadorGoleador = (Jugador) ligaChad.getEquipos().get(random.nextInt(ligaChad.getEquipos().size())).getJugadoresSuplentes().get(random.nextInt(equipoVisitanteSorteado.getJugadoresSuplentes().size()));
            }
            minutoGol = random.nextInt(111) - 20;
            System.out.println("\n\nSe intentará registrar un gol del jugador " + jugadorGoleador.getNombre() + " en el partido " + equipoLocalSorteado.getNombre() + " vs. " + equipoVisitanteSorteado.getNombre() + " al minuto " + minutoGol);
            try{
                ligaServicio.asignarGolesPartido(equipoLocalSorteado, equipoVisitanteSorteado, jugadorGoleador.getNombre(), minutoGol, ligaChad);
                System.out.println("El gol se registró correctamente");
                System.out.println("Este jugador lleva " + jugadorGoleador.getCantGoles() + " goles en la liga.");
                System.out.println("El partido está yendo " + equipoLocalSorteado.getNombre() + " " + ligaChad.obtenerPartido(equipoLocalSorteado, equipoVisitanteSorteado).obtenerGolesLocal() + " vs. " + equipoVisitanteSorteado.getNombre() + " " + ligaChad.obtenerPartido(equipoLocalSorteado, equipoVisitanteSorteado).obtenerGolesVisitante());
                minutoCambio = random.nextInt(DURACIÓN_PARTIDOS);
                if(númeroSorteo >= CANT_INTENTOS_GOLES / 2){
                    jugadorSale = equipoLocalSorteado.getJugadoresTitulares().get(random.nextInt(equipoLocalSorteado.getJugadoresTitulares().size()));
                    jugadorEntra = equipoLocalSorteado.getJugadoresSuplentes().get(random.nextInt(equipoLocalSorteado.getJugadoresSuplentes().size()));
                    System.out.println("Se intentará llevar a cabo el siguiente cambio: sale " + jugadorSale.getNombre() + " y entra " + jugadorEntra.getNombre() + " en " + equipoLocalSorteado.getNombre() + " a los " + minutoCambio + " minutos del partido.");
                    ligaChad.obtenerPartido(equipoLocalSorteado, equipoVisitanteSorteado).realizarCambio(jugadorSale, jugadorEntra, minutoCambio);
                }
                else{
                    jugadorSale = equipoVisitanteSorteado.getJugadoresTitulares().get(random.nextInt(equipoVisitanteSorteado.getJugadoresTitulares().size()));
                    jugadorEntra = equipoVisitanteSorteado.getJugadoresSuplentes().get(random.nextInt(equipoVisitanteSorteado.getJugadoresSuplentes().size()));
                    System.out.println("Se intentará llevar a cabo el siguiente cambio: sale " + jugadorSale.getNombre() + " y entra " + jugadorEntra.getNombre() + " en " + equipoVisitanteSorteado.getNombre() + " a los " + minutoCambio + " minutos del partido.");
                    ligaChad.obtenerPartido(equipoLocalSorteado, equipoVisitanteSorteado).realizarCambio(jugadorSale, jugadorEntra, minutoCambio);
                }
                System.out.println("El cambio se llevó correctamente.");
            }
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        */
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
                        System.out.println("El gol fue cargado correctamente :D");
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
                    transferirJugador(teclado, ligaChad);
                    System.out.println("Transferencia exitosa :D");
                    break;

                case OPCIÓN_MOSTRAR_SUPLENTES_NUNCA_INGRESADOS:
                    mostrarSuplentesNuncaIngresados(ligaChad);
                    break;

                case OPCIÓN_MOSTRAR_TITULAR_MÁX_MINUTOS:
                    ligaChad.mostrarJugadorTitularConMásMinutos();
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

    public static void transferirJugador(Scanner teclado, Liga laLiga){
        String nombreJugador;
        Equipo equipoOrigen, equipoDestino;
        EquipoServicio equipoServicio = new EquipoServicio();
        boolean repetir;
        do{
            repetir = false;
            try{
                System.out.print("Ingrese el nombre del jugador: ");
                nombreJugador = teclado.nextLine();
                equipoOrigen = solicitarNombreDeUnEquipoExistente("Ingrese el nombre del equipo de origen: ", teclado, laLiga);
                equipoDestino = solicitarNombreDeUnEquipoExistente("Ingrese el nombre del equipo destino: ", teclado, laLiga);
                equipoServicio.transferirJugador(equipoOrigen, equipoDestino, nombreJugador);
            }
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                repetir = true;
            }
        }while(repetir);
    }

    public static void asignarGolesPartido(Scanner teclado, Liga laLiga){
        Equipo local, visitante;
        int minuto;
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
                System.out.print("Ingrese al minuto que fue hecho el gol: ");
                minuto = teclado.nextInt();
                teclado.nextLine();
                ligaServicio.asignarGolesPartido(local, visitante, nombreJugador, minuto, laLiga);
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
        HashMap<Equipo, Double> promediosGoles = laLiga.obtenerPromGolesPorPartido();
        System.out.printf("%-30s%s\n", "Equipo", "goles/partido");
        for(Equipo equipo : promediosGoles.keySet()){
            System.out.printf("%-30s%.3f\n", equipo.getNombre(), promediosGoles.get(equipo).doubleValue());
        }
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

    public static void mostrarSuplentesNuncaIngresados(Liga laLiga){
        ArrayList<JugadorSuplente> suplentesSinPartidos;
        suplentesSinPartidos = laLiga.obtenerSuplentesSinPartidos();
        System.out.println("Jugadores suplentes de la liga nunca ingresados:\n");
        for(JugadorSuplente jugador : suplentesSinPartidos){
            System.out.println(jugador.getNombre());
        }
    }

    public static void registrarJugador(Scanner teclado, Liga laLiga){
        String esTitular, nombre = null;
        int edad = 0;
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
        if(esTitular.equalsIgnoreCase("S")){
            equipo.agregarJugadorTitular(new JugadorTitular(nombre, edad));
        }
        else{
            equipo.agregarJugadorSuplente(new JugadorSuplente(nombre, edad));
        }
        teclado.nextLine();
    }
}
