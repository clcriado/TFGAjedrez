package vista;

import jugadores.Jugador;
import jugadores.JugadorBlanco;
import jugadores.JugadorNegro;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

/**
 * Clase VentanaJuego, esctructura principal del Juego, mediante el uso de JPanel se componen
 * los diferentes paneles, botones y su respectiva colocacion.
 */

public class VentanaJuego {

    private static JFrame frame;

    private final Tablero tablero;

    private static String nombreBlanco;
    private static String nombreNegro;

    public VentanaJuego(String nombreNegro, String nombreBlanco) {

        VentanaJuego.nombreBlanco = nombreBlanco;
        VentanaJuego.nombreNegro = nombreNegro;

        frame = new JFrame("Ajedrez");
        frame.setLocation(100, 100);
        frame.setLayout(new BorderLayout(100, 100));

        // Ventana de Juego
        JPanel panelDatos = panelDatos();
        panelDatos.setSize(panelDatos.getPreferredSize());
        frame.add(panelDatos, BorderLayout.NORTH);

        JPanel panelDatosIzquierda = panelDatosIzquierda(nombreBlanco);
        panelDatosIzquierda.setSize(panelDatosIzquierda.getPreferredSize());
        frame.add(panelDatosIzquierda, BorderLayout.WEST);

        JPanel panelDatosDerecha = panelDatosDerecha(nombreNegro);
        panelDatosDerecha.setSize(panelDatosDerecha.getPreferredSize());
        frame.add(panelDatosDerecha, BorderLayout.EAST);

        tablero = new Tablero();

        frame.add(tablero, BorderLayout.CENTER);
        frame.add(botones(), BorderLayout.SOUTH);

        frame.setMinimumSize(frame.getPreferredSize());
        frame.setSize(frame.getPreferredSize());
        frame.setResizable(false);

        // Creamos jugadores y colocan piezas
        Jugador jugadorBlanco = new JugadorBlanco(this.tablero);
        Jugador jugadorNegro = new JugadorNegro(this.tablero);

        jugadorBlanco.colocarPiezas();
        jugadorNegro.colocarPiezas();

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Función guardarPartida, abre un elector de archivo, para obtener una ruta, para serializar el objeto tablero.
     */
    public void guardarPartida() {
        JFileChooser fileChooser = new JFileChooser();

        int status = fileChooser.showSaveDialog(frame);

        if (status != JFileChooser.APPROVE_OPTION) return;

        //Ruta del archivo
        String path = null;

        try {
            //La ruta equivaldrá al archivo seleccionado.
            path = fileChooser.getSelectedFile().getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (path == null) return;

        //Serializamos la ruta y escribimos el objeto tablero.
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(path, false)
        )) {
            oos.writeObject(tablero);
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    /**
     * Función cargarPartida, abre un elector de archivo, para obtener una ruta, para serializar el objeto tablero.
     */
    public void cargarPartida() {
        JFileChooser fileChooser = new JFileChooser();

        int status = fileChooser.showOpenDialog(frame);

        if (status != JFileChooser.APPROVE_OPTION) return;

        //Ruta del archivo
        String path = null;

        try {
            //La ruta equivaldrá al archivo seleccionado.
            path = fileChooser.getSelectedFile().getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (path == null) return;

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(path)
        )) {
            tablero.cargarTablero((Tablero) ois.readObject());
        } catch (IOException | ClassNotFoundException fileNotFoundException) {
            // O no hay fichero o el fichero no es un archivo de guardado correcto. Se muestra error

            JOptionPane.showMessageDialog(frame,"El archivo que ha intentado cargar no es válido. Intente con otro.","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     *  Método JaqueMate, muestra un mensaje cuando la Pieza Rey ha muerto.
     */
    public static void jaqueMate(boolean gananBlancas) {
        String mensaje = "Ha ganado el jugador: ";


        //Dependiendo de si ganan las blancas o negras, se añadirá el nombre del Jugador Blanco o Negro.
        mensaje+= gananBlancas ? nombreBlanco : nombreNegro;

        JOptionPane.showMessageDialog(VentanaJuego.frame, mensaje, "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);

        // Como es un mensaje informativo, hagan lo que hagan con el panel, salimos al menu principal.
        SwingUtilities.invokeLater(new MenuPrincipal());
        frame.dispose();
    }

    // Funcion para crear el Panel del juego Norte.
    private JPanel panelDatos() {

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new GridLayout(3, 2, 0, 0));

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

        final JButton buttonInstantanea = new JButton("Instantanea");

        buttonInstantanea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarPartida();
            }
        });

        final JButton buttonCargarInstantanea = new JButton("Cargar Instantanea");

        buttonCargarInstantanea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarPartida();
            }
        });

        final JButton buttonNuevaPartida = new JButton("Nueva Partida");

        buttonNuevaPartida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new MenuPrincipal());
                frame.dispose();
            }
        });

        final JButton buttonSalir = new JButton("Salir");

        buttonSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        panelBotones.add(buttonInstantanea);
        panelBotones.add(buttonCargarInstantanea);
        panelBotones.add(buttonNuevaPartida);
        panelBotones.add(buttonSalir);

        panelBotones.setPreferredSize(panelBotones.getMinimumSize());

        return panelBotones;
    }
}
