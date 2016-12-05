package controler;

import IPlugin.IAttack;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by JuIngong on 05/12/2016.
 */
public class att implements IAttack {
    @Override
    public int attaque() {
        return 50;
    }

    @Override
    public int range() {
        return 1000;
    }

    @Override
    public int energy() {
        return 10;
    }

    @Override
    public Point location(Point position, java.util.List<Point> positionEnemy) {
        return positionEnemy.get(0);
    }
}
