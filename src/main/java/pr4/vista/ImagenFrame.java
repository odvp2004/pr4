package pr4.vista;

import pr4.cambiosImagen.ControladorCambiosImagen;
import pr4.cambiosImagen.ICommandImagen;
import pr4.cambiosImagen.Undo;
import pr4.excepciones.ManejoDeImagenException;
import pr4.manejoDeImagen.AbrirImagen;
import pr4.manejoDeImagen.GuardarImagen;
import pr4.manejoDeImagen.IManejoDeImagen;
import pr4.modelo.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pr4.modelo.Pizarron;

import javax.swing.*;
import java.awt.*;



public class ImagenFrame extends JFrame {
    private Pizarron modelo;
    private ImagenPanel centerPanel;
    private RightPanel rightPanel;
    private JMenu jMenuArchivo, jMenuEditar;
    private JMenuItem nuevoItem, guardarItem, salirItem, undoItem;
    private JMenuBar jMenubar;
    private static final Logger logger = LogManager.getRootLogger();
    private ControladorCambiosImagen controladorImagen;


    public ImagenFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(400, 480));
        this.controladorImagen = new ControladorCambiosImagen();

        init();
    }

    private void init() {
        modelo = new Pizarron(new Imagen());
        rightPanel = new RightPanel(modelo);
        rightPanel.setPreferredSize(new Dimension(150, 0));

        centerPanel = new ImagenPanel(modelo, rightPanel);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(centerPanel, BorderLayout.CENTER);
        this.getContentPane().add(rightPanel, BorderLayout.EAST);

        nuevoItem = new JMenuItem("Cargar Imagen");
        guardarItem = new JMenuItem("Guardar Imagen");
        salirItem = new JMenuItem("Salir");

        undoItem = new JMenuItem("Undo");

        salirItem.addActionListener(e -> {
            logger.info("Se ha cerrado el programa");
            System.exit(0);
        });

        nuevoItem.addActionListener(e -> manejarImagen(new AbrirImagen()));
        guardarItem.addActionListener(e -> manejarImagen(new GuardarImagen()));

        undoItem.addActionListener(e -> executeEditCommand(new Undo(modelo)));

        jMenuArchivo = new JMenu("Archivo");
        jMenuArchivo.add(nuevoItem);
        jMenuArchivo.add(guardarItem);
        jMenuArchivo.add(salirItem);
        jMenubar = new JMenuBar();
        jMenubar.add(jMenuArchivo);

        jMenuEditar = new JMenu("Editar");
        jMenuEditar.add(undoItem);
        jMenubar.add(jMenuEditar);

        setJMenuBar(jMenubar);
        centerPanel.setPreferredSize(new Dimension(500, 400));


        this.pack();
        this.setVisible(true);
    }

    private void manejarImagen(IManejoDeImagen manejoDeImagen) {
        try {
            manejoDeImagen.hacer(this, modelo.getImagen());
            logger.info("El manejo de la imagen ha sido exitoso");
        } catch (ManejoDeImagenException e) {
            logger.error("El manejo de la imagen tubo un error");
            e.printStackTrace();
        }

    }

    private void executeEditCommand(ICommandImagen cambioImagen) {
        controladorImagen.setCambioCommand(cambioImagen);
        controladorImagen.execute();
        logger.info("Se ha ejecutado el comando");
    }

}
