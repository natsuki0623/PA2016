package pluginAttack;

import java.awt.Point;
import java.util.List;

import IPlugin.IAttack;

public class AttackCac implements IAttack{

	public int attaque() {
		
		return 70;
	}

	public int range() {
		return 10;
	}

	public Point location(Point position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point location(Point position, List<Point> positionEnemy) {
		// TODO Auto-generated method stub
		return null;
	}

}