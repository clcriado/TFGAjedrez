package vista;

import piezas.Pieza;
import piezas.Rey;
import utilidades.Imagenes;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.*;

/**
 * Clase Casilla, define que es, como colocarlas en el Tablero, ademas de
 * poderse colocar piezas en ella y tambien quitarlas.
 */

public class Casilla extends JComponent implements Serializable {

    private final int colorInicial;

    public void setColor(int color) {
        this.color = color;
    }

    public void resetearColor() {
        this.color = colorInicial;
    }

    private int color;


    private final int posX;
    private final int posY;

    private Pieza pieza;

    private boolean piezaDisponible;


    public Casilla(int color, int posX, int posY) {

        this.color = color;
        this.colorInicial = color;
        this.piezaDisponible = true;
        this.posX = posX;
        this.posY = posY;


        this.setBorder(BorderFactory.createEmptyBorder());
    }

    /**
     *  Funcion colocarPieza, si al colocarla, la Pieza es una instancia de Rey, reyMuerto equivaldría a True, eso
     *  quiere decir que ha muerto por ello la partida terminaría.
     */
    public boolean colocarPieza(Pieza p) {
        boolean reyMuerto = this.pieza instanceof Rey;

        this.pieza = p;
        pieza.setCasillaActual(this);

        return reyMuerto;
    }

    /**
     *  Método quitarPieza, sirve para quitar una pieza de la Casilla estableciéndola a null.
     */
    public void quitarPieza() {
        this.pieza = null;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dependiendo del Color, se pintará una casilla de ese color.
        if (this.color == Imagenes.COLOR_AMARILLO) {
            g.setColor(new Color(255, 230, 181));
        } else if (color == Imagenes.COLOR_MARRON) {
            g.setColor(new Color(161, 98, 29));
        } else {
            g.setColor(new Color(50, 67, 200));
        }

        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        //Si la pieza no equivale a null y la disponibilidad es True, podemos pintar las piezas.
        if (pieza != null && piezaDisponible) {
            pieza.pintarse(g);
        }
    }

    public Pieza getPieza() {
        return pieza;
    }

    public boolean hayPieza() {
        return this.pieza != null;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setDisplay(boolean v) {
        this.piezaDisponible = v;
    }
}
