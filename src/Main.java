
import vista.MenuPrincipal;

import javax.swing.*;

public class Main implements Runnable {
    public void run() {
        SwingUtilities.invokeLater(new MenuPrincipal());
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main());
    }
}
