package model;

import controler.RobotAction;
import model.entity.ObjectHitbox;
import model.entity.robot.Robot;
import model.plateau.Map;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JuIngong on 22/11/2016.
 */
public class Model extends Observable {

    private static Model model = null;
    private Map map;

    private List<RobotAction> robotActions;

    //RESET
    private HashMap<ObjectHitbox, Point> listReset;

    // ------------------------------------------- Singleton
    private Model(Map map, List<RobotAction> robotAction) {
        this.map = map;
        this.robotActions = robotAction;
        listReset = new HashMap<>();
    }

    public static Model createModel( Map map, List<RobotAction> robotAction){
        model = new Model(map,robotAction);
        return model;
    }

    public static Model getModel(){
        return model;
    }


    // ------------------------------------------- Update
    /**
     * Permet de mettre a jour la vue.
     *
     * HashMap : "point"->Point
     *                 "id"->int
     */
    public void updateMove(ObjectHitbox objC) {
        HashMap<String, HashMap<String, Object>> send = new HashMap<>();
        HashMap<String, Object> description = new HashMap<>();

        description.put("point", objC.getDefaultPosition());
        description.put("id",objC.getId());

        send.put("move", description);

        setChanged();
        notifyObservers(send);
    }

    /**
     * Permet de mettre a jour la vue pour les degat.
     *
     * HashMap : "point"->Point
     *                 "id"->int
     */
    public void updateDoDamage(ObjectHitbox objC){
        HashMap<String, Integer> send = new HashMap<>();
        send.put("damage", objC.getId());

        setChanged();
        notifyObservers(send);
    }

    /**
     * Permet de cacher un objet sur la vue
     *
     * HashMap :   "id"->int
     */
    private void updateHide(ObjectHitbox objC) {
        HashMap<String, Integer> send = new HashMap<>();
        send.put("hide", objC.getId());

        setChanged();
        notifyObservers(send);
    }

    // ------------------------------------------- Methodes


    public void doDamage(Robot robot, Point point){
        for(Robot r : getMap().getEnemy(robot)){
            System.out.println(r.toString());
          if(r.isTouch(point)){
              System.out.println(r.getId() + " " + r.getLife());
              r.setLife(r.getLife()-robot.getAttack().attaque());
              if(r.getLife() <= 0) {
                  updateHide(r);
                  for (RobotAction ra: robotActions) {
                      if(ra.getRobot().getId() == r.getId()){
                          ra.cancelTimer();
                      }
                  }
                  map.getObjets().remove(r);
                  //r.remove();
              }
              //updateDoDamage(r);
          }
        }

    }

    /**
     * Permet de bouger un robot dans une direction.
     * @param robot
     */
    public void moveRobot(Robot robot, Direction direction){
        robot.move(direction);
        updateMove(robot);
    }

    /**
     * Permet de recupérer les position des enemi
     * a un robot present sur la carte
     *
     * @param self Robot demandant
     * @return List des position des robot sauf celui demandant
     */
    public List<Point> listEnemy(Robot self){
        return map.getEnemy(self).stream()
                .map(enemy -> enemy.getPosition())
                .collect(Collectors.toList());
    }

    /**
     * Retourne une liste des objets qui seront en collision avec l'objet si
     * l'objet se déplace dans la direction donnée en parametre.
     * @param objectHitbox
     * @param direction
     * @return
     */
    public List<ObjectHitbox> listObjHitbox(ObjectHitbox objectHitbox, Direction direction){
        if( direction == null || Direction.NONE.equals(direction)){
            return null;
        }

        int vit = Robot.SPEED;
        Rectangle hitBoxObj =  objectHitbox.getHitBox();
        Rectangle hitBox = new Rectangle(hitBoxObj.x, hitBoxObj.y,
                hitBoxObj.width, hitBoxObj.height);
        if(Direction.NORTH.equals(direction)){
            hitBox.setLocation(hitBox.x, hitBox.y - vit);
        }
        else if(Direction.SOUTH.equals(direction)){
            hitBox.setLocation(hitBox.x, hitBox.y + vit );
        }
        else if(Direction.EAST.equals(direction)){
            hitBox.setLocation(hitBox.x + vit, hitBox.y);
        }
        else if(Direction.WEST.equals(direction)){
            hitBox.setLocation(hitBox.x - vit, hitBox.y);
        }

        return getMap().isInContact(hitBox, objectHitbox);
    }

    /**
     * Permet de reset le jeu
     */
    public void reset(){

        for (ObjectHitbox obj : listReset.keySet()) {
            resetObjetCollision(obj);
        }

    }

    /**
     * Permet de remettre l'objet à son état initiale.
     * @param obj
     */
    private void resetObjetCollision(ObjectHitbox obj){
        Point point = listReset.get(obj);
        obj.setPosition(point);
        updateMove(obj);
    }


    /**
     * Permet de supprimer un objet sur la care courante.
     * @param obj
     */
    public void removeObjet(ObjectHitbox obj){
        map.removeObj(obj);
        updateHide(obj);
    }

    // ------------------------------------------- GETTER


    public Map getMap() {
        return map;
    }

}
