package piezas;

import utilidades.Imagenes;
import vista.Casilla;
import vista.Tablero;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Peon, sirve para crear una Pieza de tipo Peon.
 */

public class Peon extends Pieza {
    private boolean primerTurno = true;

    public Peon(boolean soyPiezaBlanca) {
        super(soyPiezaBlanca, soyPiezaBlanca ? Imagenes.UTILIDADES_PEON_BLANCO : Imagenes.UTILIDADES_PEON_NEGRO);
    }

    @Override
    public List<Casilla> movimientosPosibles(Tablero tablero) {
        List<Casilla> movimientosPosibles = new ArrayList<>();

        int x = casillaActual.getPosX();
        int y = casillaActual.getPosY();

        int posicionFrontal = soyPiezaBlanca ? y - 1 : y + 1;
        int posicionMasDos = soyPiezaBlanca ? y - 2 : y + 2;

        Casilla casillaFrontal = tablero.getCasilla(x, posicionFrontal);

        if (casillaFrontal.getPieza() == null) {
            movimientosPosibles.add(casillaFrontal);

            if (primerTurno) {
                Casilla casillaSuperiorAFrontal = tablero.getCasilla(x, posicionMasDos);

                if (casillaSuperiorAFrontal.getPieza() == null) {
                    movimientosPosibles.add(casillaSuperiorAFrontal);
                }
            }
        }

        Pieza diagonalIzquierda = soyPiezaBlanca ? tablero.getCasilla(x - 1, y - 1).getPieza()
                : tablero.getCasilla(x + 1, y + 1).getPieza();

        Pieza diagonalDerecha = soyPiezaBlanca ? tablero.getCasilla(x + 1, y - 1).getPieza()
                : tablero.getCasilla(x - 1, y + 1).getPieza();


        if (soyPiezaBlanca) {

            if (diagonalIzquierda != null && !diagonalIzquierda.soyPiezaBlanca)
                 movimientosPosibles.add(diagonalIzquierda.getCasillaActual());

            if(diagonalDerecha != null && !diagonalDerecha.soyPiezaBlanca)
                movimientosPosibles.add(diagonalDerecha.getCasillaActual());

        } else {

            if (diagonalIzquierda != null && diagonalIzquierda.soyPiezaBlanca)
                movimientosPosibles.add(diagonalIzquierda.getCasillaActual());

            if(diagonalDerecha != null && diagonalDerecha.soyPiezaBlanca)
                movimientosPosibles.add(diagonalDerecha.getCasillaActual());

        }

        return movimientosPosibles;
    }

    @Override
    public void moverA(Casilla nuevaCasilla) {
        super.moverA(nuevaCasilla);
        primerTurno = false;
    }
}
