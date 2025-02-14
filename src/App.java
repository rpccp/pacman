import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        int cantFilas = 21;
        int cantColumnas = 19;
        int tamPared = 32;
        int anchoVentana = cantColumnas * tamPared;
        int largoVentana = cantFilas * tamPared;

        JFrame ventana = new JFrame("Pacman");
        ventana.setSize(anchoVentana, largoVentana);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Pacman juegoPacman = new Pacman();
        ventana.add(juegoPacman);
        ventana.pack();
        ventana.setVisible(true);
    }
}
