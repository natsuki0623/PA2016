package pluginAttack;

import java.awt.Point;
import java.util.List;

import IPlugin.IAttack;

public class AttackRange implements IAttack {

	@Override
	public int attaque() {
		// TODO Auto-generated method stub
		return 70;
	}

	@Override
	public int range() {
		// TODO Auto-generated method stub
		return 100;
	}
	
	@Override
	public Point location(Point position, List<Point> positionEnemy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int energy() {
		// TODO Auto-generated method stub
		return 15;
	}

}
