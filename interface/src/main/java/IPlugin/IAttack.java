package IPlugin;

/*
 * Interface permettant de gérer les plugins d'attaques
 */

import java.awt.*;

public interface IAttack {
	public String toString();
	public int attaque();
	public int range();
	public Point location(Point position);
}
