package jugadores;

import piezas.*;
import vista.Tablero;

/**
 * Clase JugadorNegro, sirve para crear al Jugador Negro.
 */

public class JugadorNegro extends Jugador {

    public JugadorNegro(Tablero tablero) {
        super(false, tablero);

    }

    // Método del Padre Jugador, se utiliza para colocar las piezas en el tablero.
    @Override
    public void colocarPiezas() {
        // Creamos las piezas

        Pieza piezaAColocar;

        // Peones
        for (int i = 0; i < 8; i++) {
            piezaAColocar = new Peon(false);
            piezas.add(piezaAColocar);
            tablero.colocarPieza(piezaAColocar, i, 1);
        }

        // Torres
        piezaAColocar = new Torre(false);
        piezas.add(piezaAColocar);
        tablero.colocarPieza(piezaAColocar, 0, 0);

        piezaAColocar = new Torre(false);
        piezas.add(piezaAColocar);
        tablero.colocarPieza(piezaAColocar, 7, 0);

        // Alfiles
        piezaAColocar = new Alfil(false);
        piezas.add(piezaAColocar);
        tablero.colocarPieza(piezaAColocar, 2, 0);

        piezaAColocar = new Alfil(false);
        piezas.add(piezaAColocar);
        tablero.colocarPieza(piezaAColocar, 5, 0);

        // Caballos
        piezaAColocar = new Caballo(false);
        piezas.add(piezaAColocar);
        tablero.colocarPieza(piezaAColocar, 1, 0);

        piezaAColocar = new Caballo(false);
        piezas.add(piezaAColocar);
        tablero.colocarPieza(piezaAColocar, 6, 0);

        // Reina
        piezaAColocar = new Reina(false);
        piezas.add(piezaAColocar);
        tablero.colocarPieza(piezaAColocar, 3, 0);

        // Rey
        piezaAColocar = new Rey(false);
        piezas.add(piezaAColocar);
        tablero.colocarPieza(piezaAColocar, 4, 0);
    }
}
