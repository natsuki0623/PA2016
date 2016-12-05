package pluginMovement;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import IPlugin.IMovement;

public class MovementEscape implements IMovement{

	@Override
	public String move(Point positionIni, List<Point> PositionEnemy) {
		double dif;
		List<Point> Enemies = new ArrayList<Point>();
		double x1=positionIni.getX();
		/* Comparaison du X de chaque ennemi par rapport au robot ciblé*/
		
		/*Definit le premier comme étant le plus proche*/
		if(PositionEnemy.get(0).getX()>=x1){
			dif=PositionEnemy.get(0).getX()-x1;
		}
		else {
			dif=x1-PositionEnemy.get(0).getX();
		}
		Enemies.add(PositionEnemy.get(0));
		
		/*Verifie chaque autre ennemi s'il est plus proche*/
		for(int i=1; i<PositionEnemy.size();i++){
			if(PositionEnemy.get(i).getX()>=x1){
				double tempdif=PositionEnemy.get(i).getX()-x1;
				if( tempdif<dif){
					Enemies.clear();
					Enemies.add(PositionEnemy.get(i));
					dif=tempdif;
				}
				if(tempdif==dif){
					Enemies.add(PositionEnemy.get(i));					
				}
			}
			else if(PositionEnemy.get(i).getX()<x1){
				double tempdif=x1-PositionEnemy.get(i).getX();
				if(tempdif<dif){
					Enemies.clear();
					Enemies.add(PositionEnemy.get(i));
					dif=tempdif;
				}
			}
		}
		double difx=dif;
		double y1=positionIni.getY();
		Point closerEnnemy=Enemies.get(0);		
		/*Comparaison du Y des ennemis préselectionnés comme étant les plus proches en X*/
		if(Enemies.size()>1){
			/*Définit le premier comme étant le plus proche*/
			if(Enemies.get(0).getY()>=y1){
				dif=Enemies.get(0).getY()-y1;
			}
			else {
				dif=y1-Enemies.get(0).getY();
			}
			
			/*Verifie chaque autre ennemi s'il est plus proche*/
			for(int i=1; i<Enemies.size(); i++){
				if(Enemies.get(i).getY()>y1){
					double tempdif=Enemies.get(i).getY()-y1;
					if( tempdif<dif){
						dif=tempdif;
						closerEnnemy=Enemies.get(i);
					}
				}
				else if(Enemies.get(i).getY()<y1){
					double tempdif=y1-Enemies.get(i).getY();
					if(tempdif<dif){
						dif=tempdif;
						closerEnnemy=Enemies.get(i);
					}
				}
				
			}
		}
		
		/* Analyse de la direction à prendre*/
		
		double dify=dif;
		double x2= closerEnnemy.getX();
		double y2=closerEnnemy.getY();
		if(dify>=difx){
			if(y2>y1) return "NORTH";
			else return "SOUTH";
		}
		if(difx>dify){
			if(x1>x2) return "EAST";
			else return "WEST";			
		}
		
		return "NONE";
	}
}


