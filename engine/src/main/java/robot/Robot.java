package robot;

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


    public Robot(Point position) {
        this.life = 100;
        this.energy = 100;
        this.position = position;
    }

    public Robot(int x, int y) {
        this.life = 100;
        this.energy = 100;
        this.position = new Point(x, y);
    }

    public Robot(int life, int energy, int x, int y) {
        this.life = life;
        this.energy = energy;
        this.position = new Point(x, y);
    }

    public Robot(int life, int energy, Point position) {
        this.life = life;
        this.energy = energy;
        this.position = position;
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


}
