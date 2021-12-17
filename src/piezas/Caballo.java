package piezas;

import utilidades.Imagenes;
import vista.Casilla;
import vista.Tablero;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Caballo, sirve para crear una Pieza de tipo Caballo.
 */

public class Caballo extends Pieza {

    public Caballo(boolean soyPiezaBlanca) {
        super(soyPiezaBlanca, soyPiezaBlanca ? Imagenes.UTILIDADES_CABALLO_BLANCO : Imagenes.UTILIDADES_CABALLO_NEGRO);
    }

    //TODO Movimientos posibles del Caballo
    @Override
    public List<Casilla> movimientosPosibles(Tablero tablero) {
        List<Casilla> movimientosPosibles = new ArrayList<>();

        int x = casillaActual.getPosX();
        int y = casillaActual.getPosY();

        // Arriba Derecha
        int resultado = comprobarCasilla(x + 1, y - 2, tablero);

        if (resultado == 1 || resultado == 0) {
            movimientosPosibles.add(tablero.getCasilla(x + 1, y - 2));
        }

        // Arriba Izquierda
        resultado = comprobarCasilla(x - 1, y - 2, tablero);

        if (resultado == 1 || resultado == 0) {
            movimientosPosibles.add(tablero.getCasilla(x - 1, y - 2));
        }

        // Derecha Arriba
        resultado = comprobarCasilla(x + 2, y - 1, tablero);

        if (resultado == 1 || resultado == 0) {
            movimientosPosibles.add(tablero.getCasilla(x + 2, y - 1));
        }

        // Izquierda Arriba
        resultado = comprobarCasilla(x - 2, y - 1, tablero);

        if (resultado == 1 || resultado == 0) {
            movimientosPosibles.add(tablero.getCasilla(x - 2, y - 1));
        }


        // Abajo Derecha
        resultado = comprobarCasilla(x + 1, y + 2, tablero);

        if (resultado == 1 || resultado == 0) {
            movimientosPosibles.add(tablero.getCasilla(x + 1, y + 2));
        }

        // Abajo Izquierda
        resultado = comprobarCasilla(x - 1, y + 2, tablero);

        if (resultado == 1 || resultado == 0) {
            movimientosPosibles.add(tablero.getCasilla(x - 1, y + 2));
        }

        // Derecha Abajo
        resultado = comprobarCasilla(x + 2, y + 1, tablero);

        if (resultado == 1 || resultado == 0) {
            movimientosPosibles.add(tablero.getCasilla(x + 2, y + 1));
        }

        // Izquierda Abajo
        resultado = comprobarCasilla(x - 2, y + 1, tablero);

        if (resultado == 1 || resultado == 0) {
            movimientosPosibles.add(tablero.getCasilla(x - 2, y + 1));
        }

        return movimientosPosibles;
    }
}
