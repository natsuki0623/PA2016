package model;

import model.entity.ObjectHitbox;
import model.entity.robot.Robot;
import model.plateau.Map;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by JuIngong on 22/11/2016.
 */
public class Model extends Observable {

    private static Model model = null;
    private Map map;

    //RESET
    private HashMap<ObjectHitbox, Point> listReset;

    // ------------------------------------------- Singleton
    private Model(Map map) {
        this.map = map;
        listReset = new HashMap<>();
    }

    public static Model creerMoteur( Map map){
        model = new Model(map);
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

        send.put("deplacer", description);

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
        send.put("cacher", objC.getId());

        setChanged();
        notifyObservers(send);
    }

    // ------------------------------------------- Methodes


    /**
     * Permet de bouger un robot dans une direction.
     * @param robot
     */
    public void moveRobot(Robot robot, Direction direction){
        robot.move(direction);
        updateMove(robot);
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
        Rectangle hitBox = new Rectangle(hitBoxObj.x, hitBoxObj.y + hitBoxObj.height/2,
                hitBoxObj.width, hitBoxObj.height/2);
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

        objectHitbox.setHitBox(hitBox);
        List<ObjectHitbox> listObj =  getMap().isInContact(objectHitbox);

        objectHitbox.setHitBox(hitBoxObj);

        return listObj;
    }

    /**
     * Permet de reset le jeu
     */
    public void reset(){
        Iterator<ObjectHitbox> i = listReset.keySet().iterator();

        while(i.hasNext()){
            ObjectHitbox obj = i.next();
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
