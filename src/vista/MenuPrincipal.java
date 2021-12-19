package vista;

import utilidades.Imagenes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Clase MenuPrincipal, ventana inicial de la aplicacion, permite iniciar el juego o cerrarlo.
 */

public class MenuPrincipal implements Runnable {
    public void run() {
        final JFrame ventana = new JFrame("Ajedrez");
        // Set window properties
        ventana.setLocation(300,100);
        ventana.setResizable(false);
        ventana.setSize(560, 540);
        
        Box boxComponentes = Box.createVerticalBox();
        ventana.add(boxComponentes);
        
        // Titulo del Juego
        final JPanel panelTitulo = new JPanel();
        boxComponentes.add(panelTitulo);

        final JLabel labelTitulo = new JLabel("Ajedrez");
        panelTitulo.add(labelTitulo);

        BufferedImage bi;
        try {
            bi = ImageIO.read(Objects.requireNonNull(getClass().getResource(Imagenes.UTILIDADES_IMAGEN_MENU)));
            ImageIcon ic = new ImageIcon(bi);
            Image imagen = ic.getImage();
            imagen = imagen.getScaledInstance(185, 120,  java.awt.Image.SCALE_SMOOTH);
            ic = new ImageIcon(imagen);

            JLabel imagenLabel = new JLabel(ic);
            panelTitulo.add(imagenLabel);
        } catch (IOException e) {
            System.out.println("Fallo al carga la imagen del menú");
        }


        // Nombre Jugador Negro
        final JPanel panelNegro = new JPanel();
        boxComponentes.add(panelNegro, BorderLayout.EAST);


        final JLabel labelTextoNegro = new JLabel("Nombre del Jugador Negro: ");
        final JTextField tfTextoNegro = new JTextField("Negro", 10);

        panelNegro.add(labelTextoNegro);
        panelNegro.add(tfTextoNegro);

        // Nombre Jugador Blanco
        final JPanel panelBlanco = new JPanel();
        boxComponentes.add(panelBlanco);

        final JLabel labelTextoBlanco = new JLabel("Nombre del Jugador Blanco: ");
        final JTextField tfTextoBlanco = new JTextField("Blanco", 10);

        panelBlanco.add(labelTextoBlanco);
        panelBlanco.add(tfTextoBlanco);

        
        // Botones
        Box botones = Box.createHorizontalBox();
        final JButton salir = new JButton("Salir");
        
        salir.addActionListener(e -> ventana.dispose());
        
        final JButton comenzar = new JButton("Comenzar");
        
        comenzar.addActionListener(e -> {
            String stringNegro = tfTextoNegro.getText();
            String stringBlanco = tfTextoBlanco.getText();

            new VentanaJuego(stringNegro, stringBlanco);
            ventana.dispose();
        });
        
        botones.add(comenzar);
        botones.add(Box.createHorizontalStrut(20));
        botones.add(Box.createHorizontalStrut(20));
        botones.add(salir);
        boxComponentes.add(botones);
        
        Component space = Box.createGlue();
        boxComponentes.add(space);

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }
}
