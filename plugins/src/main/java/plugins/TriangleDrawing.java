package plugins;

import IPlugin.IDrawing;

import java.awt.Color;
import java.awt.Graphics;

public class TriangleDrawing implements IDrawing {

	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillPolygon(new int[] {0,25,50}, new int[] {50, 0, 50}, 3);
	}

	@Override
	public void colorLife(Graphics g) {
		g.setColor(Color.red);
	}

	@Override
	public void colorEnergy(Graphics g) {
		g.setColor(Color.green);
	}
}
