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
    private static final long TIME = 10;

    private Robot robot;

    private HashMap<Direction, Boolean> directionBooleanHashMap;

    private Point attackPoint;

    private Timer timer;
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Model model = Model.getModel();
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
            for (Direction d : directionBooleanHashMap.keySet()) {
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
        System.out.println(robot.getId() + " " + robot.getPosition());
        List<ObjectHitbox> listObj = model.listObjHitbox(robot, direction);
        System.out.println(robot.getId() + " " + robot.getPosition());
        if (listObj.isEmpty()) {
            model.moveRobot(robot, direction);
            System.out.println("Deplacement " + robot.getId() + "  " + robot.getPosition());
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

    public Robot getRobot() {
        return robot;
    }
}
