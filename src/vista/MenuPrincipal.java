package vista;

import java.awt.BorderLayout;
import java.awt.Component;
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

        // Nombre Jugador Negro
        final JPanel panelNegro = new JPanel();
        boxComponentes.add(panelNegro, BorderLayout.EAST);


        final JLabel labelTextoNegro = new JLabel("Nombre del Jugador Negro: ");
        final JTextField tfTextoNegro = new JTextField("Negro", 10);

        //TODO anadir imagen
        //panelNegro.add()
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
