package piezas;

import vista.Casilla;
import vista.Tablero;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;

/**
 * Clase Abstracta Pieza, se declara como se crea una Pieza, como pintarla, moverla y colocarla.
 */

public abstract class Pieza implements Serializable {

    //Las piezas blancas siempre inician el primer movimiento de la partida.
    protected final boolean soyPiezaBlanca;

    //Objeto Casilla de la casilla actual en la que se encuetra nuestra pieza.
    protected Casilla casillaActual;

    transient protected BufferedImage imagen;

    protected String rutaImagen;

    public Pieza(boolean soyPiezaBlanca, String rutaImagen) {
        this.soyPiezaBlanca = soyPiezaBlanca;
        this.rutaImagen = rutaImagen;
        this.establerImagen(rutaImagen);
    }

    // Lista de Casillas donde se declarara los movimientos posibles de nuestra Pieza.
    public abstract List<Casilla> movimientosPosibles(Tablero tablero);

    // Metodo donde establecemos la imagen de la Pieza.
    protected void establerImagen(String rutaImagen) {
        try {
            this.imagen = ImageIO.read(Objects.requireNonNull(getClass().getResource(rutaImagen)));
        } catch (IOException e) {
            System.out.println("Imagen no encontrada: " + e.getMessage());
        }
    }

    // Metodo donde pintamos la Pieza en la posicion requerida.
    public void pintarse(Graphics g) {
        int x = casillaActual.getX();
        int y = casillaActual.getY();

        g.drawImage(this.imagen, x, y, null);
    }

    // Metodo con el cual poder mover la Pieza.
    public boolean moverA(Casilla nuevaCasilla) {
        casillaActual.quitarPieza();

        boolean reyMuerto = nuevaCasilla.colocarPieza(this);

        return reyMuerto;
    }

    /**
     * A partir de unas posiciones x e y, comprobamos si es una casilla a la que nos podemos mover
     *
     * Devolveremos un -1 si no nos podemos mover.
     *              un  1 si nos podemos mover y no hay piezas en la casilla.
     *              un  0 si nos podemos mover y lo que hay en la casilla es una pieza de color diferente.
     */
    protected int comprobarCasilla(int x, int y, Tablero tablero) {
        Casilla casillaComprobar = tablero.getCasilla(x, y);

        //Si la casilla a comprobar es null, quiere decir que esta fuera de los limites del tablero, por ello no sirve.
        if (casillaComprobar == null) return -1;

        //Si la pieza esta en el tablero, comprobaremos la pieza en esa casilla.
        Pieza piezaComprobar = casillaComprobar.getPieza();

        //Si la pieza a comprobar es null, quiere decir que no hay pieza en dicha casilla, por ello nos
        // podemos mover a casilla.
        if (piezaComprobar == null) return 1;

        //Si soy pieza blanca es diferente al otro color, puedo comer dicha pieza y moverme a esa casilla.
        if (this.soyPiezaBlanca != piezaComprobar.esBlanca()) return 0;

        return -1;
    }

    @Serial
    private void readObject(ObjectInputStream  ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.establerImagen(this.rutaImagen);
    }

    public void setCasillaActual(Casilla nuevaCasilla) {
        casillaActual = nuevaCasilla;
    }

    public Casilla getCasillaActual() {
        return casillaActual;
    }

    public boolean esBlanca() {
        return soyPiezaBlanca;
    }

    public BufferedImage getImagen() {
        return imagen;
    }
}