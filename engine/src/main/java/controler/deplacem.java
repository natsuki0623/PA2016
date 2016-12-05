package controler;

import IPlugin.IMovement;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by JuIngong on 05/12/2016.
 */
public class deplacem implements IMovement {
    @Override
    public String move(Point positionIni, java.util.List<Point> PositionEnemy) {
        List<String> movements = Arrays.asList("NONE", "WEST", "EAST", "NORTH", "SOUTH");
        int nombreAleatoire = (int) (Math.random() * ((4) + 1));
        return movements.get(nombreAleatoire);
    }
}
