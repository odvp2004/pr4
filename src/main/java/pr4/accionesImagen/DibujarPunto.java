package pr4.accionesImagen;

import pr4.cambiosImagen.ChangeMatrizCommand;
import pr4.cambiosImagen.HistorialCambios;
import pr4.modelo.Pizarron;

import java.awt.*;


public class DibujarPunto {
    public static void hacer(Pizarron modelo, Point punto) {
        int[][] pixeles = new int[modelo.getImagen().getPixeles().length][];
        for (int i = 0; i < modelo.getImagen().getPixeles().length; i++) {
            pixeles[i] = modelo.getImagen().getPixeles()[i].clone();
        }

        pixeles[punto.y][punto.x] = modelo.getColorActual();
        modelo.getHistorial().ejecutarComando(new ChangeMatrizCommand(modelo, pixeles));
    }

}

