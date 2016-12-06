package IPlugin;

/*
 * Interface permettant de gérer les plugins d'attaques
 */

import java.awt.*;
import java.util.List;

public interface IAttack extends IPlugin{
	public String toString();
	public int attaque();
	public int range();
	public int energy();
	public Point location(Point position, List<Point>positionEnemy);
	public void animation(Graphics g);
}
