package IPlugin;
import java.awt.Graphics;

/*
 * Interface permettant de gérer les plugins graphiques
 */

public interface IDrawing extends IPlugin{
	public String toString();
	public void draw(Graphics g);
	public void colorLife(Graphics g);
	public void colorEnergy(Graphics g);

}
