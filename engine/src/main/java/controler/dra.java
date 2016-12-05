package controler;

import IPlugin.IDrawing;

import java.awt.*;
import java.util.Random;

/**
 * Created by JuIngong on 05/12/2016.
 */
public class dra implements IDrawing {
    @Override
    public void draw(Graphics g) {
        Random rand = new Random();
        float r = rand.nextFloat();
        float v = rand.nextFloat();
        float b = rand.nextFloat();
        g.setColor(new Color(r, v, b));
    }
}
