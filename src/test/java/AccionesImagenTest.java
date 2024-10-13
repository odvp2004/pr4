import org.junit.Test;
import pr4.accionesImagen.DibujarCuadrado;
import pr4.accionesImagen.DibujarLinea;
import pr4.accionesImagen.DibujarPunto;
import pr4.accionesImagen.Pintar;
import pr4.modelo.Imagen;
import pr4.modelo.Pizarron;

import java.awt.*;

import static org.junit.Assert.assertArrayEquals;

public class AccionesImagenTest {
    @Test
    public void dibujarLinea() {
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

        //Asert
        int[][] pixelesEsperados = {
                {0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        assertArrayEquals(pixelesEsperados, modelo.getImagen().getPixeles());
    }
    @Test
    public void dibujarCuadrado(){
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
        DibujarCuadrado.hacer(modelo, new Point(1,1), 2,3);

        //Assert
        int[][] pixelesEsperados = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {0, 1, 1, 1, 1}
        };
        assertArrayEquals(pixelesEsperados, modelo.getImagen().getPixeles());
    }

    @Test
    public void pintarConCubeta(){
        //Arrange
        Pizarron modelo = new Pizarron(new Imagen());
        modelo.setColorActual(1);
        modelo.setHerramientaSeleccionada(Pizarron.HERRAMIENTA_CUBETA);

        int c1 = Color.ORANGE.getRGB();
        int c2 = Color.GREEN.getRGB();

        int[][] pixeles = {
                {c2, c2, c1, c1, c2},
                {c2, c1, c2, c1, c1},
                {c1, c2, c2, c2, c1},
                {c2, c1, c1, c1, c1}
        };
        modelo.getImagen().setPixeles(pixeles);

        //Act
        Pintar.hacer(modelo, new Point(2,2));

        //Assert
        int[][] pixelesEsperados = {
                {c2, c2, c1, c1, c2},
                {c2, c1, 1, c1, c1},
                {c1, 1, 1, 1, c1},
                {c2, c1, c1, c1, c1}
        };
        assertArrayEquals(pixelesEsperados, modelo.getImagen().getPixeles());
    }
    @Test
    public void dibujarPunto(){
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
        DibujarPunto.hacer(modelo, new Point(1,1));

        //Assert
        int[][] pixelesEsperados = {
                {0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        assertArrayEquals(pixelesEsperados, modelo.getImagen().getPixeles());
    }



}
