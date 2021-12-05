package vista;

import jugadores.Jugador;
import jugadores.JugadorBlanco;
import jugadores.JugadorNegro;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Clase VentanaJuego, esctructura principal del Juego, mediante el uso de JPanel se componen
 * los diferentes paneles, botones y su respectiva colocacion.
 */

public class VentanaJuego {
    private final JFrame ventanaJuego;
    private final Tablero tablero;

    public VentanaJuego(String nombreNegro, String nombreBlanco) {

        ventanaJuego = new JFrame("Ajedrez");
        ventanaJuego.setLocation(100, 100);
        ventanaJuego.setLayout(new BorderLayout(100, 100));

        // Ventana de Juego

        JPanel panelDatos = panelDatos();
        panelDatos.setSize(panelDatos.getPreferredSize());
        ventanaJuego.add(panelDatos, BorderLayout.NORTH);

        JPanel panelDatosIzquierda = panelDatosIzquierda(nombreNegro);
        panelDatosIzquierda.setSize(panelDatosIzquierda.getPreferredSize());
        ventanaJuego.add(panelDatosIzquierda, BorderLayout.WEST);

        JPanel panelDatosDerecha = panelDatosDerecha(nombreNegro);
        panelDatosDerecha.setSize(panelDatosDerecha.getPreferredSize());
        ventanaJuego.add(panelDatosDerecha, BorderLayout.EAST);


        this.tablero = new Tablero(this);

        ventanaJuego.add(tablero, BorderLayout.CENTER);
        ventanaJuego.add(botones(), BorderLayout.SOUTH);

        ventanaJuego.setMinimumSize(ventanaJuego.getPreferredSize());
        ventanaJuego.setSize(ventanaJuego.getPreferredSize());
        ventanaJuego.setResizable(false);

        // Creamos jugadores y colocan piezas
        Jugador jugadorBlanco = new JugadorBlanco(this.tablero);
        Jugador jugadorNegro = new JugadorNegro(this.tablero);

        jugadorBlanco.colocarPiezas();
        jugadorNegro.colocarPiezas();

        ventanaJuego.pack();
        ventanaJuego.setVisible(true);
        ventanaJuego.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // Funcion para crear el Panel del juego Norte.
    private JPanel panelDatos() {

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new GridLayout(3, 2, 0, 0));

        //Nombre del Jugador
        JLabel labelNorte = new JLabel("AJEDREZ");

        labelNorte.setHorizontalAlignment(JLabel.CENTER);
        labelNorte.setVerticalAlignment(JLabel.CENTER);

        panelDatos.add(labelNorte);

        return panelDatos;
    }

    // Funcion para crear el Panel del juego Derecha.
    private JPanel panelDatosDerecha(final String nombreNegro) {

        JPanel panelDatosDerecha = new JPanel();
        panelDatosDerecha.setLayout(new GridLayout(3, 2, 0, 0));

        //Nombre del Jugador
        JLabel labelNegro = new JLabel("Jugador Negro: " + nombreNegro);

        labelNegro.setHorizontalAlignment(JLabel.RIGHT);
        labelNegro.setVerticalAlignment(JLabel.CENTER);

        panelDatosDerecha.add(labelNegro);

        return panelDatosDerecha;
    }

    // Funcion para crear el Panel del juego Izquierda.
    private JPanel panelDatosIzquierda(final String nombreBlanco) {

        JPanel panelDatosJuegoIzquierda = new JPanel();
        panelDatosJuegoIzquierda.setLayout(new GridLayout(3, 2, 0, 0));

        //Nombre del Jugador
        JLabel labelBlanco = new JLabel("Jugador Blanco: " + nombreBlanco);

        labelBlanco.setHorizontalAlignment(JLabel.LEFT);
        labelBlanco.setVerticalAlignment(JLabel.CENTER);

        panelDatosJuegoIzquierda.add(labelBlanco);

        return panelDatosJuegoIzquierda;
    }

    // Funcion JPanel con la estructura de los botones y su uso
    private JPanel botones() {
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 3, 10, 0));

        //TODO Implementar la Instantanea de las Piezas
        final JButton buttonInstantanea = new JButton("Instantanea");

        buttonInstantanea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        final JButton buttonNuevaPartida = new JButton("Nueva Partida");

        buttonNuevaPartida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new MenuPrincipal());
                ventanaJuego.dispose();
            }
        });

        final JButton buttonSalir = new JButton("Salir");

        buttonSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ventanaJuego.dispose();
            }
        });

        panelBotones.add(buttonInstantanea);
        panelBotones.add(buttonNuevaPartida);
        panelBotones.add(buttonSalir);

        panelBotones.setPreferredSize(panelBotones.getMinimumSize());

        return panelBotones;
    }

    //TODO Implementar el Hake Mate
    public void hakeMate() {

    }
}
