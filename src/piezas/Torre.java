package piezas;

import utilidades.Imagenes;
import vista.Casilla;
import vista.Tablero;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Torre, sirve para crear una Pieza de tipo Torre.
 */
public class Torre extends Pieza {

    public Torre(boolean soyPiezaBlanca) {
        super(soyPiezaBlanca, soyPiezaBlanca ? Imagenes.UTILIDADES_TORRE_BLANCA : Imagenes.UTILIDADES_TORRE_NEGRA);
    }

    @Override
    public List<Casilla> movimientosPosibles(Tablero tablero) {
        List<Casilla> movimientosPosibles = new ArrayList<>();

        int x = casillaActual.getPosX();
        int y = casillaActual.getPosY();

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


        return movimientosPosibles;
    }
}
