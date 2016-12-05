package model.entity.robot;

import IPlugin.IAttack;
import IPlugin.IDrawing;
import IPlugin.IMovement;
import model.Direction;
import model.entity.ObjectHitbox;

import java.awt.*;

/**
 * Class created on 09/11/2016
 *
 * @author JuIngong
 */
public class Robot extends ObjectHitbox {

    public static final int SPEED = 10;

    private int life;
    private int energy;

    private IAttack attack;
    private IMovement movement;
    private IDrawing drawing;

    public Robot(int life, int energy, IAttack attack, IMovement movement, Rectangle hitbox, IDrawing drawing) {
        super(hitbox, Type.Robot.name());
        this.life = life;
        this.energy = energy;
        this.attack = attack;
        this.movement = movement;
        this.drawing = drawing;
    }

    public Robot(int life, int energy, Rectangle hitbox) {
        super(hitbox, Type.Robot.name());
        this.life = life;
        this.energy = energy;
    }

    /**
     * Deplacement en utilisant le plugin
     */

    public void move(Direction direction) {
        if (Direction.EAST.equals(direction)) {
            hitBox.x += SPEED;
        } else if (Direction.WEST.equals(direction)) {
            hitBox.x -= SPEED;
        } else if (Direction.NORTH.equals(direction)) {
            hitBox.y -= SPEED;
        } else if (Direction.SOUTH.equals(direction)) {
            hitBox.y += SPEED;
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

    public IDrawing getDrawing() {
        return drawing;
    }

    public void setDrawing(IDrawing drawing) {
        this.drawing = drawing;
    }
}
