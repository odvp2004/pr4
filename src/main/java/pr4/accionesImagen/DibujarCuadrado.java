package pr4.accionesImagen;

import pr4.cambiosImagen.ChangeMatrizCommand;
import pr4.cambiosImagen.HistorialCambios;
import pr4.modelo.Pizarron;

import java.awt.*;

public class DibujarCuadrado {
    public static void hacer(Pizarron modelo, Point puntoInicial, int alto, int ancho) {

        int[][] pixeles = new int[modelo.getImagen().getPixeles().length][];
        for (int i = 0; i < modelo.getImagen().getPixeles().length; i++) {
            pixeles[i] = modelo.getImagen().getPixeles()[i].clone();
        }

        for (int y = puntoInicial.y; y <= puntoInicial.y + alto; y++) {
            pixeles[y][puntoInicial.x] = modelo.getColorActual();
            pixeles[y][puntoInicial.x + ancho] = modelo.getColorActual();
        }
        for (int x = puntoInicial.x; x <= puntoInicial.x + ancho; x++) {
            pixeles[puntoInicial.y][x] = modelo.getColorActual();
            pixeles[puntoInicial.y + alto][x] = modelo.getColorActual();
        }

        modelo.getHistorial().ejecutarComando(new ChangeMatrizCommand(modelo, pixeles));


    }
}
