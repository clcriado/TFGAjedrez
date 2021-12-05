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

        Casilla casillaAComprobar;

        // Movimiento horizontal

        for (int i = x + 1; i<Tablero.LIMITE_HORIZONTAL; i++){
            casillaAComprobar = tablero.getCasilla(i, y);
            if(casillaAComprobar.getPieza() != null) {

                // Comprobamos si la pieza que está en la casilla es de diferente color a la nuestra.
                // Si lo es, podemos movernos a esa casilla, ya que se puede comer.
                if (casillaAComprobar.getPieza().soyPiezaBlanca != soyPiezaBlanca) {
                    movimientosPosibles.add(casillaAComprobar);
                }

                break;
            }

            movimientosPosibles.add(casillaAComprobar);
        }

        for (int i = x - 1; i>=0; i--) {
            casillaAComprobar = tablero.getCasilla(i, y);
            if (casillaAComprobar.getPieza() != null) {
                // Comprobamos si la pieza que está en la casilla es de diferente color a la nuestra.
                // Si lo es, podemos movernos a esa casilla, ya que se puede comer.
                if (casillaAComprobar.getPieza().soyPiezaBlanca != soyPiezaBlanca) {
                    movimientosPosibles.add(casillaAComprobar);
                }

                break;
            }

            movimientosPosibles.add(casillaAComprobar);
        }

        // Movimiento vertical

        for (int i = y + 1; i<Tablero.LIMITE_VERTICAL; i++){
            casillaAComprobar = tablero.getCasilla(x, i);
            if(casillaAComprobar.getPieza() != null){
                // Comprobamos si la pieza que está en la casilla es de diferente color a la nuestra.
                // Si lo es, podemos movernos a esa casilla, ya que se puede comer.
                if (casillaAComprobar.getPieza().soyPiezaBlanca != soyPiezaBlanca) {
                    movimientosPosibles.add(casillaAComprobar);
                }

                break;
            }

            movimientosPosibles.add(casillaAComprobar);
        }

        for (int i = y - 1; i>=0; i--){
            casillaAComprobar = tablero.getCasilla(x, i);

            if(casillaAComprobar.getPieza() != null){
                // Comprobamos si la pieza que está en la casilla es de diferente color a la nuestra.
                // Si lo es, podemos movernos a esa casilla, ya que se puede comer.
                if (casillaAComprobar.getPieza().soyPiezaBlanca != soyPiezaBlanca) {
                    movimientosPosibles.add(casillaAComprobar);
                }

                break;
            }

            movimientosPosibles.add(casillaAComprobar);
        }


        return movimientosPosibles;
    }
}
