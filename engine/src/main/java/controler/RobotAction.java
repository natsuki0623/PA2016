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
public class RobotAction implements Runnable {
    private static final long TIME = 15;

    private Robot robot;

    private HashMap<Direction, Boolean> directionBooleanHashMap;

    private Point attackPoint;

    private Timer timer;
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Iterator<Direction> i = directionBooleanHashMap.keySet().iterator();
            while (i.hasNext()) {
                Direction d = i.next();
                if (directionBooleanHashMap.get(d)) {
                    moveRobot(d);
                }
            }
            if (attackPoint != null){
                attackRobot(attackPoint);
            }

        }
    };

    public RobotAction(Robot robot) {
        this.robot = robot;
        //init directions
        directionBooleanHashMap = new HashMap<>();
        for (Direction dir : Direction.values()) {
            directionBooleanHashMap.put(dir, false);
        }

        timer = new Timer();
        timer.schedule(timerTask, 0, TIME);
    }

    @Override
    public void run() {
        Model model = Model.getModel();
        while (true) {
            Direction direction = Direction.getDirection(robot.getMovement().move(robot.getPosition(), model.listEnemy(robot)));
            resetDirection();
            if (direction != null && !Direction.NONE.equals(direction)) {
                directionBooleanHashMap.put(direction, true);
            }
            Point dam = robot.getAttack().location(robot.getPosition(), model.listEnemy(robot));
            if (dam != null) {
                int range = new Double(Math.sqrt(Math.pow(robot.getPosition().getX() - dam.getX(), 2) + Math.pow(robot.getPosition().getY() - dam.getY(), 2))).intValue();
                if (range <= robot.getAttack().range()) {
                    attackPoint = dam;
                }
            } else {
                attackPoint = null;
            }
        }
    }

    /**
     * Reinitialise direction HashMap
     */
    private void resetDirection() {
        directionBooleanHashMap.put(Direction.NORTH, false);
        directionBooleanHashMap.put(Direction.SOUTH, false);
        directionBooleanHashMap.put(Direction.WEST, false);
        directionBooleanHashMap.put(Direction.EAST, false);
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
     *
     * @param direction
     */
    private void moveRobot(Direction direction) {
        Model model = Model.getModel();
        List<ObjectHitbox> listObj = model.listObjHitbox(robot, direction);
        if (listObj.isEmpty()) {
            model.moveRobot(robot, direction);
        } else {
            for (ObjectHitbox objC : listObj) {
                robotInContact(objC);
            }
        }
    }

    private void robotInContact(ObjectHitbox objC) {
        Model model = Model.getModel();

        if (objC.getType().equals(ObjectHitbox.Type.Damage.name())) {
            robot.setLife(robot.getLife() - objC.getDamage());
            model.removeObjet(objC);
        }
    }

    public void cancelTimer() {
        timer.cancel();
    }

}
