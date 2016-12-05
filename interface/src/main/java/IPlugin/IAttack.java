package IPlugin;

/*
 * Interface permettant de gérer les plugins d'attaques
 */

import com.sun.istack.internal.Nullable;

import java.awt.*;
import java.util.List;

public interface IAttack extends IPlugin{
	public String toString();
	public int attaque();
	public int range();
	public int energy();
	@Nullable
	public Point location(Point position, List<Point>positionEnemy);
}
