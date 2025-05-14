package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Hashtable;


public class PoobkemonGUI extends JFrame {
    private JLabel logoPoobkemon;
    private JButton butonPressStart;
    private JButton butonIniciarPartida;
    private JButton butonOpciones;
    private JButton butonJugadorVsJugador;
    private JButton butonJugadorVsMaquina;
    private JButton butonMaquinaVsMaquina;
    private JButton butonAttackingTrainer;
    private JButton butonDefensiveTrainer;
    private JButton butonChangingTrainer;
    private JButton butonExpertTrainer;
    private JButton butonContinue;
    private JButton butonBack;
    private int numeroVista;

    //POKEDEZ
    private Boolean jugador;
    private Boolean maquina;
    private JButton agregarPlayer1;
    private JButton agregarPlayer2;
    private JButton pokedexSiguiente;
    private JButton pokedexAnterior;
    private String numeroPokemon;
    private String rutaPokemon;
    private JLabel pokemonGif;
    private JButton eliminarPokemones;


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
        accionesMenuIniciales();
    }


    private void menuInicial() {
        numeroVista = 1;
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

        ImageIcon start = new ImageIcon(getClass().getResource("/presentation/recursos/pressstart.gif"));

        butonPressStart = new JButton(start);
        botonComoImagen(butonPressStart);
        add(butonPressStart, gbc);

        // Panel 3
        gbc.gridy = 2;
        gbc.weighty = 0.2;
        gbc.weightx = 1.0;
        add(new JLabel("HECHO POR: NIKOLAS Y CAMILO :d"), gbc);


        setVisible(true);
    }

    private void accionesMenuIniciales(){
        butonPressStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numeroVista = 2;
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
                numeroVista = 3;
                menuDeSeleccion();
                accionesmenuDeSeleccion();

            }
        });

        butonOpciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

        // LAYOUT SUPERIOR (parte de arriba)
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setOpaque(false);

        // PARTE SUPERIOR IZQUIERDA (zona de texto y gif Pokemon)
        JPanel superiorIzquierda = new JPanel(new BorderLayout());
        superiorIzquierda.setOpaque(false);

        JLabel textoSeleccion = new JLabel(imagenEscalada("/presentation/recursos/texto1.png",1,4,1,6));
        textoSeleccion.setHorizontalAlignment(SwingConstants.CENTER);
        superiorIzquierda.add(textoSeleccion,BorderLayout.NORTH);

        JLabel imagenApoyo = new JLabel(new ImageIcon(getClass().getResource("/presentation/recursos/snorlax.gif")));
        imagenApoyo.setHorizontalAlignment(SwingConstants.CENTER);
        imagenApoyo.setOpaque(false);
        superiorIzquierda.add(imagenApoyo,BorderLayout.CENTER);

        textoSeleccion.setPreferredSize(new Dimension(0, getWidth()/8));
        superiorIzquierda.setPreferredSize(new Dimension(getWidth()*3/10, 0));


        // PANEL SUPERIOR DERECHA (modos de juego)
        JPanel superiorDerecha = new JPanel(new GridLayout(3,0));
        superiorDerecha.setOpaque(false);

        butonJugadorVsJugador = new JButton(imagenEscalada("/presentation/recursos/jugadorVsjugador.png",2,3,1,7));
        botonComoImagen(butonJugadorVsJugador);
        butonJugadorVsMaquina = new JButton(imagenEscalada("/presentation/recursos/jugadorVsmaquina.png",2,3,1,7));
        botonComoImagen(butonJugadorVsMaquina);
        butonMaquinaVsMaquina = new JButton(imagenEscalada("/presentation/recursos/maquinaVsmaquina.png",2,3,1,7));
        botonComoImagen(butonMaquinaVsMaquina);

        superiorDerecha.add(butonJugadorVsJugador);
        superiorDerecha.add(butonJugadorVsMaquina);
        superiorDerecha.add(butonMaquinaVsMaquina);


        panelSuperior.add(superiorDerecha, BorderLayout.CENTER);
        panelSuperior.add(superiorIzquierda, BorderLayout.WEST);


        // LAYOUT INFERIOR
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setOpaque(false);

        // PARTE INFERIOR IZQUIERDA (modos de maquina)
        JPanel inferiorIzquierda = new JPanel(new BorderLayout());
        inferiorIzquierda.setOpaque(false);

        //BORDER LAYOUT PARTE DE LOS BOTONES
        JLabel subNorte = new JLabel("Selecciona La dificultad de la maquina");
        subNorte.setHorizontalAlignment(SwingConstants.CENTER);
        subNorte.setOpaque(false);

        subNorte.setPreferredSize(new Dimension(0, getHeight()/20));
        inferiorIzquierda.add(subNorte,BorderLayout.NORTH);

        JPanel subCenter = new JPanel(new GridLayout(2,2));
        subCenter.setOpaque(false);

        butonDefensiveTrainer = new JButton(imagenEscalada("/presentation/recursos/defensiveTrainer.png",1,6,1,6));
        botonComoImagen(butonDefensiveTrainer);
        butonAttackingTrainer = new JButton(imagenEscalada("/presentation/recursos/attackingTrainer.png",1,6,1,6));
        botonComoImagen(butonAttackingTrainer);
        butonChangingTrainer = new JButton(imagenEscalada("/presentation/recursos/changingTrainer.png",1,6,1,6));
        botonComoImagen(butonChangingTrainer);
        butonExpertTrainer= new JButton(imagenEscalada("/presentation/recursos/expertTrainer.png",1,6,1,6));
        botonComoImagen(butonExpertTrainer);

        subCenter.add(butonDefensiveTrainer);
        subCenter.add(butonAttackingTrainer);
        subCenter.add(butonChangingTrainer);
        subCenter.add(butonExpertTrainer);

        inferiorIzquierda.add(subCenter,BorderLayout.CENTER);

        JPanel subWest = new JPanel();
        subWest.setOpaque(false);
        subWest.setPreferredSize(new Dimension(getWidth()/20, 0));
        inferiorIzquierda.add(subWest,BorderLayout.EAST);

        JPanel subEast = new JPanel();
        subEast.setOpaque(false);
        subEast.setPreferredSize(new Dimension(getWidth()/20,0 ));
        inferiorIzquierda.add(subEast,BorderLayout.WEST);

        JPanel subSouth = new JPanel();
        subSouth.setOpaque(false);
        subSouth.setPreferredSize(new Dimension(0, getHeight()/20));
        inferiorIzquierda.add(subSouth,BorderLayout.SOUTH);

        panelInferior.add(inferiorIzquierda, BorderLayout.WEST);

        // INFERIOR DERECHA (modo de juego y botones de continuar y atras)
        JPanel inferiorDerecha = new JPanel(new GridLayout(2,0));
        inferiorDerecha.setOpaque(false);

        // slider para modo de juego
        JPanel sliderModo = new JPanel(new BorderLayout());
        sliderModo.setOpaque(false);

        JPanel subWestInf = new JPanel();
        subWestInf.setOpaque(false);
        subWestInf.setPreferredSize(new Dimension(getWidth()/20, 0));
        sliderModo.add(subWestInf,BorderLayout.EAST);

        JPanel subEastInf = new JPanel();
        subEastInf.setOpaque(false);
        subEastInf.setPreferredSize(new Dimension(getWidth()/20,0 ));
        sliderModo.add(subEastInf,BorderLayout.WEST);

        JLabel subNorthtInf = new JLabel("MODO DE JUEGO");
        subNorthtInf.setHorizontalAlignment(SwingConstants.CENTER);
        subNorthtInf.setOpaque(false);
        subNorthtInf.setPreferredSize(new Dimension(0,getWidth()/20 ));
        sliderModo.add(subNorthtInf,BorderLayout.NORTH);


        JSlider seleccionarModo = new JSlider(0,1,0);
        seleccionarModo.setOpaque(false);

        seleccionarModo.setMajorTickSpacing(1);
        seleccionarModo.setPaintTicks(true);
        seleccionarModo.setSnapToTicks(true);
        seleccionarModo.setPaintLabels(true);

        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel("Normal"));
        labelTable.put(1, new JLabel("Survival"));
        seleccionarModo.setLabelTable(labelTable);

        sliderModo.add(seleccionarModo,BorderLayout.CENTER);

        inferiorDerecha.add(sliderModo);

        JPanel opcionesFinales = new JPanel(new GridLayout(0,2));
        opcionesFinales.setOpaque(false);

        butonContinue = new JButton(imagenEscalada("/presentation/recursos/continuar.png",2,9,2,9));
        botonComoImagen(butonContinue);
        butonBack = new JButton(imagenEscalada("/presentation/recursos/volver.png",2,9,2,9));
        botonComoImagen(butonBack);

        opcionesFinales.add(butonContinue);
        opcionesFinales.add(butonBack);

        inferiorDerecha.add(opcionesFinales);

        panelInferior.add(inferiorDerecha, BorderLayout.CENTER);


        // Establecer dimensiones preferidas para controlar tamaños iniciales
        inferiorIzquierda.setPreferredSize(new Dimension(getWidth()/2, 0)); // Ancho de 150px

        add(panelSuperior);
        add(panelInferior);
        setLocationRelativeTo(null);
        setVisible(true);

        botonComoImagen(butonJugadorVsJugador);

        setVisible(true);
    }
    private void accionesmenuDeSeleccion(){
        butonContinue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(numeroVista == 3){
                    numeroVista = 4;
                    pokedex();
                    accionesPokedex();
                }
            }
        });
        butonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(numeroVista == 2){
                    numeroVista = 1;
                    menuInicial();
                    accionesMenuIniciales();
                }else if(numeroVista == 3){
                    numeroVista = 2;
                    menuDeOpciones();
                    accionesMenuDeOpciones();
                }else if(numeroVista == 4){
                    numeroVista = 3;
                    menuDeSeleccion();
                    accionesmenuDeSeleccion();
                }
            }
        });
        butonJugadorVsJugador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jugador = true;
                maquina = false;
            }
        });
        butonJugadorVsMaquina.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jugador = true;
                maquina = true;
            }
        });
        butonMaquinaVsMaquina.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jugador = false;
                maquina = false;
            }
        });
    };

    private void pokedex(){

        // FONDO DE PANTALLAS (por decidir)
        JPanel fondoFinal = new JPanel();
        setContentPane(fondoFinal);

        //DISTRIBUCION DE PANELES
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;

        //PANEL SUPERIOR
        gbc.gridy = 0;
        gbc.weighty = 0.6;
        gbc.weightx = 1.0;
        JPanel panelSuperior = new JPanel(new BorderLayout());

        JPanel pantallaPokemon = new JPanel();
        pantallaPokemon.setPreferredSize(new Dimension(getWidth()/4,0));


        //BOTONES POKEMON E INFORMACION
        JPanel informacionPokemon = new JPanel(new GridLayout(2,0));

        //ESTADISTICAS POKEMON
        JPanel estadisticasPokemon = new JPanel();
        estadisticasPokemon.setBackground(Color.cyan);

        informacionPokemon.add(estadisticasPokemon);

        // BOTONES DE CAMBIAR POKEMON
        pokedexSiguiente = new JButton();
        pokedexAnterior = new JButton();

        JPanel botonesPokemon = new JPanel(new GridLayout(0,2));
        botonesPokemon.add(pokedexSiguiente);
        botonesPokemon.add(pokedexAnterior);

        informacionPokemon.add(botonesPokemon);


        // PARTE DE SELECCIONAR ATAQUES POKEMON
        JPanel ataquesPokemon = new JPanel(new GridLayout(3,1,0,10));
        ataquesPokemon.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel ataquesSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        ataquesSuperior.add(new JComboBox<>(new String[]{"Opción 1A", "Opción 1B"}));
        ataquesSuperior.add(new JComboBox<>(new String[]{"Opción 1C", "Opción 1D"}));

        JPanel atquesInferior = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        atquesInferior.add(new JComboBox<>(new String[]{"Opción 2A", "Opción 2B"}));
        atquesInferior.add(new JComboBox<>(new String[]{"Opción 2C", "Opción 2D"}));
        ataquesPokemon.add(ataquesSuperior);
        ataquesPokemon.add(atquesInferior);

        if(jugador && !maquina){
            JPanel agregarPokemon = new JPanel(new GridLayout(0,2));
            agregarPlayer1 = new JButton("agregar a P1");
            agregarPlayer2 = new JButton("agregar a P2");
            agregarPokemon.add(agregarPlayer1);
            agregarPokemon.add(agregarPlayer2);

            ataquesPokemon.add(agregarPokemon);
        } else if (jugador && maquina){
            agregarPlayer1 = new JButton("agregar a player");
            ataquesPokemon.add(agregarPlayer1);
        }

        ataquesPokemon.setPreferredSize(new Dimension(getWidth()/3,0));

        panelSuperior.add(pantallaPokemon,BorderLayout.WEST);
        panelSuperior.add(ataquesPokemon,BorderLayout.EAST);
        panelSuperior.add(informacionPokemon,BorderLayout.CENTER);

        add(panelSuperior, gbc);

        //PANEL INFERIOR
        gbc.gridy = 1;
        gbc.weighty = 0.4;
        gbc.weightx = 1.0;
        JPanel panelInferior = new JPanel(new BorderLayout());

        JPanel personajes = new JPanel();
        personajes.setLayout(new GridLayout(2,0));
        personajes.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 10));


        personajes.add(new JButton());
        personajes.add(new JButton());

        personajes.setPreferredSize(new Dimension(getWidth()/6,0));
        personajes.setBackground(Color.cyan);

        JPanel pokemones = new JPanel(new GridLayout(2,6));
        pokemones.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 20));
        pokemones.add(new JButton());
        pokemones.add(new JButton());
        pokemones.add(new JButton());
        pokemones.add(new JButton());
        pokemones.add(new JButton());
        pokemones.add(new JButton());
        pokemones.add(new JButton());
        pokemones.add(new JButton());
        pokemones.add(new JButton());
        pokemones.add(new JButton());
        pokemones.add(new JButton());
        pokemones.add(new JButton());

        pokemones.setBackground(Color.DARK_GRAY);

        JPanel botonesFinales = new JPanel(new GridLayout(2,0));
        eliminarPokemones = new JButton();
        eliminarPokemones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel atrasYcontinuar = new JPanel(new GridLayout(0,2));
        butonContinue = new JButton();
        butonBack = new JButton();
        atrasYcontinuar.add(butonBack);
        atrasYcontinuar.add(butonContinue);

        botonesFinales.add(eliminarPokemones);
        botonesFinales.add(atrasYcontinuar);

        botonesFinales.setPreferredSize(new Dimension(getWidth()/4,0));

        botonesFinales.setBackground(Color.GREEN);

        panelInferior.add(personajes,BorderLayout.WEST);
        panelInferior.add(pokemones,BorderLayout.CENTER);
        panelInferior.add(botonesFinales,BorderLayout.EAST);




        add(panelInferior, gbc);



        setVisible(true);
    }
    private void accionesPokedex(){
        pokedexSiguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int longitud = numeroPokemon.length();
                int valor = Integer.parseInt(numeroPokemon);
                valor++;
                numeroPokemon = String.format("%0" + longitud + "d", valor);
                rutaPokemon = "/presentation/recursos/animated/"+numeroPokemon+".gif";
                pokemonGif.setIcon(new ImageIcon(getClass().getResource(rutaPokemon)));

            }
        });
        pokedexAnterior.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int longitud = numeroPokemon.length();
                int valor = Integer.parseInt(numeroPokemon);
                valor--;
                numeroPokemon = String.format("%0" + longitud + "d", valor);
                rutaPokemon = "/presentation/recursos/animated/"+numeroPokemon+".gif";
                pokemonGif.setIcon(new ImageIcon(getClass().getResource(rutaPokemon)));
            }
        });
        butonContinue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(numeroVista == 3){
                    numeroVista = 4;
                    pokedex();
                    accionesPokedex();
                }else if(numeroVista == 4){
                    numeroVista = 5;
                    duelo();
                    accionesDuelo();
                }
            }
        });
        butonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(numeroVista == 2){
                    numeroVista = 1;
                    menuInicial();
                    accionesMenuIniciales();
                }else if(numeroVista == 3){
                    numeroVista = 2;
                    menuDeOpciones();
                    accionesMenuDeOpciones();
                }else if(numeroVista == 4){
                    numeroVista = 3;
                    menuDeSeleccion();
                    accionesmenuDeSeleccion();
                }
            }
        });

    }

    private void duelo(){
        ImageIcon fondo = new ImageIcon(getClass().getResource("/presentation/recursos/combate.png"));
        JPanel fondoFinal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        setContentPane(fondoFinal);


        fondoFinal.setLayout(new GridBagLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        //PARTE SUPERIOR(peleaPokemon)
        GridBagConstraints fondoGBC = new GridBagConstraints();
        fondoGBC.gridx = 0;
        fondoGBC.gridy = 0;
        fondoGBC.weightx = 1.0;
        fondoGBC.weighty = 1.0;
        fondoGBC.fill = GridBagConstraints.BOTH;
        fondoFinal.add(mainPanel, fondoGBC);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 0.74;
        JPanel top = new JPanel();

        top.setLayout(new GridBagLayout());
        GridBagConstraints topGbc = new GridBagConstraints();
        topGbc.fill = GridBagConstraints.BOTH;

// Panel izquierda arriba (0.5 altura, 0.4 ancho)
        topGbc.gridx = 0;
        topGbc.gridy = 0;
        topGbc.weightx = 0.5;
        topGbc.weighty = 0.5;
        JPanel topLeftUp = new JPanel();
        topLeftUp.setBackground(Color.WHITE);
        top.add(topLeftUp, topGbc);

// Panel izquierda abajo (0.5 altura, 0.4 ancho)
        topGbc.gridy = 1;
        topGbc.weighty = 0.5;
        JPanel topLeftDown = new JPanel();
        topLeftDown.setBackground(Color.PINK);
        top.add(topLeftDown, topGbc);

// Panel derecha arriba (0.2 altura, 0.6 ancho)
        topGbc.gridx = 1;
        topGbc.gridy = 0;
        topGbc.weightx = 0.5  ;
        topGbc.weighty = 0.2;  // Ajuste a 0.2 para el 20% de la altura
        JPanel topRightUp = new JPanel();
        topRightUp.setBackground(Color.YELLOW);
        top.add(topRightUp, topGbc);

// Panel derecha abajo (0.8 altura, 0.6 ancho)
        topGbc.gridy = 1;
        topGbc.weighty = 0.8;  // Ajuste a 0.8 para el 80% de la altura
        JPanel topRightDown = new JPanel();
        topRightDown.setBackground(Color.CYAN);
        top.add(topRightDown, topGbc);



        mainPanel.add(top, gbc);

        //PARTE INFERIOR
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weighty = 0.26;

        gbc.gridx = 0;
        gbc.weightx = 0.05;
        JPanel p1 = new JPanel();
        p1.setOpaque(false);
        mainPanel.add(p1, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.45;
        JPanel p2 = new JPanel();
        p2.setBackground(Color.BLUE);
        mainPanel.add(p2, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.44;
        JPanel p3 = new JPanel();
        p3.setBackground(Color.MAGENTA);
        mainPanel.add(p3, gbc);

        gbc.gridx = 3;
        gbc.weightx = 0.06;
        JPanel p4 = new JPanel();
        p4.setOpaque(false);
        mainPanel.add(p4, gbc);

        setVisible(true);

    }
    private void accionesDuelo(){

    }

    /**
     * Metodo para configuara las imagenes que sean botones, quitando fondo, marco ETC...
     * @param b referenciando al boton
     */
    private void botonComoImagen(JButton b) {

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
     *
     * @param ruta
     * @param anchoN
     * @param anchoD
     * @param altoN
     * @param altoD
     * @return
     */
    private ImageIcon imagenEscalada(String ruta,int anchoN,int anchoD,int altoN,int altoD){


        Image jugadorVsjugador = new ImageIcon(getClass().getResource(ruta)).getImage();
        Image jugadorVsjugadorEscalada = jugadorVsjugador.getScaledInstance((getWidth()*anchoN)/anchoD, (getHeight()*altoN)/altoD, Image.SCALE_SMOOTH);
        return new ImageIcon(jugadorVsjugadorEscalada);
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
