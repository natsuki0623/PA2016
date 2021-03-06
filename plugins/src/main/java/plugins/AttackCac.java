package plugins;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import IPlugin.IAttack;

public class AttackCac implements IAttack{

	public int attaque() {
		
		return 5;
	}

	public int range() {
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
	public void animation(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(20, 20, 10, 10);
	}

	@Override
	public int energy() {
		// TODO Auto-generated method stub
		return 10;
	}

}
