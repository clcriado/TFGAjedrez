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




        return movimientosPosibles;
    }
}
