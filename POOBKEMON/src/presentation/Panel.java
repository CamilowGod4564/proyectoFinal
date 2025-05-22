package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Clase base para todos los paneles de la aplicación Poobkemon
 */
public abstract class Panel extends JPanel {
    protected PoobkemonGUIProvisional gui;
    protected Panel prevPanel;
    protected Panel nextPanel;
    protected String backgroundImage;
    
    /**
     * Constructor de la clase Panel
     * 
     * @param gui Referencia a la ventana principal
     * @param prevPanel Panel anterior en la secuencia
     * @param nextPanel Panel siguiente en la secuencia
     * @param backgroundImage Ruta de la imagen de fondo
     */
    public Panel(PoobkemonGUIProvisional gui, Panel prevPanel, Panel nextPanel, String backgroundImage) {
        this.gui = gui;
        this.prevPanel = prevPanel;
        this.nextPanel = nextPanel;
        this.backgroundImage = backgroundImage;
        setLayout(new BorderLayout());
    }
    
    /**
     * Método para preparar los elementos visuales del panel
     * Debe ser implementado por las clases hijas
     */
    public abstract void prepareElements();
    
    /**
     * Método para preparar las acciones de los componentes
     * Debe ser implementado por las clases hijas
     */
    public abstract void prepareActions();
    
    /**
     * Método para navegar al panel anterior
     */
    public void goToPrevious() {
        if (prevPanel != null) {
            gui.changePanel(prevPanel.getClass().getSimpleName());
        }
    }
    
    /**
     * Método para navegar al panel siguiente
     */
    public void goToNext() {
        if (nextPanel != null) {
            gui.changePanel(nextPanel.getClass().getSimpleName());
        }
    }
    
    /**
     * Dibuja el fondo del panel si hay una imagen configurada
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (backgroundImage != null && !backgroundImage.isEmpty()) {
            ImageIcon icon = new ImageIcon(getClass().getResource(backgroundImage));
            g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
   
    protected void botonComoImagen(JButton b) {

        b.setContentAreaFilled(false);
        b.setFocusPainted(false);

    }
    
    protected void setupKeyBindings() {
        // Tecla Escape para salir
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "exit");
        actionMap.put("exit", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.exit();
            }
        });
    }

	public boolean getJugador() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getMaquina() {
		// TODO Auto-generated method stub
		return false;
	}

    
}