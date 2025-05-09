package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class PoobkemonGUI extends JFrame {
    private JLabel logoPoobkemon;
    private JButton pressStart;

    PoobkemonGUI(){
        prepareElements();
        prepareAcctions();

    }
    public static void main(String[] args) { new PoobkemonGUI(); }

    /**
     * Elementos principales sobre la pantalla y demas
     */
    private void prepareElements() {
        setTitle("Poobkemon Esmerald Edition");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = d.width;
        int alto = d.height;
        int anchoFinal = (int)(ancho * 0.5);
        int altoFinal = (int)(anchoFinal * 2.0 / 3.0);

        setSize(anchoFinal,altoFinal);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
        animacionInicial();

    }

    private void prepareAcctions() {
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) {
                exit();
            }
        });
    }

    /**
     * Animacion inicial con musica antes que empiece el juego
     */
    private void animacionInicial() {
        menuInicial();
    }


    private void menuInicial() {
        ImageIcon fondo = new ImageIcon(getClass().getResource("/recursos/fondoPokemon2.gif"));

        JPanel fondoFinal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        setContentPane(fondoFinal);


        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Panel 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.5;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        Image logo = new ImageIcon(getClass().getResource("/recursos/logo_Poobkemon.png")).getImage();
        Image logoEscalada = logo.getScaledInstance(getWidth()*3/4, getHeight()/2, Image.SCALE_SMOOTH);
        logoPoobkemon = new JLabel(new ImageIcon(logoEscalada));
        add(logoPoobkemon, gbc);

        // Panel 2
        gbc.gridy = 1;
        gbc.weighty = 0.3;
        gbc.weightx = 1.0;

        Image start = new ImageIcon(getClass().getResource("/recursos/pressStart.png")).getImage();
        Image startEscalada = start.getScaledInstance(getWidth()/4, 75, Image.SCALE_SMOOTH);

        pressStart = new JButton(new ImageIcon(startEscalada));
        botonComoImagen(pressStart);
        add(pressStart, gbc);

        // Panel 3 - Ocupa 2/10 de la altura
        gbc.gridy = 2;
        gbc.weighty = 0.2;
        gbc.weightx = 1.0;
        add(new JLabel("ECI :D"), gbc);


        setVisible(true);
    }


    /**
     * Metodo para configuara las imagenes que sean botones, quitando fondo, marco ETC...
     * @param b referenciando al boton
     */
    private void botonComoImagen(JButton b) {
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
    }

    /**
     * SALIDA / EXIT
     */
    public void exit() {
        int respuesta = JOptionPane.showConfirmDialog(
                null,
                "¿Estás seguro de que quieres cerrar la aplicación?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (respuesta == JOptionPane.YES_OPTION){
            dispose();
            System.exit(0);
        }
    }
}
