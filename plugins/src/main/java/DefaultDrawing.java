import java.awt.Color;
import java.awt.Graphics;

import IPlugin.IDrawing;

public class DefaultDrawing implements IDrawing{

	public void draw(Graphics g) {
		g.setColor(Color.red);
	}

}
