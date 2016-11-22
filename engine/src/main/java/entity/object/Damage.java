package entity.object;

import entity.ObjectHitbox;

import java.awt.*;

/**
 * Created by JuIngong on 22/11/2016.
 */
public class Damage extends ObjectHitbox {


    public Damage(Point point, int damage) {
        super(new Rectangle(point), damage);
    }

}
