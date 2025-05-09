package presentation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class PoobkemonGUI extends JFrame {
    private JLabel logoPoobkemon;
    private JButton butonPressStart;
    private JButton butonIniciarPartida;
    private JButton butonOpciones;
    private JButton butonJugadorVsJugador;
    private JButton butonJugadorVsMaquina;
    private JButton butonMaquinaVsMaquina;

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
        ImageIcon fondo = new ImageIcon(getClass().getResource("/presentation/recursos/fondoPokemon2.gif"));

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

        Image logo = new ImageIcon(getClass().getResource("/presentation/recursos/logo_Poobkemon.png")).getImage();
        Image logoEscalada = logo.getScaledInstance(getWidth()*3/4, getHeight()/2, Image.SCALE_SMOOTH);
        logoPoobkemon = new JLabel(new ImageIcon(logoEscalada));
        add(logoPoobkemon, gbc);

        // Panel 2
        gbc.gridy = 1;
        gbc.weighty = 0.3;
        gbc.weightx = 1.0;

        Image start = new ImageIcon(getClass().getResource("/presentation/recursos/pressstart.gif")).getImage();
        Image startEscalada = start.getScaledInstance(getWidth()/4, 75, Image.SCALE_SMOOTH);

        butonPressStart = new JButton(new ImageIcon(startEscalada));
        botonComoImagen(butonPressStart);
        add(butonPressStart, gbc);

        // Panel 3 - Ocupa 2/10 de la altura
        gbc.gridy = 2;
        gbc.weighty = 0.2;
        gbc.weightx = 1.0;
        add(new JLabel("ECI :D"), gbc);


        setVisible(true);
    }
    
    private void accionesMenuIniciales(){
        butonPressStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarJFrame();
                menuDeOpciones();
                accionesMenuDeOpciones();
            }
        });
    }

    private void menuDeOpciones(){

        ImageIcon fondo = new ImageIcon(getClass().getResource("/presentation/recursos/fondo1.png"));
        JPanel fondoFinal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        setContentPane(fondoFinal);

        Image iniciarPartida = new ImageIcon(getClass().getResource("/presentation/recursos/iniciarPartida.png")).getImage();
        Image iniciarPartidaEscalada = iniciarPartida.getScaledInstance(getWidth()*7/8, getHeight()/4, Image.SCALE_SMOOTH);
        butonIniciarPartida = new JButton(new ImageIcon(iniciarPartidaEscalada));
        botonComoImagen(butonIniciarPartida);

        Image opciones = new ImageIcon(getClass().getResource("/presentation/recursos/opciones.png")).getImage();
        Image opcionesEscalada = opciones.getScaledInstance(getWidth()*7/8, getHeight()/4, Image.SCALE_SMOOTH);
        butonOpciones = new JButton(new ImageIcon(opcionesEscalada));
        botonComoImagen(butonOpciones);

        setLayout(new GridLayout(4,0));
        add(butonIniciarPartida);
        add(butonOpciones);
        add(new JLabel());
        add(new JLabel());
        setVisible(true);

    }
    private void accionesMenuDeOpciones(){
        butonIniciarPartida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarJFrame();
                menuDeSeleccion();

            }
        });

        butonOpciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarJFrame();
            }
        });

    }

    private void menuDeSeleccion(){
        ImageIcon fondo = new ImageIcon(getClass().getResource("/presentation/recursos/fondo1.png"));
        JPanel fondoFinal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        setContentPane(fondoFinal);


        //LAYOUT PRINCIPAL
        setLayout(new GridLayout(2,0));

        // LAYOUT SECUNDARIO 1
        JPanel panel1 = new JPanel(new BorderLayout()); // Espaciado horizontal y vertical de 10px

        // Crear los paneles para cada región con colores distintos

        // Panel NORTE (arriba)
        JPanel panelNorte1 = crearPanelConEtiqueta("NORTE (North)", Color.RED);
        panel1.add(panelNorte1, BorderLayout.NORTH);

        // Panel SUR (abajo)
        JPanel panelSur1 = crearPanelConEtiqueta("SUR (South)", Color.BLUE);
        panel1.add(panelSur1, BorderLayout.SOUTH);

        // Panel ESTE (derecha)
        JPanel panelEste1 = crearPanelConEtiqueta("ESTE (East)", Color.GREEN);
        panel1.add(panelEste1, BorderLayout.EAST);

        // Panel OESTE (izquierda)
        JPanel panelOeste1 = crearPanelConEtiqueta("OESTE (West)", Color.YELLOW);
        panel1.add(panelOeste1, BorderLayout.WEST);

        // Panel CENTRO (centro)
        JPanel panelCentro1 = crearPanelConEtiqueta("CENTRO (Center)", Color.ORANGE);
        panel1.add(panelCentro1, BorderLayout.CENTER);

        // Establecer dimensiones preferidas para controlar tamaños iniciales
        panelNorte1.setPreferredSize(new Dimension(0, 0)); // Alto de 100px
        panelSur1.setPreferredSize(new Dimension(0, 0)); // Alto de 100px
        panelEste1.setPreferredSize(new Dimension(0, 0)); // Ancho de 150px
        panelOeste1.setPreferredSize(new Dimension(getWidth()*3/10, 0)); // Ancho de 150px


        // LAYOUT SECUNDARIO 2
        JPanel panel2 = new JPanel(new BorderLayout()); // Espaciado horizontal y vertical de 10px
        panel2.setBackground(Color.WHITE);

        // Crear los paneles para cada región con colores distintos

        // Panel NORTE (arriba)
        JPanel panelNorte2 = crearPanelConEtiqueta("NORTE (North)", Color.RED);
        panel2.add(panelNorte2, BorderLayout.NORTH);

        // Panel SUR (abajo)
        JPanel panelSur2 = crearPanelConEtiqueta("SUR (South)", Color.BLUE);
        panel2.add(panelSur2, BorderLayout.SOUTH);

        // Panel ESTE (derecha)
        JPanel panelEste2 = crearPanelConEtiqueta("ESTE (East)", Color.GREEN);
        panel2.add(panelEste2, BorderLayout.EAST);

        // Panel OESTE (izquierda)
        JPanel panelOeste2 = crearPanelConEtiqueta("OESTE (West)", Color.YELLOW);
        panel2.add(panelOeste2, BorderLayout.WEST);

        // Panel CENTRO (centro)
        JPanel panelCentro2 = crearPanelConEtiqueta("CENTRO (Center)", Color.ORANGE);
        panel2.add(panelCentro2, BorderLayout.CENTER);

        // Establecer dimensiones preferidas para controlar tamaños iniciales
        panelNorte2.setPreferredSize(new Dimension(0, 0)); // Alto de 100px
        panelSur2.setPreferredSize(new Dimension(0, 0)); // Alto de 100px
        panelEste2.setPreferredSize(new Dimension(0, 0)); // Ancho de 150px
        panelOeste2.setPreferredSize(new Dimension(getWidth()/2, 0)); // Ancho de 150px

        add(panel1);
        add(panel2);
        setLocationRelativeTo(null);
        setVisible(true);


        Image jugadorVsjugador = new ImageIcon(getClass().getResource("/presentation/recursos/jugadorVsjugador.png")).getImage();
        Image jugadorVsjugadorEscalada = jugadorVsjugador.getScaledInstance(getWidth()*3/5, getHeight()/8, Image.SCALE_SMOOTH);
        butonJugadorVsJugador = new JButton(new ImageIcon(jugadorVsjugadorEscalada));
        botonComoImagen(butonJugadorVsJugador);






        setVisible(true);
    }
    private JPanel crearPanelConEtiqueta(String texto, Color color) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(color);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JLabel label = new JLabel(texto);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(Color.WHITE);
        panel.add(label, BorderLayout.CENTER);

        return panel;
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
    
    /**
     * Limpia completamente un JFrame: remueve todos los componentes
     *
     */
    public void limpiarJFrame() {
        setContentPane(new JPanel());
        getContentPane().removeAll();
        repaint();
        revalidate();
    }
}
