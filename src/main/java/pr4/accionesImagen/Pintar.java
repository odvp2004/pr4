package pr4.accionesImagen;

import pr4.cambiosImagen.ChangeMatrizCommand;
import pr4.cambiosImagen.HistorialCambios;
import pr4.floodFill.FloodFill;
import pr4.modelo.Pizarron;

import java.awt.*;

public class Pintar {
    public static void hacer(Pizarron modelo, Point point) {
        FloodFill floodFill = new FloodFill();
        int[][] pixeles = floodFill.floodFill(modelo.getImagen().getPixeles(), point.x, point.y, modelo.getColorActual(), modelo.getRango());
        modelo.getHistorial().ejecutarComando(new ChangeMatrizCommand(modelo, pixeles));
    }
}
