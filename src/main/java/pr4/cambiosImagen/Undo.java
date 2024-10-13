package pr4.cambiosImagen;

import pr4.modelo.Pizarron;

public class Undo implements ICommandImagen {

    private Pizarron modelo;
    private HistorialCambios historialCambios;

    public Undo(Pizarron modelo){
        this.modelo = modelo;
        this.historialCambios = modelo.getImagen().getHistorialCambios();
    }
    @Override
    public void execute() {
        if(!historialCambios.isEmpty()){
            modelo.getImagen().setPixeles(historialCambios.pop());
        }

    }
}
