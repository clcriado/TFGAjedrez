package vista;

import piezas.Pieza;
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

    private boolean dispPiece;


    public Casilla(int color, int posX, int posY) {

        this.color = color;
        this.colorInicial = color;
        this.dispPiece = true;
        this.posX = posX;
        this.posY = posY;


        this.setBorder(BorderFactory.createEmptyBorder());
    }

    public void colocarPieza(Pieza p) {
        this.pieza = p;
        pieza.setCasillaActual(this);
    }

    public void quitarPieza() {
        this.pieza = null;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.color == Imagenes.COLOR_AMARILLO) {
            g.setColor(new Color(255, 230, 181));
        } else if (color == Imagenes.COLOR_MARRON) {
            g.setColor(new Color(161, 98, 29));
        } else {
            g.setColor(new Color(50, 67, 200));
        }

        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        if (pieza != null && dispPiece) {
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
        this.dispPiece = v;
    }
}
