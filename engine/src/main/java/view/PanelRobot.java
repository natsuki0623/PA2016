package view;

import IPlugin.IAttack;
import IPlugin.IDrawing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by JuIngong on 05/12/2016.
 */
public class PanelRobot extends JPanel {

    private IDrawing drawing;
    private IAttack attack;
    private int opacity;
    private boolean att;

    public PanelRobot(IDrawing drawing, Rectangle rectangle) {
        opacity = 255;
        this.drawing = drawing;
        setBounds(rectangle);
        setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawing.draw(g);
        g.setColor(new Color(Color.red.getRed(),Color.red.getGreen(),Color.red.getBlue(),opacity));
        g.fillRect(0,0,50,10);
        if(att){
            attack.animation(g);
        }
    }

    public void showAtt(){
        att = true;
    }
    public void hideAtt(){
        att = false;
    }

    public void setOpacity(int opacity) {
        this.opacity = opacity;
    }

    public void setDrawing(IDrawing drawing) {
        this.drawing = drawing;
    }

    public void setAttack(IAttack attack) {
        this.attack = attack;
    }
}
