package es.Poo.sieteymedio;

import java.util.Scanner;

/*
 * Lo he documentado todo, tal y como te pregunte el viernes en clase, a mi me parece mas que lo ha hecho una IA
 * asi que de otra manera pero espero que haberlo documentado todo no lo haga mas IA en los detectores la verdad.
 */

/**
 * Clase principal que inicia el juego del Siete y Medio.
 * Se encarga de:
 * <ul>
 *   <li>El modo de juego</li>
 *   <li>Crear los jugadores</li>
 *   <li>Tipo de baraja</li>
 *   <li>La partida</li>
 * </ul>
 */
public class Main {
    /** Scanner para leer la entrada del usuario */
    private static Scanner scanner;

    /**
     * La parte principal del programa se encuentra aqui (todo lo mencionado arriba basicamente)
     * 
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        
        mostrarCabecera();
        int modoJuego = seleccionarModoJuego();
        
        Jugador jugador1 = crearJugadorUno();
        Jugador jugador2 = crearJugadorDos(modoJuego, jugador1.getAlias());
        
        Baraja.Tipo tipoBaraja = seleccionarTipoBaraja();
        Baraja baraja = crearBaraja(tipoBaraja);
        
        iniciarPartida(jugador1, jugador2, baraja, tipoBaraja);
        
        scanner.close();
    }

    /**
     * Titulo 
     */
    private static void mostrarCabecera() {
    // (la idea de esto me la dio Sandra y me gusto, los colores porque me acorde de la practica con Jesuja pero el codigo de color lo busque en internet)
        System.out.println("\033[1;35m"); // Cambiar el color a magenta y negrita
        System.out.println("╔═════════════════════════════════╗");
        System.out.println("║         \033[1;33m★  7 Y MEDIO  ★\033[1;35m        ║"); // Color amarillo para el texto
        System.out.println("╚═════════════════════════════════╝");
        System.out.println("\033[0m"); // Restablecer color
    }

    /**
     * Valida el modo de juego seleccionado.
     * 
     * @return 1 para modo un jugador, 2 para modo dos jugadores
     */
    private static int seleccionarModoJuego() {
        System.out.println("\nSeleccione modo de juego:");
        System.out.println("1. Un jugador");
        System.out.println("2. Dos jugadores");
        System.out.print("Introduzca su elección (1-2): ");
        int modo = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return modo;
    }

    /**
     * Crea el jugador.
     * 
     * @return Jugador creado con los datos introducidos
     */
    private static Jugador crearJugadorUno() {
        System.out.println("\nDatos del Jugador 1:");
        return crearJugador(1);
    }

    /**
     * Solicita los datos para crear un jugador.
     * 
     * @param numJugador número del jugador (1 o 2)
     * @return nuevo jugador con los datos introducidos
     */
    private static Jugador crearJugador(int numJugador) {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Primer apellido: ");
        String apellido1 = scanner.nextLine();
        System.out.print("Segundo apellido: ");
        String apellido2 = scanner.nextLine();
        System.out.print("Alias: ");
        String alias = scanner.nextLine();
        
        return new Jugador(nombre, apellido1, apellido2, alias, false);
    }

    /**
     * Crea el segundo jugador según el modo de juego seleccionado.
     * 
     * @param modoJuego modo de juego seleccionado (1 o 2)
     * @param aliasJugador1 alias del primer jugador para evitar duplicados
     * @return jugador creado (automático o humano según el modo)
     */
    private static Jugador crearJugadorDos(int modoJuego, String aliasJugador1) {
        if (modoJuego == 1) {
            Jugador jugadorAutomatico = new Jugador("CPU", "Auto", "Mático", "La Máquina", true);
            System.out.println("\nJugador 2 (automático) creado con alias: La Máquina");
            return jugadorAutomatico;
        } else {
            return crearJugadorDosHumano(aliasJugador1);
        }
    }

    /**
     * Crea el segundo jugador humano verificando que su alias sea único.
     * 
     * @param aliasJugador1 alias del primer jugador para evitar duplicados
     * @return segundo jugador humano creado
     */
    private static Jugador crearJugadorDosHumano(String aliasJugador1) {
        System.out.println("\nDatos del Jugador 2:");
        Jugador jugador2;
        do {
            jugador2 = crearJugador(2);
            if (jugador2.getAlias().equals(aliasJugador1)) {
                System.out.println("El alias debe ser diferente del Jugador 1.");
                System.out.print("Introduce un nuevo alias: ");
                jugador2 = new Jugador(
                    jugador2.getNombre(),
                    jugador2.getApellido1(),
                    jugador2.getApellido2(),
                    scanner.nextLine(),
                    false
                );
            }
        } while (jugador2.getAlias().equals(aliasJugador1));
        return jugador2;
    }

    /**
     * Solicita y valida el tipo de baraja seleccionado.
     * 
     * @return tipo de baraja seleccionado (ESPANOLA o FRANCESA)
     */
    private static Baraja.Tipo seleccionarTipoBaraja() {
        while (true) {
            System.out.print("\nSeleccione tipo de baraja (F: Francesa, E: Española): ");
            String input = scanner.nextLine().toUpperCase();
            if (input.equals("E")) {
                return Baraja.Tipo.ESPANOLA;
            } else if (input.equals("F")) {
                return Baraja.Tipo.FRANCESA;
            }
            System.out.println("Tipo de baraja no válido. Por favor, seleccione F o E.");
        }
    }

    /**
     * Crea una baraja del tipo especificado.
     * 
     * @param tipoBaraja tipo de baraja a crear
     * @return nueva baraja creada
     */
    private static Baraja crearBaraja(Baraja.Tipo tipoBaraja) {
        return new Baraja(tipoBaraja);
    }

    /**
     * Inicia una partida con los jugadores y la baraja creados.
     * 
     * @param jugador1 primer jugador
     * @param jugador2 segundo jugador
     * @param baraja baraja a utilizar
     * @param tipoBaraja tipo de baraja seleccionado
     */
    private static void iniciarPartida(Jugador jugador1, Jugador jugador2, Baraja baraja, Baraja.Tipo tipoBaraja) {
        Juego juego = new Juego(jugador1, jugador2, baraja, tipoBaraja);
        juego.iniciarJuego();
    }
}