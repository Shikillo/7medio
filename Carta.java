package es.Poo.sieteymedio;

// Cuando empeze a crear el proyecto llame a los valores de Carta tipo y numero, asique como lo use en muchos lugares no me atrevo a tocarlo.

/**
 * Clase Carta, en esta se genera una carta generica usada por la clase baraja
 * para definir cada una de las cartas que incluye el siete y medio.
 */
public class Carta {
	private String tipo;
	private int numero;
	
	/**
	 * Public Carta se usa para crear cartas.
	 */
	public Carta(String simbolo, int numero) {
		this.tipo = simbolo;
		this.numero = numero;
	}
	
    /**
     * Obtiene el palo de la carta.
     * @return Palo de la carta (Oros, Copas, etc. o Picas, Rombos, etc.)
     */
    public String getPalo() {
        return tipo;
    }

    /**
     * Obtiene el número de la carta.
     * @return Número de la carta (1-12 para española, 1-13 para francesa)
     */
    public int getnumero() {
        return numero;
    }

    /**
     * Crea la carta juntando su numero con el palo que le toca
     * @return String con el formato "número de palo"
     */
    public String toString() {
        return numero + " de " + tipo;
    }
}
