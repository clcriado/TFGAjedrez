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

        Casilla casillaAComprobar;

        // Diagonal principal

        int xDiagonal = x + 1;
        int yDiagonal = y + 1;

        while (xDiagonal < Tablero.LIMITE_HORIZONTAL && yDiagonal < Tablero.LIMITE_VERTICAL) {
            casillaAComprobar = tablero.getCasilla(xDiagonal, yDiagonal);
            if(casillaAComprobar.getPieza() != null){
                // Comprobamos si la pieza que está en la casilla es de diferente color a la nuestra.
                // Si lo es, podemos movernos a esa casilla, ya que se puede comer.
                if (casillaAComprobar.getPieza().soyPiezaBlanca != soyPiezaBlanca) {
                    movimientosPosibles.add(casillaAComprobar);
                }

                break;
            }

            movimientosPosibles.add(casillaAComprobar);

            xDiagonal++;
            yDiagonal++;
        }

        xDiagonal = x - 1;
        yDiagonal = y - 1;

        while (xDiagonal >= 0 && yDiagonal >= 0) {
            casillaAComprobar = tablero.getCasilla(xDiagonal, yDiagonal);
            if(casillaAComprobar.getPieza() != null){
                // Comprobamos si la pieza que está en la casilla es de diferente color a la nuestra.
                // Si lo es, podemos movernos a esa casilla, ya que se puede comer.
                if (casillaAComprobar.getPieza().soyPiezaBlanca != soyPiezaBlanca) {
                    movimientosPosibles.add(casillaAComprobar);
                }

                break;
            }

            movimientosPosibles.add(casillaAComprobar);

            xDiagonal--;
            yDiagonal--;
        }



        // Diagonal inversa

        xDiagonal = x + 1;
        yDiagonal = y - 1;

        while (xDiagonal < Tablero.LIMITE_HORIZONTAL && yDiagonal >= 0) {
            casillaAComprobar = tablero.getCasilla(xDiagonal, yDiagonal);
            if(casillaAComprobar.getPieza() != null){
                // Comprobamos si la pieza que está en la casilla es de diferente color a la nuestra.
                // Si lo es, podemos movernos a esa casilla, ya que se puede comer.
                if (casillaAComprobar.getPieza().soyPiezaBlanca != soyPiezaBlanca) {
                    movimientosPosibles.add(casillaAComprobar);
                }

                break;
            }

            movimientosPosibles.add(casillaAComprobar);

            xDiagonal++;
            yDiagonal--;
        }

        xDiagonal = x - 1;
        yDiagonal = y + 1;

        while (xDiagonal >= 0 && yDiagonal < Tablero.LIMITE_VERTICAL) {
            casillaAComprobar = tablero.getCasilla(xDiagonal, yDiagonal);
            if(casillaAComprobar.getPieza() != null){
                // Comprobamos si la pieza que está en la casilla es de diferente color a la nuestra.
                // Si lo es, podemos movernos a esa casilla, ya que se puede comer.
                if (casillaAComprobar.getPieza().soyPiezaBlanca != soyPiezaBlanca) {
                    movimientosPosibles.add(casillaAComprobar);
                }

                break;
            }

            movimientosPosibles.add(casillaAComprobar);

            xDiagonal--;
            yDiagonal++;
        }

        return movimientosPosibles;
    }
}
