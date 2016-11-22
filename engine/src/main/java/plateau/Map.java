package plateau;

import entity.ObjectHitbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JuIngong on 22/11/2016.
 */
public class Map {

    private List<ObjectHitbox> objectHitboxes;

    public Map(List<ObjectHitbox> objectHitboxes) {
        this.objectHitboxes = objectHitboxes;
    }

    /**
     * Permet de savoir si "objA" est en collision
     *  avec un des objet de la map.
     *
     * @param objA celle qu'on veux tester parmis les autres objets
     * @return true si il est en collision
     */
    public List<ObjectHitbox> isInContact(ObjectHitbox objA){
        List<ObjectHitbox> listObj = new ArrayList<>();
        for(int i = 0; i < objectHitboxes.size(); i++){
            ObjectHitbox objB = objectHitboxes.get(i);
            if( objA != objB  && objB.isTouch(objA.getHitBox())){
                listObj.add(objB);
            }
        }
        return listObj;
    }


    /**
     * Permet de supprimer un objet de la liste.
     * @param obj
     */
    public void removeObj(ObjectHitbox obj){
        objectHitboxes.remove(obj);
    }

    /**
     * Permet de rajouter un objet dans la liste .
     * @param obj
     */
    public void addObj(ObjectHitbox obj){
        objectHitboxes.add(obj);
    }

    /**
     * Permet de savoir si la carte contient l'element donnée en paramétre.
     * @param obj
     * @return true s il contient l'element
     */
    public boolean containObjCollision(ObjectHitbox obj){
        return objectHitboxes.contains(obj);
    }
    /**
     * Permet de récupérer les objets
     * sur la carte courante.
     * @return
     */
    public List<ObjectHitbox> getObjets(){
        return objectHitboxes;
    }


}
