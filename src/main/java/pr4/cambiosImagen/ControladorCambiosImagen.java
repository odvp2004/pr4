package pr4.cambiosImagen;

public class ControladorCambiosImagen {
    private ICommandImagen cambioCommand;

    public void setCambioCommand(ICommandImagen cambioCommand) {
        this.cambioCommand = cambioCommand;
    }
    public void execute(){
        cambioCommand.execute();
    }
}
