package presentation;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PoobkemonGUIProvisional extends JFrame {
	
	// Card layout for managing panel transitions
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    // All the panels from your UML diagram
    private InitPanel initPanel;
    private SelectPanel selectPanel;
    private PlayerPanel playerPanel;
    private PokedexPanel pokedexPanel;
    private ItemsPanel itemsPanel;
    private BattlePanel battlePanel;
    
    // Constants for panel names
    public static final String INIT_PANEL = "InitPanel";
    public static final String SELECT_PANEL = "SelectPanel";
    public static final String PLAYER_PANEL = "PlayerPanel";
    public static final String POKEDEX_PANEL = "PokedexPanel";
    public static final String ITEMS_PANEL = "ItemsPanel";
    public static final String BATTLE_PANEL = "BattlePanel";
    
    public PoobkemonGUIProvisional() {
    	
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
       
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        initPanel = new InitPanel(this, null, null, "/presentation/recursos/fondoPrincipal.gif");
        selectPanel = new SelectPanel(this, initPanel, null, "/presentation/recursos/fondo1.png");
        playerPanel = new PlayerPanel(this, selectPanel, null, "/presentation/recursos/fondoPlayer.gif");
        pokedexPanel = new PokedexPanel(this, playerPanel, null, "/presentation/recursos/fondoPokedex.gif");
        itemsPanel = new ItemsPanel(this, pokedexPanel, null, "/presentation/recursos/fondoItems.gif");
        battlePanel = new BattlePanel(this, itemsPanel, null, "/presentation/recursos/fondoBattle.gif");

        // Add panels to the card layout
        mainPanel.add(initPanel, INIT_PANEL);
        mainPanel.add(selectPanel, SELECT_PANEL);
        mainPanel.add(playerPanel, PLAYER_PANEL);
        mainPanel.add(pokedexPanel, POKEDEX_PANEL);
        mainPanel.add(itemsPanel, ITEMS_PANEL);
        mainPanel.add(battlePanel, BATTLE_PANEL);
        
        // Start with the init panel
        cardLayout.show(mainPanel, INIT_PANEL);
        
        // Add the main panel to the frame
        add(mainPanel);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit(); // Muestra el diálogo de confirmación
            }
        });
    }
    
    /**
     * Changes the current panel
     * @param panelName The name of the panel to display
     */
    public void changePanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
        mainPanel.revalidate(); 
        mainPanel.repaint();  
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	PoobkemonGUIProvisional gui = new PoobkemonGUIProvisional();
            gui.setVisible(true);
        });
    }
    
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
