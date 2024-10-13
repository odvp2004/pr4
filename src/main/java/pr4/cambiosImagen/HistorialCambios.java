package pr4.cambiosImagen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pr4.cambiosImagen.modeloLista.Pila;

public class HistorialCambios{
    private Pila<ChangeMatrizCommand> undoStack = new Pila<>();
    private static final Logger logger = LogManager.getRootLogger();

    public void ejecutarComando(ChangeMatrizCommand comando){
        comando.execute();
        undoStack.push(comando);
        logger.info("Se ha guardado el cambio realizado en al imagen");
    }
    public void undo(){

        if(!undoStack.isEmpty()){

            ICommandImagen comando = undoStack.pop();
            logger.info("Se ha revertido el cambio realizado en la imagen");
            comando.undo();
        }
        else{
            logger.info("No hay cambios para deshacer en la imagen");
        }

    }



}