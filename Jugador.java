package es.Poo.sieteymedio;
import java.util.ArrayList;

/**
 * Clase que almacena los datos de los jugadores incluyendo nombre, apellidos, alias y mano
 * Almacena la información personal del jugador y las cartas que tiene en su mano.
 * Humano o maquina
 */
public class Jugador {
    /** Nombre del jugador */
    private String nombre;
    
    /** Primer apellido del jugador */
    private String apellido1;
    
    /** Segundo apellido del jugador */
    private String apellido2;
    
    /** Alias o nombre de juego del jugador */
    private String alias;
    
    /** Indica si el jugador es la máquina (true) o un humano (false) */
    private boolean esAutomatico;
    
    /** Lista de cartas que el jugador tiene en su mano */
    private ArrayList<Carta> mano;

    /**
     * Constructor que crea un nuevo jugador con sus datos personales.
     * Inicializa la mano del jugador como una lista vacía.
     * 
     * @param nombre Nombre del jugador
     * @param apellido1 Primer apellido del jugador
     * @param apellido2 Segundo apellido del jugador
     * @param alias Alias o nombre de juego del jugador
     * @param esAutomatico true si es la máquina, false si es un jugador humano
     */
    public Jugador(String nombre, String apellido1, String apellido2, String alias, boolean esAutomatico) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.alias = alias;
        this.esAutomatico = esAutomatico;
        this.mano = new ArrayList<>();
    }
   
    /**
     * Obtiene el nombre del jugador.
     * @return Nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el primer apellido del jugador.
     * @return Primer apellido del jugador
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Obtiene el segundo apellido del jugador.
     * @return Segundo apellido del jugador
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * Obtiene el alias o nombre de juego del jugador.
     * @return Alias del jugador
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Verifica si el jugador es la máquina.
     * @return true si es la máquina, false si es un jugador humano
     */
    public boolean isEsAutomatico() {
        return esAutomatico;
    }

    /**
     * Obtiene la lista de cartas en la mano del jugador.
     * @return ArrayList con las cartas que tiene el jugador
     */
    public ArrayList<Carta> getMano() {
        return mano;
    }	
}
