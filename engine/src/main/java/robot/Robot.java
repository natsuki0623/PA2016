package robot;

import IPlugin.IAttack;
import IPlugin.IMovement;

import java.awt.*;

/**
 * Class created on 09/11/2016
 *
 * @author JuIngong
 */
public class Robot {

    private int life;
    private int energy;
    private Point position;
    private IAttack attack;
    private IMovement movement;

    public Robot(int life, int energy, Point position, IAttack attack, IMovement movement) {
        this.life = life;
        this.energy = energy;
        this.position = position;
        this.attack = attack;
        this.movement = movement;
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

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
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
