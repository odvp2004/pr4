package pr4.accionesImagen;

import pr4.floodFill.FloodFill;
import pr4.modelo.Pizarron;

import java.awt.*;

public class Pintar {
    public static void hacer(Pizarron modelo, Point point) {
        FloodFill floodFill = new FloodFill();
        int[][] pixeles = floodFill.floodFill(modelo.getImagen().getPixeles(), point.x, point.y, modelo.getColorActual(), modelo.getRango());
        modelo.getImagen().getHistorialCambios().push(modelo.getImagen().getPixeles());
        modelo.getImagen().setPixeles(pixeles);

    }
}
