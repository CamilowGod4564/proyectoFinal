package presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.swing.*;

public class BagDialog extends JDialog {
    
    private JPanel imagePanel;
    private JScrollPane scrollPane;
    private JPanel itemsPanel;
    private JLabel selectedItemLabel;
    private String selectedItem = null;
    private boolean haveItems = true;
    private TreeMap<String,Integer> items;
	private JButton[] arrayItems;
	private BattlePanel panel;
    
    public BagDialog(JFrame parent, TreeMap<String,Integer> items,BattlePanel panel) {
        super(parent, "Items para romperla Daddy yankee", true);
        this.items = items;
        this.panel = panel;
        prepareElements();
        prepareActions();

    }

	private void prepareElements() {
		//JDIALOG
		setSize(650, 350);
		setResizable(false);
        setLocationRelativeTo(getParent());
        
        setLayout(new BorderLayout());
        
        // Panel principal con GridBagLayout para mejor control
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Panel izquierdo (imagen)
        
        imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        String ruta = "/presentation/recursos/bagImage.png";
        ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
        ImageIcon scaledIcon = scaleImage(icon, 200, 200); 
        selectedItemLabel = new JLabel(scaledIcon, SwingConstants.CENTER);
        imagePanel.add(selectedItemLabel, BorderLayout.CENTER);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.4;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 20);
        mainPanel.add(imagePanel, gbc);
        
        // Panel derecho scrolleable para los items
        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));

        arrayItems = new JButton[items.size()];
        
        for(String itemName : items.keySet()) {
        	System.out.print(itemName);
        }
        
        if(items.size() > 0) {
        	int index = 0;
        	for (String itemName : items.keySet()) {
        		int qty = items.get(itemName);
        	    JButton item = new JButton(itemName + " x" + qty);
        	    item.setAlignmentX(Component.LEFT_ALIGNMENT); 
        	    item.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); 
        	    item.setMinimumSize(new Dimension(300, 40)); 
        	    item.setBackground(Color.WHITE);
        	    item.setForeground(Color.BLACK);
        	    item.setFont(new Font("Arial", Font.BOLD, 14));
        	    itemsPanel.add(item);
        	    itemsPanel.add(Box.createVerticalStrut(10)); 
        	    arrayItems[index++] = item;
        	}
        }else {
        	haveItems = false;
        }
        // Panel derecho (lista scrolleable)

        scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        
        gbc.gridx = 1;
        gbc.weightx = 0.6;
        gbc.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(scrollPane, gbc);
        
        add(mainPanel, BorderLayout.CENTER);
        
        
        if(!haveItems) {
        	//itemsPanel.add(noItemsPanel)
        }
        
        
        JPanel rowPanel = new JPanel(new BorderLayout());
        rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        rowPanel.setPreferredSize(new Dimension(400, 80));
        rowPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        rowPanel.setOpaque(false);
        
        // Panel para el nombre del item
        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        namePanel.setPreferredSize(new Dimension(250, 70));
        
        itemsPanel.add(Box.createVerticalGlue());
        itemsPanel.revalidate();
        itemsPanel.repaint();
        
	}
	
    private void prepareActions() {
    	if(haveItems) {
    		for(JButton b: arrayItems) {
    			b.addMouseListener(new java.awt.event.MouseAdapter() {
    				@Override
    				public void mouseEntered(java.awt.event.MouseEvent evt) {
    					String imagePath = "/presentation/recursos/" + b.getText().replaceAll("\\s*[xX]\\d+$", "").trim() + ".png";
    					try {
    						ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
    						selectedItemLabel.setIcon(scaleImage(icon, 200, 200));
    					} catch (Exception e) {
						
		                	}
		            	}
    				@Override
    				public void mouseExited(java.awt.event.MouseEvent evt) {
    					// Restaurar imagen por defecto
    					String ruta = "/presentation/recursos/bagImage.png";
    					ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
    					selectedItemLabel.setIcon(scaleImage(icon, 200, 200));
    				}
    			});

    			// CLICK
    			b.addActionListener(e -> {
    				selectedItem = b.getText().replaceAll("\\s*[xX]\\d+$", "").trim();
    				PoobkemonGUIProvisional.juego.useItem(selectedItem);
    				PoobkemonGUIProvisional.juego.nextTurn();
    				panel.refresh();
    				dispose();
    			});
    		}
		}
   }

    // Método auxiliar para escalar imágenes (si decides implementar imágenes)
    private ImageIcon scaleImage(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }
}
