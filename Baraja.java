package es.Poo.sieteymedio;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase usada para generar una baraja, sea española o francesa que contiene 52 cartas en el caso de la francesa
 * o 40 en el caso de que sea la baraja española.
 */
public class Baraja {
    /** Lista de las cartas de la baraja */
    private ArrayList<Carta> cartas;
    
    /** Tipo de baraja*/
    private Tipo tipo;

    /**
     * Enumeración que define los tipos de baraja disponibles.
     */
    public enum Tipo {
        /** Baraja española*/
        ESPANOLA(new String[]{"Oros", "Copas", "Espadas", "Bastos"}, 12),
        
        /** Baraja francesa*/
        FRANCESA(new String[]{"Picas", "Rombos", "Tréboles", "Corazones"}, 13);

        /** Array con los palos de la baraja */
        private final String[] palos;
        
        /** Número máximo de cartas por palo */
        private final int maxNumero;

        /**
         * Constructor del enum Tipo.
         * @param palos Array con los nombres de los palos
         * @param maxNumero Número más alto de carta en cada palo
         */
        Tipo(String[] palos, int maxNumero) {
            this.palos = palos;
            this.maxNumero = maxNumero;
        }
    }

    /**
     * Constructor que crea una nueva baraja del tipo especificado.
     * @param tipo Tipo de baraja a crear (ESPANOLA o FRANCESA)
     */
    public Baraja(Tipo tipo) {
        this.tipo = tipo;
        cartas = new ArrayList<>();
        
        for (String palo : tipo.palos) {
            for (int numero = 1; numero <= tipo.maxNumero; numero++) {
                if (tipo == Tipo.ESPANOLA) {
                    if (numero != 8 && numero != 9) {  // En la baraja española no hay 8 ni 9
                        cartas.add(new Carta(palo, numero));
                    }
                } else {
                    cartas.add(new Carta(palo, numero));
                }
            }
        }
    }

    /**
     * Mezcla las cartas de la baraja de forma aleatoria.
     */
    public void barajar() {
        Collections.shuffle(cartas);
    }

    /**
     * Obtiene y retira la carta de la baraja (la carta de arriba).
     * @return La última carta de la baraja, o null si no quedan cartas
     */
    public Carta obtenerCarta() {
        if (cartas.isEmpty()) {
            System.out.println("¡No quedan cartas en la baraja!");
            return null;
        }
        return cartas.remove(cartas.size() - 1);  // Obtiene y elimina la última carta
    }

    /**
     * Devuelve la lista de cartas actual de la baraja.
     * @return ArrayList con las cartas que quedan en la baraja
     */
    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    /**
     * Devuelve el tipo de baraja.
     * @return Tipo de baraja
     */
    public Tipo getTipo() {
        return tipo;
    }
}