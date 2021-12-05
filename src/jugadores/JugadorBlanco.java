package jugadores;

import piezas.*;
import vista.Tablero;

/**
 * Clase JugadorBlanco, sirve para crear al Jugador Blanco.
 */

public class JugadorBlanco extends Jugador {

    public JugadorBlanco(Tablero tablero) {
        super(true, tablero);

    }

    // Método del Padre Jugador, se utiliza para colocar las piezas en el tablero.
    @Override
    public void colocarPiezas() {
        // Creamos las piezas
        Pieza piezaAColocar;

        // Peones
        for (int i = 0; i < 8; i++) {
            piezaAColocar = new Peon(true);
            piezas.add(piezaAColocar);
            tablero.colocarPieza(piezaAColocar, i, 6);
        }

        // Torres
        piezaAColocar = new Torre(true);
        piezas.add(piezaAColocar);
        tablero.colocarPieza(piezaAColocar, 0, 7);

        piezaAColocar = new Torre(true);
        piezas.add(piezaAColocar);
        tablero.colocarPieza(piezaAColocar, 7, 7);

        // Alfiles
        piezaAColocar = new Alfil(true);
        piezas.add(piezaAColocar);
        tablero.colocarPieza(piezaAColocar, 2, 7);

        piezaAColocar = new Alfil(true);
        piezas.add(piezaAColocar);
        tablero.colocarPieza(piezaAColocar, 5, 7);

        // Caballos
        piezaAColocar = new Caballo(true);
        piezas.add(piezaAColocar);
        tablero.colocarPieza(piezaAColocar, 1, 7);

        piezaAColocar = new Caballo(true);
        piezas.add(piezaAColocar);
        tablero.colocarPieza(piezaAColocar, 6, 7);

        // Reina
        piezaAColocar = new Reina(true);
        piezas.add(piezaAColocar);
        tablero.colocarPieza(piezaAColocar, 3, 7);

        // Rey
        piezaAColocar = new Rey(true);
        piezas.add(piezaAColocar);
        tablero.colocarPieza(piezaAColocar, 4, 7);
    }
}
