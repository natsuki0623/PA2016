package pluginAttack;

import java.awt.Point;
import java.util.ArrayList;
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
		List<Point> Enemies=new ArrayList<Point>();
		for(int i=0;i<positionEnemy.size();i++){
			if(positionEnemy.get(i).getX()<position.getX()+100+this.range() || position.getX()-100-this.range()<positionEnemy.get(i).getX()){
				if(positionEnemy.get(i).getY()<position.getY()+100+this.range() || position.getY()-100-this.range()<positionEnemy.get(i).getY()){
					Enemies.add(positionEnemy.get(i));
				}
			}
		}
		if(Enemies.isEmpty()){
			return null;			
		}
		else return Enemies.get(0);
	}

	@Override
	public int energy() {
		// TODO Auto-generated method stub
		return 15;
	}

}
