package piezas;

import utilidades.Imagenes;
import vista.Casilla;
import vista.Tablero;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Rey, sirve para crear una Pieza de tipo Rey.
 */

public class Rey extends Pieza {

    public Rey(boolean soyPiezaBlanca) {
        super(soyPiezaBlanca, soyPiezaBlanca ? Imagenes.UTILIDADES_REY_BLANCO : Imagenes.UTILIDADES_REY_NEGRO);
    }

    @Override
    public List<Casilla> movimientosPosibles(Tablero tablero) {
        List<Casilla> movimientosPosibles = new ArrayList<>();

        int x = casillaActual.getPosX();
        int y = casillaActual.getPosY();

        // Arriba
        int resultado = comprobarCasilla(x, y - 1, tablero);
        if (resultado == 1 || resultado == 0) {
            movimientosPosibles.add(tablero.getCasilla(x, y - 1));
        }

        // Abajo
        resultado = comprobarCasilla(x, y + 1, tablero);
        if (resultado == 1 || resultado == 0) {
            movimientosPosibles.add(tablero.getCasilla(x, y + 1));
        }

        // Izquierda
        resultado = comprobarCasilla(x - 1, y, tablero);
        if (resultado == 1 || resultado == 0) {
            movimientosPosibles.add(tablero.getCasilla(x - 1, y));
        }

        // Derecha
        resultado = comprobarCasilla(x + 1, y, tablero);
        if (resultado == 1 || resultado == 0) {
            movimientosPosibles.add(tablero.getCasilla(x + 1, y));
        }

        // Diagonal superior derecha
        resultado = comprobarCasilla(x + 1, y - 1, tablero);
        if (resultado == 1 || resultado == 0) {
            movimientosPosibles.add(tablero.getCasilla(x + 1, y - 1));
        }

        // Diagonal superior izquierda
        resultado = comprobarCasilla(x - 1, y - 1, tablero);
        if (resultado == 1 || resultado == 0) {
            movimientosPosibles.add(tablero.getCasilla(x - 1, y - 1));
        }

        // Diagonal inferior derecha
        resultado = comprobarCasilla(x + 1, y + 1, tablero);
        if (resultado == 1 || resultado == 0) {
            movimientosPosibles.add(tablero.getCasilla(x + 1, y + 1));
        }

        // Diagonal inferior izquierda
        resultado = comprobarCasilla(x - 1, y + 1, tablero);
        if (resultado == 1 || resultado == 0) {
            movimientosPosibles.add(tablero.getCasilla(x - 1, y + 1));
        }

        return movimientosPosibles;
    }


}
