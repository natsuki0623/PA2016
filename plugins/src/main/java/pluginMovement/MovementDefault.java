package pluginMovement;

import java.awt.Point;

import IPlugin.IMovement;

public class MovementDefault implements IMovement{

	public String move(Point positionIni){
		int x=(int) positionIni.getX();
		int y=(int) positionIni.getY();
		x++;
		if(x>9){
			x=0;
		}
		Point positionFin=new Point(x,y);
		return positionFin.toString();
	}
	
}
