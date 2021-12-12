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
    public void moverA(Casilla nuevaCasilla) {
        casillaActual.quitarPieza();

        nuevaCasilla.colocarPieza(this);
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