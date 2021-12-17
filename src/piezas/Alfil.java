package piezas;

import utilidades.Imagenes;
import vista.Casilla;
import vista.Tablero;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Alfil, sirve para crear una Pieza de tipo Alfil.
 */

public class Alfil extends Pieza {

    public Alfil(boolean soyPiezaBlanca) {
        super(soyPiezaBlanca, soyPiezaBlanca ? Imagenes.UTILIDADES_ALFIL_BLANCO : Imagenes.UTILIDADES_ALFIL_NEGRO);
    }

    // Lista de Casillas donde declaramos los movimientos posibles de nuestra Pieza.
    @Override
    public List<Casilla> movimientosPosibles(Tablero tablero) {
        List<Casilla> movimientosPosibles = new ArrayList<>();

        int x = casillaActual.getPosX();
        int y = casillaActual.getPosY();

        int resultado;

        // Diagonal principal

        int xDiagonal = x + 1;
        int yDiagonal = y + 1;

        while (xDiagonal < Tablero.LIMITE_HORIZONTAL && yDiagonal < Tablero.LIMITE_VERTICAL) {
            resultado = comprobarCasilla(xDiagonal, yDiagonal, tablero);
            if (resultado == -1) {
                break;
            } else if (resultado == 0) {
                movimientosPosibles.add(tablero.getCasilla(xDiagonal, yDiagonal));
                break;
            }

            movimientosPosibles.add(tablero.getCasilla(xDiagonal, yDiagonal));

            xDiagonal++;
            yDiagonal++;
        }

        xDiagonal = x - 1;
        yDiagonal = y - 1;

        while (xDiagonal >= 0 && yDiagonal >= 0) {
            resultado = comprobarCasilla(xDiagonal, yDiagonal, tablero);
            if (resultado == -1) {
                break;
            } else if (resultado == 0) {
                movimientosPosibles.add(tablero.getCasilla(xDiagonal, yDiagonal));
                break;
            }

            movimientosPosibles.add(tablero.getCasilla(xDiagonal, yDiagonal));

            xDiagonal--;
            yDiagonal--;
        }



        // Diagonal inversa

        xDiagonal = x + 1;
        yDiagonal = y - 1;

        while (xDiagonal < Tablero.LIMITE_HORIZONTAL && yDiagonal >= 0) {
            resultado = comprobarCasilla(xDiagonal, yDiagonal, tablero);
            if (resultado == -1) {
                break;
            } else if (resultado == 0) {
                movimientosPosibles.add(tablero.getCasilla(xDiagonal, yDiagonal));
                break;
            }

            movimientosPosibles.add(tablero.getCasilla(xDiagonal, yDiagonal));

            xDiagonal++;
            yDiagonal--;
        }

        xDiagonal = x - 1;
        yDiagonal = y + 1;

        while (xDiagonal >= 0 && yDiagonal < Tablero.LIMITE_VERTICAL) {
            resultado = comprobarCasilla(xDiagonal, yDiagonal, tablero);
            if (resultado == -1) {
                break;
            } else if (resultado == 0) {
                movimientosPosibles.add(tablero.getCasilla(xDiagonal, yDiagonal));
                break;
            }

            movimientosPosibles.add(tablero.getCasilla(xDiagonal, yDiagonal));

            xDiagonal--;
            yDiagonal++;
        }

        return movimientosPosibles;
    }
}
