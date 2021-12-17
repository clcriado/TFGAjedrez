package vista;

import piezas.*;
import utilidades.Imagenes;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.List;
import java.awt.Image;

import javax.swing.*;

/**
 * Clase Tablero, define que es, como pintarlo, y controlar donde y cuando
 * se ha hecho click en el para arrastrar las piezas.
 */

public class Tablero extends JPanel implements MouseListener, MouseMotionListener, Serializable {

    public static final int LIMITE_VERTICAL = 8;
    public static final int LIMITE_HORIZONTAL = 8;

	private Casilla[][] casillas;

    private Pieza piezaActual;
    private List<Casilla> movimientosPosibles;

    private boolean turnoBlanco = true;

    private int x, y;
    
    public Tablero() {
        casillas = new Casilla[LIMITE_HORIZONTAL][LIMITE_VERTICAL];

        setLayout(new GridLayout(LIMITE_VERTICAL, LIMITE_HORIZONTAL, 0, 0));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        int color;
        for (int y = 0; y < LIMITE_VERTICAL; y++) {
            for (int x = 0; x < LIMITE_HORIZONTAL; x++) {
                boolean xEsPar = x % 2 == 0;
                boolean yEsPar = y % 2 == 0;

                if ((xEsPar && yEsPar) || (!xEsPar && !yEsPar)) {
                    color = Imagenes.COLOR_AMARILLO;
                } else {
                    color = Imagenes.COLOR_MARRON;
                }

                casillas[x][y] = new Casilla(color, x, y);

                this.add(casillas[x][y]);
            }
        }

        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));
    }

    public void loadTablero(Tablero tablero) {

        // Cargamos otra vez los estados
        casillas = tablero.getCasillas();
        turnoBlanco = tablero.isTurnoBlanco();

        // Eliminamos del JPanel todos los componentes
        this.removeAll();

        // Los añadimos de nuevo, esta vez con las casillas actualizadas
        for (int y = 0; y < LIMITE_VERTICAL; y++) {
            for (int x = 0; x < LIMITE_HORIZONTAL; x++) {
                this.add(casillas[x][y]);
            }
        }

        // Pintamos de nuevo para que se muestre actualizado
        this.repaint();
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }

    public boolean isTurnoBlanco() {
        return turnoBlanco;
    }

    public void colocarPieza(Pieza pieza, int x, int y) {
        casillas[x][y].colocarPieza(pieza);
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int x = 0; x < LIMITE_HORIZONTAL; x++) {
            for (int y = 0; y < LIMITE_VERTICAL; y++) {
                casillas[x][y].paintComponent(g);
            }
        }

        if (piezaActual == null) return;

        if (piezaActual.esBlanca() && !turnoBlanco) return;

        if (!piezaActual.esBlanca() && turnoBlanco) return;

        final Image imagen = piezaActual.getImagen();
        g.drawImage(imagen, x, y, null);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();

        Casilla casilla = (Casilla) this.getComponentAt(new Point(e.getX(), e.getY()));

        if (casilla.hayPieza()) {
            piezaActual = casilla.getPieza();

            if (piezaActual.esBlanca() && !turnoBlanco) return;

            if (!piezaActual.esBlanca() && turnoBlanco) return;

            casilla.setDisplay(false);

            movimientosPosibles = piezaActual.movimientosPosibles(this);

            movimientosPosibles.forEach(cas -> cas.setColor(Imagenes.COLOR_AZUL));
        }

        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Casilla casilla = (Casilla) this.getComponentAt(new Point(e.getX(), e.getY()));

        if (piezaActual != null) {
            if (piezaActual.esBlanca() && !turnoBlanco) return;

            if (!piezaActual.esBlanca() && turnoBlanco) return;

            if (movimientosPosibles.contains(casilla)) {
                casilla.setDisplay(true);
                boolean reyMuerto = piezaActual.moverA(casilla);

                if (reyMuerto) finDelJuego();

                piezaActual = null;
                turnoBlanco = !turnoBlanco;
            } else {
                piezaActual.getCasillaActual().setDisplay(true);
                piezaActual = null;
            }

            movimientosPosibles.forEach(Casilla::resetearColor);
            movimientosPosibles = null;
        }

        repaint();
    }

    private void finDelJuego() {
        VentanaJuego.jaqueMate(piezaActual.esBlanca());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX() - 24;
        y = e.getY() - 24;

        repaint();
    }

    // Irrelevant methods, do nothing for these mouse behaviors
    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public Casilla getCasilla(int x, int y) {
        // Limite inferior
        if (x < 0 || y < 0) return null;

        // Limite superior
        if (x >= Tablero.LIMITE_HORIZONTAL || y >= Tablero.LIMITE_VERTICAL) return null;

        return casillas[x][y];
    }

}