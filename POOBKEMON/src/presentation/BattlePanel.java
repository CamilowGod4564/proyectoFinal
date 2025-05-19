package presentation;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class BattlePanel extends Panel {

	private JPanel panelBatalla;
	private Container backgroundLabel;


	public BattlePanel(PoobkemonGUIProvisional gui, Panel prevPanel, Panel nextPanel, String backgroundImage) {
		super(gui, prevPanel, nextPanel, backgroundImage);
		SwingUtilities.invokeLater(() -> {
		    prepareElements();
		    prepareActions();
		});
	}

	@Override
	public void prepareElements() { 
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JPanel parteSuperior = new JPanel(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.5; 
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.NORTH;

		add(parteSuperior, gbc);

		JPanel parteInferior = new JPanel();
		gbc.gridy = 1;
		gbc.weighty = 0.5; 
		gbc.fill = GridBagConstraints.BOTH;

		add(parteInferior, gbc);
		
		parteSuperior.setOpaque(false);
		parteInferior.setOpaque(false);
		
		GridBagConstraints gbcSup = new GridBagConstraints();
		
		
		JPanel panelVidaEnemigo = new JPanel();
		gbcSup.gridx = 0;
		gbcSup.gridy = 0;
		gbcSup.weightx = 0.5;
		gbcSup.weighty = 0.5;
		gbcSup.fill = GridBagConstraints.BOTH;
		parteSuperior.add(panelVidaEnemigo, gbcSup);
		
		
		JPanel panelPokemonEnemigo = new JPanel(); 
		gbcSup.gridx = 1;
		gbcSup.gridy = 0;
		gbcSup.weightx = 0.5;
		gbcSup.weighty = 0.8;
		gbcSup.fill = GridBagConstraints.BOTH;
	    parteSuperior.add(panelPokemonEnemigo,gbcSup);
		
		JPanel panelPokemonAtacando = new JPanel(); 
		gbcSup.gridx = 0;
		gbcSup.gridy = 1;
		gbcSup.weightx = 0.5;
		gbcSup.weighty = 0.5;
		gbcSup.fill = GridBagConstraints.BOTH;
	    parteSuperior.add(panelPokemonAtacando,gbcSup);
	    
		JPanel panelVidaAtacando = new JPanel(); 
		gbcSup.gridx = 1;
		gbcSup.gridy = 1;
		gbcSup.weightx = 0.5;
		gbcSup.weighty = 0.2; 
		gbcSup.fill = GridBagConstraints.BOTH;
	    parteSuperior.add(panelVidaAtacando,gbcSup);
	    
		panelPokemonEnemigo.setOpaque(false);
		panelPokemonAtacando.setOpaque(false);
		panelVidaAtacando.setOpaque(false);
		panelVidaEnemigo.setOpaque(false);
		
		panelPokemonEnemigo.setLayout(null);
		panelPokemonAtacando.setLayout(null);
		
		
		ImageIcon enemyIcon = new ImageIcon(getClass().getResource("/presentation/recursos/frame2/0001.png"));
        JLabel enemyLabel = new JLabel(escalarIcono(enemyIcon,250,250,panelPokemonEnemigo.getHeight()));
        enemyLabel.setBounds(70, 20, 250, 250);
        panelPokemonEnemigo.add(enemyLabel); 
        
	    setupKeyBindings();
	    
	    ImageIcon attackingIcon = new ImageIcon(getClass().getResource("/presentation/recursos/frame2/0321.png"));
        JLabel attackingLabel = new JLabel(escalarIcono(attackingIcon,250,250,panelPokemonAtacando.getHeight()));
        attackingLabel.setBounds(70, -50, 250, 250);
        panelPokemonAtacando.add(attackingLabel); 
	    
	}
	
	@Override
	public void prepareActions() {
		// TODO Auto-generated method stub
		
	}
	
	
	public static ImageIcon escalarIcono(ImageIcon icon, int anchoMax, int altoMax, double panelHeight) {
	    int anchoOriginal = icon.getIconWidth();
	    int altoOriginal = icon.getIconHeight();

	    double escalaAncho = (double) anchoMax / anchoOriginal;
	    double escalaAlto = (double) altoMax / altoOriginal;
	    double escala = Math.min(escalaAncho, escalaAlto);

	    // Calculamos primero el tamaño que tendría escalado
	    int nuevoAncho = (int) (anchoOriginal * escala);
	    int nuevoAlto = (int) (altoOriginal * escala);

	    // Si el nuevo ancho sigue siendo más grande que el panel, devolvemos el original
	    if (nuevoAncho >= panelHeight) {
	        return icon;
	    }

	    // Ahora sí escalamos
	    Image imagenEscalada = icon.getImage().getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
	    return new ImageIcon(imagenEscalada);
	}

}
