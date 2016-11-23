package controler;

import model.Direction;
import model.Model;
import model.entity.ObjectHitbox;
import model.entity.robot.Robot;

import java.util.*;

/**
 * Created by JuIngong on 22/11/2016.
 */
public class RobotAction implements Runnable {
    private static final long TIME = 15;

    private Robot robot;

    private HashMap<Direction, Boolean> directionBooleanHashMap;

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
        while (true) {
            Direction direction = Direction.getDirection(robot.getMovement().move(robot.getPosition()));
            if (direction != null && !Direction.NONE.equals(direction)) {
                makeDirectionsCorect(direction);
            }
        }
    }

    private void makeDirectionsCorect(Direction direction) {
        if (direction.equals(Direction.EAST)) {
            directionBooleanHashMap.put(direction, true);
            directionBooleanHashMap.put(Direction.NORTH, false);
            directionBooleanHashMap.put(Direction.SOUTH, false);
            directionBooleanHashMap.put(Direction.WEST, false);
        } else if (direction.equals(Direction.WEST)) {
            directionBooleanHashMap.put(direction, true);
            directionBooleanHashMap.put(Direction.NORTH, false);
            directionBooleanHashMap.put(Direction.SOUTH, false);
            directionBooleanHashMap.put(Direction.EAST, false);
        } else if (direction.equals(Direction.NORTH)) {
            directionBooleanHashMap.put(direction, true);
            directionBooleanHashMap.put(Direction.EAST, false);
            directionBooleanHashMap.put(Direction.SOUTH, false);
            directionBooleanHashMap.put(Direction.WEST, false);
        } else if (direction.equals(Direction.SOUTH)) {
            directionBooleanHashMap.put(direction, true);
            directionBooleanHashMap.put(Direction.NORTH, false);
            directionBooleanHashMap.put(Direction.EAST, false);
            directionBooleanHashMap.put(Direction.WEST, false);
        }
    }


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
