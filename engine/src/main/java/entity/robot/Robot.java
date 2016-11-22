package entity.robot;

import IPlugin.IAttack;
import IPlugin.IMovement;
import entity.ObjectHitbox;

import java.awt.*;

/**
 * Class created on 09/11/2016
 *
 * @author JuIngong
 */
public class Robot extends ObjectHitbox {

    public static final int SPEED = 5;

    private int life;
    private int energy;

    private IAttack attack;
    private IMovement movement;

    public Robot(int life, int energy, IAttack attack, IMovement movement, Rectangle hitbox) {
        super(hitbox, 0);
        this.life = life;
        this.energy = energy;
        this.attack = attack;
        this.movement = movement;
    }

    /**
     * Deplacement en utilisant le plugin
     */

    public void move() {
        Point point = this.movement.move(getPosition());
        if (point.getX() > hitBox.getX()) {
            hitBox.x += SPEED;
        } else if (point.getX() < hitBox.getX()) {
            hitBox.x -= SPEED;
        }
        if (point.getY() > hitBox.getY()) {
            hitBox.y -= SPEED;
        } else if (point.getY() < hitBox.getY()) {
            hitBox.y += SPEED;
        }
    }

    /**
     * Attaque en utilisant le plugin
     */
    public void attack() {
        Point dam = this.attack.location(getPosition());

        int range = new Double(Math.sqrt(
                Math.pow(this.getPosition().getX() - dam.getX(), 2) +
                        Math.pow(this.getPosition().getY() - dam.getY(), 2)))
                .intValue();
        if (range <= this.attack.range()) {
            new ObjectHitbox(new Rectangle(dam), this.attack.attaque());
        }
    }


    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public IAttack getAttack() {
        return attack;
    }

    public void setAttack(IAttack attack) {
        this.attack = attack;
    }

    public IMovement getMovement() {
        return movement;
    }

    public void setMovement(IMovement movement) {
        this.movement = movement;
    }
}
