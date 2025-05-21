package presentation;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

public class InitPanel extends Panel {
    private JLabel logoPoobkemon;
    private JButton buttonPressStart;

    /**
     * Constructor del panel inicial
     * @param gui Referencia a la ventana principal
     * @param prevPanel Panel anterior (null para el inicial)
     * @param nextPanel Panel siguiente
     * @param backgroundImage Ruta de la imagen de fondo
     */
    public InitPanel(PoobkemonGUIProvisional gui, Panel prevPanel, Panel nextPanel, String backgroundImage) {
        super(gui, prevPanel, nextPanel, backgroundImage);
        prepareElements();
        prepareActions();
    }

    /**
     * Configura los elementos visuales del panel
     */
    @Override
    public void prepareElements() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.5;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;


        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/presentation/recursos/logo_Poobkemon.png"));

        logoPoobkemon = new JLabel(logoIcon);
        logoPoobkemon.setHorizontalAlignment(SwingConstants.CENTER);
        add(logoPoobkemon, gbc);


        gbc.gridy = 1;
        gbc.weighty = 0.3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        ImageIcon startIcon = new ImageIcon(getClass().getResource("/presentation/recursos/pressstart.gif"));
        buttonPressStart = new JButton(startIcon);
        botonComoImagen(buttonPressStart);
        add(buttonPressStart, gbc);


        gbc.gridy = 3;
        gbc.weighty = 0.1;
        
        JLabel creditsLabel = new JLabel("HECHO POR: NIKOLAS Y CAMILO :D");
        add(creditsLabel, gbc);

        setupKeyBindings();
        
    }

    /**
     * Configura las acciones de los componentes
     */
    @Override
    public void prepareActions() {
        // Acción para el botón de inicio
        buttonPressStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambiar al siguiente panel usando CardLayout
                gui.changePanel(PoobkemonGUIProvisional.SELECT_PANEL);
            }
        });
    }
    

    /**
     * Configura los Key Bindings para acciones con teclado
     */
    @Override
	protected void setupKeyBindings() {
    	super.setupKeyBindings();
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();
        // Tecla Enter para iniciar el juego
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "start");
        actionMap.put("start", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.changePanel(PoobkemonGUIProvisional.SELECT_PANEL);
            }
        });
    }

}
