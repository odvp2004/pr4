package pr4.vista;

import pr4.accionesImagen.*;
import pr4.cambiosImagen.HistorialCambios;
import pr4.modelo.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pr4.modelo.Pizarron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ImagenPanel extends JPanel implements MouseListener, MouseMotionListener, PropertyChangeListener {
    private Pizarron modelo;
    private RightPanel rightpanel;
    private Point puntoFinal;
    private Point puntoInicial;
    private boolean figuraEnCurso;
    private static final Logger logger = LogManager.getRootLogger();

    public ImagenPanel(Pizarron modelo, RightPanel rightPanel) {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.modelo = modelo;
        this.modelo.getImagen().addObserver(this);
        this.modelo.addObserver(this);
        this.rightpanel = rightPanel;
        figuraEnCurso = false;

        puntoInicial = new Point();
        puntoFinal = new Point();
    }

    @Override
    public Dimension getPreferredSize() {
        if (modelo.getImagen().getWidth() > 0 && modelo.getImagen().getHeight() > 0) {
            return new Dimension(modelo.getImagen().getWidth(), modelo.getImagen().getHeight());
        }
        return new Dimension(500, 400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        modelo.getImagen().dibujar(g);

        if (figuraEnCurso && validarEvento(puntoFinal)) {
            previsualizacionFiguras(puntoFinal.x, puntoFinal.y, g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (validarEvento(e.getPoint())) {
            puntoFinal = e.getPoint();
            puntoInicial = e.getPoint();
            if (modelo.getHerramientaSeleccionada().equals(Pizarron.HERRAMIENTA_RECTANGULO) || modelo.getHerramientaSeleccionada().equals(Pizarron.HERRAMIENTA_LINEA)) {
                figuraEnCurso = true;
            }

        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (validarEvento(e.getPoint())) {
            realizarAccionDePintado(e.getPoint());
            figuraEnCurso = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int newX = e.getX();
        int newY = e.getY();
        if (validarEvento(e.getPoint()) && modelo.getHerramientaSeleccionada().equals(Pizarron.HERRAMIENTA_PINCEL)) {
            DibujarLinea.hacer(modelo, new Point(puntoFinal.x, puntoFinal.y), new Point(newX, newY));
        }
        if (modelo.getImagen().contains(e.getPoint())) {
            puntoFinal = new Point(newX, newY);
        }
        if (figuraEnCurso) {
            repaint();
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() instanceof Imagen) {
            logger.info("Se ha detectado cambios en el modelo");
        }
        repaint();

    }

    public void realizarAccionDePintado(Point point) {
        switch (modelo.getHerramientaSeleccionada()) {
            case Pizarron.HERRAMIENTA_CUBETA:
                Pintar.hacer(modelo, point);
                break;
            case Pizarron.HERRAMIENTA_PINCEL:
                DibujarPunto.hacer(modelo, point);
                break;
            case Pizarron.HERRAMIENTA_LINEA:
                DibujarLinea.hacer(modelo, puntoInicial, puntoFinal);
                break;
            case Pizarron.HERRAMIENTA_RECTANGULO:
                int x = puntoInicial.x;
                int y = puntoInicial.y;

                int ancho = Math.abs(puntoInicial.x - puntoFinal.x);
                int alto = Math.abs(puntoInicial.y - puntoFinal.y);

                if (puntoInicial.y > puntoFinal.y) {
                    y = puntoFinal.y;
                }
                if (puntoInicial.x > puntoFinal.x) {
                    x = puntoFinal.x;
                }
                DibujarCuadrado.hacer(modelo, new Point(x, y), alto, ancho);
                break;
        }

        logger.info("Se ha realizdo una accion sobre la imagen y se ha guardado el cambio");
    }

    public void previsualizacionFiguras(int x, int y, Graphics g) {
        switch (modelo.getHerramientaSeleccionada()) {

            case Pizarron.HERRAMIENTA_RECTANGULO:
                g.setColor(new Color(modelo.getColorActual()));

                int ancho = Math.abs(puntoInicial.x - x);
                int alto = Math.abs(puntoInicial.y - y);

                int xIzq = puntoInicial.x;
                int yIzq = puntoInicial.y;

                if (puntoInicial.y > y) {
                    yIzq = y;
                }
                if (puntoInicial.x > x) {
                    xIzq = x;
                }


                g.drawRect(xIzq, yIzq, ancho, alto);
                break;
            case Pizarron.HERRAMIENTA_LINEA:

                g.setColor(new Color(modelo.getColorActual()));
                g.drawLine(puntoInicial.x, puntoInicial.y, x, y);
                break;
        }
    }

    public boolean validarEvento(Point e) {
        return (modelo.getHerramientaSeleccionada() != null) && modelo.getImagen().contains(e);
    }

}
