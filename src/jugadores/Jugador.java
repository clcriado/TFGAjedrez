package jugadores;

import piezas.*;
import vista.Tablero;

import java.util.LinkedList;

/**
 * Clase Abstracta Jugador, se declara como se crea un jugador.
 */

public abstract class Jugador {

    protected LinkedList<Pieza> piezas;
    protected Tablero tablero;

    //Controla de quien es el turno actual.
    protected boolean miTurno;

    public Jugador(boolean miTurno, Tablero tablero) {
        this.miTurno = miTurno;
        this.tablero = tablero;

        this.piezas = new LinkedList<>();
    }


    // Declaracion abstracta del metodo colocar pieza, que desarrollaran cada uno de los tipos de jugador.
    abstract public void colocarPiezas();
}
