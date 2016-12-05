package controler;

import model.Direction;
import model.Model;
import model.entity.ObjectHitbox;
import model.entity.robot.Robot;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by JuIngong on 22/11/2016.
 */
public class RobotAction {
    private static final long TIME = 100;

    private Robot robot;


    private Timer timer;
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Model model = Model.getModel();
            Direction direction = Direction.getDirection(robot.getMovement().move(robot.getPosition(), model.listEnemy(robot)));
            if (direction != null && !Direction.NONE.equals(direction)) {
                moveRobot(direction);
            }

            Point dam = robot.getAttack().location(robot.getPosition(), model.listEnemy(robot));
            if (dam != null) {
                int range = (int) Math.sqrt(Math.pow(robot.getPosition().getX() - dam.getX(), 2) + Math.pow(robot.getPosition().getY() - dam.getY(), 2));
                if (range <= robot.getAttack().range()) {
                    attackRobot(dam);
                }
            }
        }
    };

    public RobotAction(Robot robot) {
        this.robot = robot;
        //init directions

        timer = new Timer();
        timer.schedule(timerTask, 0, TIME);
    }


    /**
     *
     * @param point
     */
    private void attackRobot(Point point){
        Model model = Model.getModel();
        model.doDamage(robot, point);
    }

    /**
     * tentative de deplacement du robot dans une direction
     * @param direction direction demlandé
     */
    private void moveRobot(Direction direction) {
        Model model = Model.getModel();
        List<ObjectHitbox> listObj = model.listObjHitbox(robot, direction);
        if (listObj.isEmpty()) {
            model.moveRobot(robot, direction);
        }
    }


    public void cancelTimer() {
        timer.cancel();
    }

    public Robot getRobot() {
        return robot;
    }
}
