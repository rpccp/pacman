import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import javax.swing.*;

public class Pacman extends JPanel implements ActionListener{

    class Bloque {
        int x;
        int y;
        int ancho;
        int largo;
        Image imagen;

        int inicioX;
        int inicioY;
        
        Bloque(Image imagen, int x, int y, int ancho, int largo) {
            this.imagen = imagen;
            this.x = x;
            this.y = y;
            this.ancho = ancho;
            this.largo = largo;
            this.inicioX = x;
            this.inicioY = y;
        }
    }

    private int cantFilas = 21;
    private int cantColumnas = 19;
    private int tamPared = 35;
    private int anchoVentana = cantColumnas * tamPared;
    private int largoVentana = cantFilas * tamPared;

    private Image paredImage;
    private Image comidaImage;

    private Image fantasmaAzulArribaImage;
    private Image fantasmaAzulAbajoImage;
    private Image fantasmaAzulIzqImage;
    private Image fantasmaAzulDerImage;

    private Image fantasmaRojoArribaImage;
    private Image fantasmaRojoAbajoImage;
    private Image fantasmaRojoIzqImage;
    private Image fantasmaRojoDerImage;

    private Image fantasmaNaranjaArribaImage;
    private Image fantasmaNaranjaAbajoImage;
    private Image fantasmaNaranjaIzqImage;
    private Image fantasmaNaranjaDerImage;

    private Image fantasmaRosaArribaImage;
    private Image fantasmaRosaAbajoImage;
    private Image fantasmaRosaIzqImage;
    private Image fantasmaRosaDerImage;

    private Image pacmanArribaImage;
    private Image pacmanAbajoImage;
    private Image pacmanIzqImage;
    private Image pacmanDerImage;

    

    //X = pared, O = saltear, P = pacman, ' ' = comida
    //Ghosts: b = azul, o = naranja, p = rosa, r = rojo
    private String[] mapa = {
        "XXXXXXXXXXXXXXXXXXX",
        "X        X        X",
        "X XX XXX X XXX XX X",
        "X                 X",
        "X XX X XXXXX X XX X",
        "X    X       X    X",
        "XXXX XXXX XXXX XXXX",
        "OOOX X       X XOOO",
        "XXXX X XXrXX X XXXX",
        "O       bpo       O",
        "XXXX X XXXXX X XXXX",
        "OOOX X       X XOOO",
        "XXXX X XXXXX X XXXX",
        "X        X        X",
        "X XX XXX X XXX XX X",
        "X  X     P     X  X",
        "XX X X XXXXX X X XX",
        "X    X   X   X    X",
        "X XXXXXX X XXXXXX X",
        "X                 X",
        "XXXXXXXXXXXXXXXXXXX" 
    };

    HashSet<Bloque> paredes;
    HashSet<Bloque> comidas;
    HashSet<Bloque> fantasmas;
    Bloque pacman;

    Pacman() {
        setPreferredSize(new Dimension(anchoVentana, largoVentana));
        setBackground(Color.BLACK);

        paredImage = new ImageIcon(getClass().getResource("./pared.png")).getImage();
        comidaImage = new ImageIcon(getClass().getResource("./bola.png")).getImage();

        pacmanArribaImage = new ImageIcon(getClass().getResource("./pacmanArriba1.png")).getImage();
        pacmanAbajoImage = new ImageIcon(getClass().getResource("./pacmanAbajo1.png")).getImage();
        pacmanIzqImage = new ImageIcon(getClass().getResource("./pacmanIzq1.png")).getImage();
        pacmanDerImage = new ImageIcon(getClass().getResource("./pacmanDer1.png")).getImage();

        fantasmaAzulArribaImage = new ImageIcon(getClass().getResource("./fantasmaAzulArriba1.png")).getImage();
        fantasmaAzulAbajoImage = new ImageIcon(getClass().getResource("./fantasmaAzulAbajo1.png")).getImage();
        fantasmaAzulIzqImage = new ImageIcon(getClass().getResource("./fantasmaAzulIzq1.png")).getImage();
        fantasmaAzulDerImage = new ImageIcon(getClass().getResource("./fantasmaAzulDer1.png")).getImage();

        fantasmaNaranjaArribaImage = new ImageIcon(getClass().getResource("./fantasmaNaranjaArriba1.png")).getImage();
        fantasmaNaranjaAbajoImage = new ImageIcon(getClass().getResource("./fantasmaNaranjaAbajo1.png")).getImage();
        fantasmaNaranjaIzqImage = new ImageIcon(getClass().getResource("./fantasmaNaranjaIzq1.png")).getImage();
        fantasmaNaranjaDerImage = new ImageIcon(getClass().getResource("./fantasmaNaranjaDer1.png")).getImage();

        fantasmaRojoArribaImage = new ImageIcon(getClass().getResource("./fantasmaRojoArriba1.png")).getImage();
        fantasmaRojoAbajoImage = new ImageIcon(getClass().getResource("./fantasmaRojoAbajo1.png")).getImage();
        fantasmaRojoIzqImage = new ImageIcon(getClass().getResource("./fantasmaRojoIzq1.png")).getImage();
        fantasmaRojoDerImage = new ImageIcon(getClass().getResource("./fantasmaRojoDer1.png")).getImage();

        fantasmaRosaArribaImage = new ImageIcon(getClass().getResource("./fantasmaRosaArriba1.png")).getImage();
        fantasmaRosaAbajoImage = new ImageIcon(getClass().getResource("./fantasmaRosaAbajo1.png")).getImage();
        fantasmaRosaIzqImage = new ImageIcon(getClass().getResource("./fantasmaRosaIzq1.png")).getImage();
        fantasmaRosaDerImage = new ImageIcon(getClass().getResource("./fantasmaRosaDer1.png")).getImage();

        cargarMapa();
    }

    public void cargarMapa() {
        paredes = new HashSet<Bloque>();
        comidas = new HashSet<Bloque>();
        fantasmas = new HashSet<Bloque>();

        for (int f = 0; f < cantFilas; f++) {
            for (int c = 0; c < cantColumnas; c++) {
                String fila = mapa[f];
                char mapaChar = fila.charAt(c);

                int x = c * tamPared;
                int y = f * tamPared;

                if (mapaChar == 'X') {
                    Bloque pared = new Bloque(paredImage, x, y, tamPared, tamPared);
                    paredes.add(pared);
                }
                else if (mapaChar == 'b') {
                    Bloque fantasma = new Bloque(fantasmaAzulDerImage, x, y, tamPared, tamPared);
                    fantasmas.add(fantasma);
                }
                else if (mapaChar == 'o') {
                    Bloque fantasma = new Bloque(fantasmaNaranjaDerImage, x, y, tamPared, tamPared);
                    fantasmas.add(fantasma);
                }
                else if (mapaChar == 'r') {
                    Bloque fantasma = new Bloque(fantasmaRojoDerImage, x, y, tamPared, tamPared);
                    fantasmas.add(fantasma);
                }
                else if (mapaChar == 'p') {
                    Bloque fantasma = new Bloque(fantasmaRosaDerImage, x, y, tamPared, tamPared);
                    fantasmas.add(fantasma);
                }
                else if (mapaChar == 'P') {
                    pacman = new Bloque(pacmanDerImage, x, y, tamPared, tamPared);
                }
                else if (mapaChar == ' ') {
                    Bloque comida = new Bloque(comidaImage, x, y, tamPared, tamPared);
                    comidas.add(comida);
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(pacman.imagen, pacman.x, pacman.y, pacman.ancho, pacman.largo, null);

        for (Bloque fantasma : fantasmas) {
            g.drawImage(fantasma.imagen, fantasma.x, fantasma.y, fantasma.ancho, fantasma.largo, null);
        }

        for (Bloque pared : paredes) {
            g.drawImage(pared.imagen, pared.x, pared.y, pared.ancho, pared.largo, null);
        }

        for (Bloque comida : comidas) {
            g.drawImage(comida.imagen, comida.x, comida.y, comida.ancho, comida.largo, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}