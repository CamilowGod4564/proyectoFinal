package presentation;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;

public class MovementButtons {
	private static JButton boton1 = new JButton();
    private static JButton boton2 = new JButton();
    private static JButton boton3 = new JButton();
    private static JButton boton4 = new JButton();

    public static void actualizarNombresDeBotones(String nombre1, String nombre2, String nombre3, String nombre4) {
        boton1.setText(nombre1);
        boton2.setText(nombre2);
        boton3.setText(nombre3);
        boton4.setText(nombre4);
    }
    
    public static ArrayList<JButton> getButtons() {
    	return new ArrayList<>(Arrays.asList(boton1, boton2, boton3, boton4));
    }
}

