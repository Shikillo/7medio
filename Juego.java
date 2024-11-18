package es.Poo.sieteymedio;
import java.util.Scanner;

/*Es un poco extra tener una clase especifica para el juego, lo se, pero empeze el proyecto como 4 veces
no queria copiarlo todo al nuevo main y se quedo todo por aqui ademas me ha ayudado a no tener que
editar el main cuando he añadido las tandas asique realmente me ha servido bien. 
Los textos, no son para excusarme es para ir contando un poco mi experiencia*/

/**
 * Clase del funcionamiento del Siete y Medio.
 * Permite jugar múltiples tandas y mantiene un registro de los resultados.
 * 
 * Características principales:
 * <ul>
 *   <li>Soporta modo un jugador (contra la máquina) y dos jugadores</li>
 *   <li>Jugar múltiples tandas consecutivas</li>
 *   <li>Calcula y muestra resultados</li>
 *   <li>Pntuación del Siete y Medio</li>
 * </ul>
 */
public class Juego {
    private Scanner scanner;
    private Jugador jugador1;
    private Jugador jugador2;
    private Baraja baraja;
    private Baraja.Tipo tipoBaraja;
    private int numTandas;  // Las tandas
    private String[] resultadosTandas;  // Para guardar resultados

    /**
     * Nueva partida.
     */
    public Juego(Jugador jugador1, Jugador jugador2, Baraja baraja, Baraja.Tipo tipoBaraja) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.baraja = baraja;
        this.tipoBaraja = tipoBaraja;
        this.scanner = new Scanner(System.in);
        solicitarNumeroTandas();
        this.resultadosTandas = new String[numTandas];
    }

    /**
     * Número de tandas a jugar.
     */
    private void solicitarNumeroTandas() {
        while (true) {
            try {
                System.out.print("\nIntroduzca el número de tandas a jugar: ");
                numTandas = Integer.parseInt(scanner.nextLine());
                if (numTandas <= 0) {
                    throw new IllegalArgumentException("El número de tandas debe ser mayor que 0");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduzca un número válido.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Juego con todas las tandas.
     */
    public void iniciarJuego() {
        int victoriasJ1 = 0;
        int victoriasJ2 = 0;
        int empates = 0;

        for (int i = 1; i <= numTandas; i++) {
            System.out.println("\n=== TANDA " + i + " DE " + numTandas + " ===");
            
            // Resetea las manos y crea otra baraja
            jugador1.getMano().clear();
            jugador2.getMano().clear();
            baraja = new Baraja(tipoBaraja);
            baraja.barajar();

            // Jugar la tanda
            if (jugador2.isEsAutomatico()) {
                modoUnJugador();
            } else {
                modoDosJugadores();
            }

            // Guardar resultado de la tanda
            double puntuacion1 = calcularPuntuacion(jugador1);
            double puntuacion2 = calcularPuntuacion(jugador2);
            
            if (puntuacion1 > 7.5) {
                if (puntuacion2 > 7.5) {
                    empates++;
                    resultadosTandas[i-1] = "Tanda " + i + ": Empate - Ambos se pasaron";
                } else {
                    victoriasJ2++;
                    resultadosTandas[i-1] = "Tanda " + i + ": Ganó " + jugador2.getAlias();
                }
            } else if (puntuacion2 > 7.5) {
                victoriasJ1++;
                resultadosTandas[i-1] = "Tanda " + i + ": Ganó " + jugador1.getAlias();
            } else if (puntuacion1 > puntuacion2) {
                victoriasJ1++;
                resultadosTandas[i-1] = "Tanda " + i + ": Ganó " + jugador1.getAlias();
            } else if (puntuacion2 > puntuacion1) {
                victoriasJ2++;
                resultadosTandas[i-1] = "Tanda " + i + ": Ganó " + jugador2.getAlias();
            } else {
                empates++;
                resultadosTandas[i-1] = "Tanda " + i + ": Empate";
            }
            
            resultadosTandas[i-1] += " (" + jugador1.getAlias() + ": " + puntuacion1 + 
                                    " vs " + jugador2.getAlias() + ": " + puntuacion2 + ")";
        }

        mostrarResumenFinal(victoriasJ1, victoriasJ2, empates);
    }

    /**
     * Muestra el resumen final de todas las tandas jugadas.
     */
    private void mostrarResumenFinal(int victoriasJ1, int victoriasJ2, int empates) {
        System.out.println("\n=== RESUMEN DE TODAS LAS TANDAS ===");
        for (String resultado : resultadosTandas) {
            System.out.println(resultado);
        }

        System.out.println("\n=== RESULTADO FINAL ===");
        System.out.println(jugador1.getAlias() + ": " + victoriasJ1 + " victorias");
        System.out.println(jugador2.getAlias() + ": " + victoriasJ2 + " victorias");
        System.out.println("Empates: " + empates);

        if (victoriasJ1 > victoriasJ2) {
            System.out.println("\n¡" + jugador1.getAlias() + " es el GANADOR FINAL!");
        } else if (victoriasJ2 > victoriasJ1) {
            System.out.println("\n¡" + jugador2.getAlias() + " es el GANADOR FINAL!");
        } else {
            System.out.println("\n¡El juego ha terminado en EMPATE!");
        }
    }
    /**
     * Manejo del modo un jugador con la máquina.
     */
    private void modoUnJugador() {
        System.out.println("\n*** UN JUGADOR ***");
        System.out.println("Jugador: " + jugador1.getAlias());
        System.out.println("Máquina: " + jugador2.getAlias());
        
        jugarTurnoHumano(jugador1);
        if (calcularPuntuacion(jugador1) <= 7.5) {
            jugarTurnoMaquina();
        }
        
        mostrarResultadoFinal();
    }

    /**
     * Manejo del modo dos jugadores con sus turnos respectivos.
     */
    private void modoDosJugadores() {
        System.out.println("\n*** DOS JUGADORES ***");
        System.out.println("Jugador 1: " + jugador1.getAlias());
        System.out.println("Jugador 2: " + jugador2.getAlias());
        
        jugarTurnoHumano(jugador1);
        jugarTurnoHumano(jugador2);
        
        mostrarResultadoFinal();
    }
    /**
     * Gestiona el turno de un jugador humano.
     * Permite al jugador pedir cartas hasta que se plante o se pase de 7.5.
     * 
     * @param jugador Jugador que está jugando el turno
     */
    private void jugarTurnoHumano(Jugador jugador) {
        boolean seguirJugando = true;
        
        while (seguirJugando) {
            Carta carta = baraja.obtenerCarta();
            jugador.getMano().add(carta);
            
            System.out.println("\nHas recibido: " + carta);
            mostrarEstadoJugador(jugador);
            
            double puntuacion = calcularPuntuacion(jugador);
            if (puntuacion > 7.5) {
                System.out.println("¡Te has pasado!");
                break;
            }
            
            String respuesta;
            do {
                System.out.print("¿Quiere otra carta? (S/N): ");
                respuesta = scanner.nextLine().toUpperCase();
                if (!respuesta.equals("S") && !respuesta.equals("N")) {
                    System.out.println("Por favor, responda solo con 'S' o 'N'");
                }
            } while (!respuesta.equals("S") && !respuesta.equals("N"));
            
            seguirJugando = respuesta.equals("S");
        }
    }

    /**
     * Gestiona el turno de la máquina.
     * La máquina pide cartas mientras tenga menos de 6 puntos.
     */
    private void jugarTurnoMaquina() {
        System.out.println("\nTurno de la máquina:");
        
        while (calcularPuntuacion(jugador2) < 6.0) {
            Carta carta = baraja.obtenerCarta();
            jugador2.getMano().add(carta);
            System.out.println("La máquina ha sacado: " + carta);
            mostrarEstadoJugador(jugador2);
        }
    }

    /**
     * Calcula la puntuación total de un jugador según sus cartas.
     * 
     * @param jugador Jugador cuya puntuación se va a calcular
     * @return Puntuación total del jugador
     */
    private double calcularPuntuacion(Jugador jugador) {
        double puntuacion = 0;
        for (Carta carta : jugador.getMano()) {
            if (tipoBaraja == Baraja.Tipo.ESPANOLA) {
                if (carta.getnumero() >= 1 && carta.getnumero() <= 7) {
                    puntuacion += carta.getnumero();
                } else if (carta.getnumero() >= 10) {
                    puntuacion += 0.5;
                }
            } else {
                if (carta.getnumero() == 1 || carta.getnumero() >= 8) {
                    puntuacion += 0.5;
                } else {
                    puntuacion += carta.getnumero();
                }
            }
        }
        return puntuacion;
    }

    /**
     * Muestra el estado actual del jugador.
     * Cartas y puntuación total.
     * 
     * @param jugador Jugador cuyo estado se va a mostrar
     */
    private void mostrarEstadoJugador(Jugador jugador) {
        System.out.println("Tus cartas: " + jugador.getMano());
        System.out.println("Puntuación: " + calcularPuntuacion(jugador));
    }

    /**
     * Muestra el resultado final de la partida.
     * Compara las puntuaciones y determina el ganador.
     */
    private void mostrarResultadoFinal() {
        System.out.println("\n*** RESULTADO FINAL ***");
        double puntuacion1 = calcularPuntuacion(jugador1);
        double puntuacion2 = calcularPuntuacion(jugador2);
        
        System.out.println(jugador1.getAlias() + ": " + puntuacion1);
        System.out.println(jugador2.getAlias() + ": " + puntuacion2);
        
        if (puntuacion1 > 7.5) {
            if (puntuacion2 > 7.5) {
                System.out.println("\n¡Empate!");
            } else {
                System.out.println("\n¡Gana " + jugador2.getAlias() + "!");
            }
        } else if (puntuacion2 > 7.5) {
            System.out.println("\n¡Gana " + jugador1.getAlias() + "!");
        } else if (puntuacion1 > puntuacion2) {
            System.out.println("\n¡Gana " + jugador1.getAlias() + "!");
        } else if (puntuacion2 > puntuacion1) {
            System.out.println("\n¡Gana " + jugador2.getAlias() + "!");
        } else {
            System.out.println("\n¡Empate! No tengo ni ideia de como lo has conseguido (yo lo he intentado sacar como empate al menos 30 veces)");
        }
    }
}