package pluginDrawing;
import java.awt.Color;
import java.awt.Graphics;

import IPlugin.IDrawing;

public class PacManDrawing implements IDrawing{

	public void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillArc(0,0,150,150,30,300);
	}

}
