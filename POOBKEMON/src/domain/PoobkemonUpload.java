package domain;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PoobkemonUpload {
    public static void saveGame(PoobkemonGame game, String ruta) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ruta))) {
            out.writeObject(game);
            System.out.println("✅ Juego guardado en" + ruta);
        } catch (IOException e) {
            System.err.println("Error al guar");
            e.printStackTrace();
        }
    }

    public static PoobkemonGame openGame(String ruta) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ruta))) {
            PoobkemonGame game = (PoobkemonGame) in.readObject();
            System.out.println("✅ Juego cargado desde: " + ruta);
            return game;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar:");
            return null;
        }
    }
}

