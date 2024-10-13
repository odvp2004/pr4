package pr4.manejoDeImagen;

import pr4.excepciones.ManejoDeImagenException;
import pr4.modelo.Imagen;

import javax.swing.*;

public interface IManejoDeImagen {

    /**
     * Cualquier tipo de manejo de imagen que se haga debe implementar esta clase.
     */

    void hacer(JFrame parent, Imagen modelo) throws ManejoDeImagenException;
}
