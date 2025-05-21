package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class HealthBar extends JPanel {
	
	 	private int currentHP;
	    private int maxHP;

	    public HealthBar(int maxHP) {
	        this.maxHP = maxHP;
	        this.currentHP = maxHP;
	        setPreferredSize(new Dimension(150, 20)); 
	    }

	    public void setHP(int hp) {
	        this.currentHP = Math.max(0, Math.min(hp, maxHP));
	        repaint();
	    }

	    public int getHP() {
	        return currentHP;
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        double percent = (double) currentHP / maxHP;
	        int width = (int) (getWidth() * percent);

	        Color barColor;
	        if (percent > 0.5) {
	            barColor = new Color(0x4CAF50); 
	        } else if (percent > 0.2) {
	            barColor = new Color(0xFFEB3B); 
	        } else {
	            barColor = new Color(0xF44336); 
	        }

	        g.setColor(Color.BLACK);
	        g.fillRect(0, 0, getWidth(), getHeight());

	        g.setColor(new Color(0x222222));
	        g.fillRect(1, 1, getWidth() - 2, getHeight() - 2);

	        g.setColor(barColor);
	        g.fillRect(1, 1, width - 2, getHeight() - 2);
	   }
}


