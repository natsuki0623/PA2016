package view;

import IPlugin.IDrawing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by JuIngong on 05/12/2016.
 */
public class PanelRobot extends JPanel {

    private IDrawing drawing;

    public PanelRobot(IDrawing drawing, Rectangle rectangle) {
        this.drawing = drawing;
        setBounds(rectangle);
        setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawing.draw(g);
        g.fillRect(0,0, getWidth(), getHeight());


    }
}
