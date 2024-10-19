import org.junit.Test;
import pr4.accionesImagen.DibujarLinea;
import pr4.cambiosImagen.ChangeMatrizCommand;
import pr4.modelo.Imagen;
import pr4.modelo.Pizarron;

import java.awt.*;

import static org.junit.Assert.assertArrayEquals;

public class CambiosImagenTest {
    @Test
    public void undo() {
        //Arrange
        Pizarron modelo = new Pizarron(new Imagen());
        modelo.setColorActual(1);
        modelo.setHerramientaSeleccionada(Pizarron.HERRAMIENTA_LINEA);
        int[][] pixeles = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        modelo.getImagen().setPixeles(pixeles);

        //Act
        DibujarLinea.hacer(modelo, new Point(1,2), new Point(3,0));

        int[][] pixeles1 = {
                {0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };

        DibujarLinea.hacer(modelo, new Point(0,2), new Point(0,0));

        int[][] pixeles2 = {
                {1, 0, 0, 1, 0},
                {1, 0, 1, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        modelo.getHistorial().undo();
        //Asert
        assertArrayEquals(pixeles1, modelo.getImagen().getPixeles());

    }
}
