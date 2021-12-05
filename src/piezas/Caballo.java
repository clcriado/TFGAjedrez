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

        Casilla casillaAComprobar;




        return movimientosPosibles;
    }
}
