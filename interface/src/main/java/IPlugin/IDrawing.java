package IPlugin;
import java.awt.Graphics;

/*
 * Interface permettant de gérer les plugins graphiques
 */

public interface IDrawing {
	public String toString();
	public void draw(Graphics g);
}
