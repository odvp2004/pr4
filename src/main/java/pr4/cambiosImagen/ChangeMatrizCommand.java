package pr4.cambiosImagen;

import pr4.modelo.Pizarron;

public class ChangeMatrizCommand implements ICommandImagen {

    private Pizarron modelo;
    private int[][] oldValue;
    private int[][] newValue;

    public ChangeMatrizCommand(Pizarron modelo, int[][] pixeles){
        this.modelo = modelo;
        this.oldValue = modelo.getImagen().getPixeles();
        this.newValue = pixeles;
    }
    @Override
    public void execute() {
        modelo.getImagen().setPixeles(newValue);
    }

    @Override
    public void undo() {
        modelo.getImagen().setPixeles(oldValue);
    }

    public int[][] getLastValue(){
        return newValue;
    }
}
