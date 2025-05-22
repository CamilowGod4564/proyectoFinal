package presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BagDialog extends JDialog{
	
	private ArrayList<String> bagItems;
	private JPanel panelBotones;
	private JPanel panelTitulo;
	private JLabel titulo;
	private JScrollPane scrollPane;
	
	
	public BagDialog(JFrame parent, ArrayList<String> bagItems) {
        super(parent, "Maleta de Objetos", true);
        this.bagItems = bagItems;
        
        prepareElements();
        prepareAcctions();
        
    }
    
	private void prepareElements() {
		setLayout(new BorderLayout());
	        
		// Panel superior con título
		panelTitulo = new JPanel();
		titulo = new JLabel("Selecciona el item que usarás:");
		titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 16f));
		panelTitulo.add(titulo);

		panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(0, 1, 5, 5)); 
		panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		scrollPane = new JScrollPane(panelBotones);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scrollPane.setPreferredSize(new Dimension(400, 300));
	        
	    // Panel inferior con botón cerrar
	    JPanel panelInferior = new JPanel(new FlowLayout());
	    JButton btnCerrar = new JButton("Cerrar");

	    add(panelTitulo, BorderLayout.NORTH);
	    add(scrollPane, BorderLayout.CENTER);
	    add(panelInferior, BorderLayout.SOUTH);
	    
	}
		
    private void prepareAcctions() {
		// TODO Auto-generated method stub
		
	}
	
	
}
