package controler;

import IPlugin.IAttack;

import java.awt.*;
import java.util.*;

/**
 * Created by JuIngong on 05/12/2016.
 */
public class att implements IAttack {
    @Override
    public int attaque() {
        return 0;
    }

    @Override
    public int range() {
        return 0;
    }

    @Override
    public int energy() {
        return 0;
    }

    @Override
    public Point location(Point position, java.util.List<Point> positionEnemy) {
        return null;
    }
}
