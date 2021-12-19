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

        //Si es Blanca o no, miramos hacia arriba o hacia abajo.
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

        //Dependiendo del color de la Pieza, la diagonal izquierda sera una casilla u otra.
        Casilla diagonalIzquierda = soyPiezaBlanca ?
                tablero.getCasilla(x - 1, y - 1) :
                tablero.getCasilla(x + 1, y + 1);

        //Dependiendo del color de la Pieza, la diagonal derecha sera una casilla u otra.
        Casilla diagonalDerecha = soyPiezaBlanca ?
                tablero.getCasilla(x + 1, y - 1) :
                tablero.getCasilla(x - 1, y + 1);


        if (diagonalIzquierda != null) {
            Pieza piezaDiagonalIzq = diagonalIzquierda.getPieza();

            if (piezaDiagonalIzq != null && soyPiezaBlanca != piezaDiagonalIzq.esBlanca()) {
                movimientosPosibles.add(diagonalIzquierda);
            }
        }


        if (diagonalDerecha != null) {
            Pieza piezaDiagonalDch = diagonalDerecha.getPieza();

            if (piezaDiagonalDch != null && soyPiezaBlanca != piezaDiagonalDch.esBlanca()) {
                movimientosPosibles.add(diagonalDerecha);
            }
        }

        return movimientosPosibles;
    }

    @Override
    public boolean moverA(Casilla nuevaCasilla) {
        boolean reyMuerto = super.moverA(nuevaCasilla);
        primerTurno = false;
        return reyMuerto;
    }
}
