package plugins;
import java.awt.Color;
import java.awt.Graphics;

import IPlugin.IDrawing;

public class DefaultDrawing implements IDrawing{

	public void draw(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, 50, 50);
	}

	@Override
	public void colorLife(Graphics g) {
		g.setColor(Color.red);
	}

	@Override
	public void colorEnergy(Graphics g) {
		g.setColor(Color.blue);
	}

}
