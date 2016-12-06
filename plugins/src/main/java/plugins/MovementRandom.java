package plugins;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;

import IPlugin.IMovement;

public class MovementRandom implements IMovement {

	@Override
	public String move(Point positionIni, List<Point> PositionEnemy) {
		List<String> movements=Arrays.asList("NONE","WEST","EAST","NORTH","SOUTH");
		int nombreAleatoire = (int)(Math.random() * ((4) + 1));
		return movements.get(nombreAleatoire);
	}

}
