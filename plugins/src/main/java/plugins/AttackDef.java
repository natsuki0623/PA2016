package plugins;

import IPlugin.IAttack;

import java.awt.*;

/**
 * Class created on 06/12/2016
 *
 * @author JuIngong
 */
public class AttackDef implements IAttack {
    @Override
    public int attaque() {
        return 5;
    }

    @Override
    public int range() {
        return 150;
    }

    @Override
    public int energy() {
        return 10;
    }

    @Override
    public Point location(Point position, java.util.List<Point> positionEnemy) {
        if (!positionEnemy.isEmpty()) {
            return positionEnemy.get(0);
        }
        return null;
    }

    @Override
    public void animation(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(20, 20, 10, 10);
    }
}
