package presentation;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SelectPanel extends Panel{


	private JButton butonContinue;
	private JButton butonJugadorVsJugador;
	private JButton butonBack;
	private JButton butonDefensiveTrainer;
	private JButton butonAttackingTrainer;
	private JButton butonChangingTrainer;
	private JButton butonExpertTrainer;
	private JButton butonJugadorVsMaquina;
	private JButton butonMaquinaVsMaquina;
	private JButton seleccionarPrimerModo;
	private JButton seleccionarSegundoModo;
	protected boolean isSurvival = false;
	protected boolean jugador = true;
	protected boolean maquina = true;
	private String machineType;

	public SelectPanel(PoobkemonGUIProvisional gui, Panel prevPanel, Panel nextPanel, String backgroundImage) {
		super(gui, prevPanel, nextPanel, backgroundImage);
		SwingUtilities.invokeLater(() -> {

		    prepareElements();
		    prepareActions();
		});
	}

	@Override
	public void prepareElements() {
		
		
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
        JPanel inferiorDerecha = new JPanel(new GridLayout(2,1,0,15));
        inferiorDerecha.setOpaque(false);
        
        JPanel modos = new JPanel(new GridBagLayout());
        modos.setOpaque(false);
        

		GridBagConstraints gbcModos = new GridBagConstraints();
		
		gbcModos.gridx = 0;
		gbcModos.anchor = GridBagConstraints.CENTER;
		gbcModos.fill = GridBagConstraints.NONE;
		gbcModos.insets = new Insets(10, 0, 10, 0);
        
        //Titulo modo
        JLabel modo = new JLabel("Elige el modo de juego", SwingConstants.CENTER);
        modo.setFont(new Font("Arial", Font.BOLD, 18));
        gbcModos.weightx = 1.0;
        gbcModos.weighty = 0.1;
        gbcModos.gridy = 0;
        gbcModos.fill = GridBagConstraints.BOTH; 
        modos.add(modo,gbcModos);
        
        // MODO JUEGO
        JPanel modosEspeciales = new JPanel(new GridLayout(1,2,20,0));
        modosEspeciales.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        modosEspeciales.setOpaque(false);

        seleccionarPrimerModo = new JButton("Survival");
        seleccionarSegundoModo = new JButton("Normal");
        seleccionarPrimerModo.setOpaque(false);
        seleccionarSegundoModo.setOpaque(false);

        modosEspeciales.add(seleccionarPrimerModo);
        modosEspeciales.add(seleccionarSegundoModo);
        
        gbcModos.weighty = 0.9; 
        gbcModos.fill = GridBagConstraints.BOTH; 
        gbcModos.gridy = 1;
        modos.add(modosEspeciales,gbcModos);
        
        
        inferiorDerecha.add(modos);

        JPanel opcionesFinales = new JPanel(new GridLayout(1,2));
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


        botonComoImagen(butonJugadorVsJugador);
      
        setupKeyBindings();
	}

	@Override
	public void prepareActions() {
		butonContinue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	nextPanel.removeAll();
            	makeDuel();
            	nextPanel.Ready();
            	gui.changePanel(PoobkemonGUIProvisional.PLAYER_PANEL);
            }
        });
		
        butonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gui.changePanel(PoobkemonGUIProvisional.INIT_PANEL); 
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
        seleccionarPrimerModo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                isSurvival = true;
            }
        });
        seleccionarSegundoModo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                isSurvival = false;
            }
        });
        
        butonDefensiveTrainer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                machineType = "Defensive";
            }
        });

        butonAttackingTrainer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                machineType = "Attacking";
            }
        });

        butonChangingTrainer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                machineType = "Changing";
            }
        });

        butonExpertTrainer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                machineType = "Expert";
            }
        });
        
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
   
   @Override
   public boolean getMaquina() {	
	   return this.maquina;
   }
   
   @Override
   public boolean getJugador() {
	   return this.jugador;
	
   }
   public boolean getSurvival() {
	   return this.isSurvival;
	
   }
   
   public void makeDuel() {
	   if(isSurvival) {
       	PoobkemonGUIProvisional.juego.setDuelMode("Survival");
       }else {
       	PoobkemonGUIProvisional.juego.setDuelMode("Normal");
       }
   }
   
   @Override
   public String getMachineType() {
	return machineType; 
   }
   
}
