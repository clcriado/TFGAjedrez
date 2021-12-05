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
