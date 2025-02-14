import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import javax.swing.*;

public class Pacman extends JPanel{
    private int cantFilas = 21;
    private int cantColumnas = 19;
    private int tamPared = 32;
    private int anchoVentana = cantColumnas * tamPared;
    private int largoVentana = cantFilas * tamPared;

    private Image imagenPared;
    private Image imagenFantasmaAzul;
    private Image imagenFantasmaRojo;
    private Image imagenFantasmaNaranja;
    private Image imagenFantasmaRosa;
    
    private Image imagenPacmanArriba;
    private Image imagenPacmanAbajo;
    private Image imagenPacmanIzquierda;
    private Image imagenPacmanDerecha;
    
    Pacman() {
        setPreferredSize(new Dimension(anchoVentana, largoVentana));
        setBackground(Color.BLACK);
        imagenPared = new ImageIcon(getClass().getResource("./wall.png")).getImage();
    }
}
