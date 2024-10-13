package pr4.cambiosImagen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pr4.cambiosImagen.modeloLista.Lista;

public class HistorialCambios extends Lista {

    private static final Logger logger = LogManager.getRootLogger();

    public void push(int[][] nuevaMatriz){
        logger.info("Se ha guardado el cambio en el historial de pixeles");
        insertar(nuevaMatriz);
    }

    public int[][] pop(){
        int[][] ultimaMatriz = (int[][]) obtener(0);
        if(tam >1){
            logger.info("Se ha extraido el cambio del historial de pixeles");
            eliminar(0);
        }
        return ultimaMatriz;
    }

    public boolean isEmpty(){
        return tam==0;
    }



}