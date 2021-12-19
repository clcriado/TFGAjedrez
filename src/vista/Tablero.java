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
 * se ha hecho clic en él para arrastrar las piezas.
 */

public class Tablero extends JPanel implements MouseListener, MouseMotionListener, Serializable {

    public static final int LIMITE_VERTICAL = 8;
    public static final int LIMITE_HORIZONTAL = 8;

    //Array de casillas para simular el Tablero.
	private Casilla[][] casillas;

    private Pieza piezaActual;
    private List<Casilla> movimientosPosibles;

    private boolean turnoBlanco = true;

    private int x, y;
    
    public Tablero() {
        //Declaramos el Array Casillas.
        casillas = new Casilla[LIMITE_HORIZONTAL][LIMITE_VERTICAL];

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        //Establecemos los parámetros de visualización de la Interfaz.
        setLayout(new GridLayout(LIMITE_VERTICAL, LIMITE_HORIZONTAL, 0, 0));
        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));

        int color;

        //For anidado para crear el tablero con las Casillas, a través de los límites verticales y horizontales.
        for (int y = 0; y < LIMITE_VERTICAL; y++) {
            for (int x = 0; x < LIMITE_HORIZONTAL; x++) {
                boolean xEsPar = x % 2 == 0;
                boolean yEsPar = y % 2 == 0;

                //Dependiendo si la posición es Par o no lo es, el color será amarillo o marron.
                if ((xEsPar && yEsPar) || (!xEsPar && !yEsPar)) {
                    color = Imagenes.COLOR_AMARILLO;
                } else {
                    color = Imagenes.COLOR_MARRON;
                }

                //Creamos la casilla con la Posición y el color correspondiente.
                casillas[x][y] = new Casilla(color, x, y);
                this.add(casillas[x][y]);
            }
        }
    }

    /**
     * Método cargarTablero, nos permite vaciar y crear de nuevo el tablero con los datos cargados de la Instantanea.
     */
    public void cargarTablero(Tablero tablero) {

        // Cargamos de nuevo los estados de las casillas y si el turno es del Equipo Blanco.
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

    /**
     * Método finDelJuego, ejecuta un método estático de VentanaJuego, que muestra la ventana de fin del juego.
     */
    private void finDelJuego() {
        VentanaJuego.jaqueMate(piezaActual.esBlanca());
    }

    /**
     * Función getCasilla, devuelve una Casilla del Tablero, si equivale a 0 o menor será nulo, para evitar
     * fallos por movimiento en Casillas inexistentes.
     */
    public Casilla getCasilla(int x, int y) {
        // Limite inferior
        if (x < 0 || y < 0) return null;

        // Limite superior
        if (x >= Tablero.LIMITE_HORIZONTAL || y >= Tablero.LIMITE_VERTICAL) return null;

        return casillas[x][y];
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

        //Con el Limite Horizontal y Vertical, pintamos las casillas.
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

        //Cuando presionemos, con la X e Y recogemos un Objeto Casilla.
        Casilla casilla = (Casilla) this.getComponentAt(new Point(e.getX(), e.getY()));

        //Si la casilla, Pieza es true entonces...
        if (casilla.hayPieza()) {
            piezaActual = casilla.getPieza();

            if (piezaActual.esBlanca() && !turnoBlanco) return;

            if (!piezaActual.esBlanca() && turnoBlanco) return;

            casilla.setDisplay(false);

            //Los movimientos serán las posibilidades de la Pieza actual.
            movimientosPosibles = piezaActual.movimientosPosibles(this);

            //Estableceremos el Color Azul por cada movimiento posible.
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

    @Override
    public void mouseDragged(MouseEvent e) {
        //Le restamos 24 para que se centre donde el cursor.
        x = e.getX() - 24;
        y = e.getY() - 24;

        repaint();
    }

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
}