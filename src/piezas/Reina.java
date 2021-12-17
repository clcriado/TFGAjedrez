package piezas;

import utilidades.Imagenes;
import vista.Casilla;
import vista.Tablero;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Reina, sirve para crear una Pieza de tipo Reina.
 */

public class Reina extends Pieza {

    public Reina(boolean soyPiezaBlanca) {
        super(soyPiezaBlanca, soyPiezaBlanca ? Imagenes.UTILIDADES_REINA_BLANCO : Imagenes.UTILIDADES_REINA_NEGRO);
    }

    @Override
    public List<Casilla> movimientosPosibles(Tablero tablero) {
        List<Casilla> movimientosPosibles = new ArrayList<>();

        int x = casillaActual.getPosX();
        int y = casillaActual.getPosY();

        Casilla casillaAComprobar;

        int resultado;

        // Movimiento horizontal

        for (int i = x + 1; i<Tablero.LIMITE_HORIZONTAL; i++){
            resultado = comprobarCasilla(i, y, tablero);
            if (resultado == -1) {
                break;
            } else if (resultado == 0) {
                movimientosPosibles.add(tablero.getCasilla(i, y));
                break;
            } else {
                movimientosPosibles.add(tablero.getCasilla(i, y));
            }
        }

        for (int i = x - 1; i>=0; i--) {
            resultado = comprobarCasilla(i, y, tablero);
            if (resultado == -1) {
                break;
            } else if (resultado == 0) {
                movimientosPosibles.add(tablero.getCasilla(i, y));
                break;
            } else {
                movimientosPosibles.add(tablero.getCasilla(i, y));
            }
        }

        // Movimiento vertical

        for (int i = y + 1; i<Tablero.LIMITE_VERTICAL; i++){
            resultado = comprobarCasilla(x, i, tablero);
            if (resultado == -1) {
                break;
            } else if (resultado == 0) {
                movimientosPosibles.add(tablero.getCasilla(x, i));
                break;
            } else {
                movimientosPosibles.add(tablero.getCasilla(x, i));
            }
        }

        for (int i = y - 1; i>=0; i--){
            resultado = comprobarCasilla(x, i, tablero);
            if (resultado == -1) {
                break;
            } else if (resultado == 0) {
                movimientosPosibles.add(tablero.getCasilla(x, i));
                break;
            } else {
                movimientosPosibles.add(tablero.getCasilla(x, i));
            }
        }


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
