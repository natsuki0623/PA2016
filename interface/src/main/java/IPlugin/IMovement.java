package IPlugin;

import java.awt.Point;
import java.util.List;

public interface IMovement extends IPlugin{

	/**
	 *
	 * @param positionIni position du robot
	 * @param PositionEnemy position des enemi
	 * @return "EAST", "WEST", "NORTH", "SOUTH"
	 */
	
	public String move(Point positionIni, List<Point> PositionEnemy);
}
