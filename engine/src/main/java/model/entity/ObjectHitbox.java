package model.entity;

import IPlugin.IAttack;
import IPlugin.IDrawing;
import IPlugin.IMovement;
import model.entity.robot.*;
import model.entity.robot.Robot;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by JuIngong on 22/11/2016.
 */
public class ObjectHitbox {
    /**
     * La hitBox de l'objet, un getter récupérera la position
     */
    protected Rectangle hitBox;


    protected String type;
    /**
     * Identifiant
     */
    private int id;
    /**
     * Nombre total d'objet
     */
    private static int objectTotalNb = 0;

    public ObjectHitbox(Rectangle hitBox, String type) {
        id = objectTotalNb;
        incrementNbTotal();
        this.type = type;
        this.hitBox = hitBox;
    }

    public ObjectHitbox(Rectangle hitBox) {
        id = objectTotalNb;
        incrementNbTotal();
        this.type = Type.ObjectHitbox.name();
        this.hitBox = hitBox;
    }


    /**
     * Méthode qui permet de savoir si un objet est touché par un
     * rectangle passé en paramètre
     *
     * @param rect l'ennemis, ou le bloc
     * @return true si touché, false sinon
     */
    public boolean isTouch(Rectangle rect) {
        return this.getHitBox().intersects(rect);
    }

    public boolean isTouch(Point point) {
        return this.getHitBox().contains(point);
    }

    /**
     * Méthode qui permet de savoir si un objet est touché par un
     * autre objet passé en paramètre
     *
     * @param o1 l'objet qui contient une hitbox
     * @return true si touché, false sinon
     */
    public boolean isTouch(ObjectHitbox o1) {
        return this.getHitBox().intersects(o1.getHitBox());
    }

    /**
     * Permet de savoir la position de l'objet par rapport à sa hitBox
     *
     * @return un point position
     */
    public Point getPosition() {
        int d1 = (int) (hitBox.getX() + (hitBox.getWidth() / 2));
        int d2 = (int) (hitBox.getY() + (hitBox.getHeight() / 2));
        return new Point(d1, d2);
    }

    /**
     * Permet de récupérer le point en haut à gauche de la hitBox
     *
     * @return
     */
    public Point getDefaultPosition() {
        return new Point(hitBox.x, hitBox.y);
    }


    public int getId() {
        return this.id;
    }

    public void setId(int aId) {
        this.id = aId;
    }

    /**
     * Méthode qui permet de savoir combien d'objetCollision
     * ont été créer.
     * Attention ce n'est pas les tableaux,
     * 4 = 4 objet créer
     *
     * @return
     */
    public static int getObjectTotalNb() {
        return objectTotalNb;
    }

    public static void setObjectTotalNb(int nbObjectTotal) {
        objectTotalNb = nbObjectTotal;
    }

    /**
     * Déplacement de la position de la hitBox
     *
     * @param op
     */
    public void setPosition(Point op) {
        Point p1 = new Point((int) hitBox.getX(), (int) hitBox.getY());

        int diffX;
        int diffY;

        if (op.getX() >= p1.getX()) {
            diffX = (int) ((int) (op.getX() - p1.getX()) - (hitBox.getWidth() / 2));
            hitBox.setRect(p1.getX() + diffX, p1.getY(), hitBox.getWidth(), hitBox.getHeight());
            p1.setLocation(p1.getX() + diffX, p1.getY());
        } else {
            diffX = (int) ((int) (p1.getX() - op.getX()) + (hitBox.getWidth() / 2));
            hitBox.setRect(p1.getX() - diffX, p1.getY(), hitBox.getWidth(), hitBox.getHeight());
            p1.setLocation(p1.getX() - diffX, p1.getY());
        }

        if (op.getY() >= p1.getY()) {
            diffY = (int) ((int) (op.getY() - p1.getY()) - (hitBox.getHeight() / 2));
            hitBox.setRect(p1.getX(), p1.getY() + diffY, hitBox.getWidth(), hitBox.getHeight());

        } else {
            diffY = (int) ((int) (p1.getY() - op.getY()) + (hitBox.getWidth() / 2));
            hitBox.setRect(p1.getX(), p1.getY() - diffY, hitBox.getWidth(), hitBox.getHeight());
        }
    }

    /**
     * Permet d'initialiser une hitBox
     *
     * @param rect
     */
    public void setHitBox(Rectangle rect) {
        this.hitBox = rect;
    }

    /**
     * Obligatoire avant de mettre à supprimer toutes les références
     * vers cette objet
     */
    public void remove() {
        objectTotalNb--;
        hitBox = null;
        id = 0;
    }

    public Rectangle getHitBox() {
        return this.hitBox;
    }

    /**
     * Augmente le nombre maximal d'objet
     */
    private void incrementNbTotal() {
        objectTotalNb++;
    }


    /**
     * Permet de créer un objet avec un hashmap de description.
     * <p>
     * le Hashmap: -> "x" -> int
     * "y" -> int
     * "largeur" -> int
     * "hauteur" -> int
     * "damage" -> int
     *
     * @param descObj
     * @return ObjectHitbox
     */
    public static ObjectHitbox createObjecHitbox(HashMap<String, java.lang.Object> descObj) {
        if (descObj == null || descObj.isEmpty()) {
            return null;
        }

        String type = (String) descObj.get("type");
        int x = 0, y = 0, largeur = 0, hauteur = 0, damage = 0;
        if (descObj.containsKey("x")) x = (int) descObj.get("x");
        if (descObj.containsKey("y")) y = (int) descObj.get("y");
        if (descObj.containsKey("largeur")) largeur = (int) descObj.get("largeur");
        if (descObj.containsKey("hauteur")) hauteur = (int) descObj.get("hauteur");

        Rectangle rect = new Rectangle(x, y, largeur, hauteur);

        return new ObjectHitbox(rect);
    }

    public static ObjectHitbox createObjecHitbox(HashMap<String, java.lang.Object> descObj, IAttack attack, IDrawing drawing, IMovement movement) {
        if (descObj == null || descObj.isEmpty()) {
            return null;
        }

        int x = 0, y = 0, largeur = 0, hauteur = 0, vie = 0, energy = 0;
        if (descObj.containsKey("x")) x = (int) descObj.get("x");
        if (descObj.containsKey("y")) y = (int) descObj.get("y");
        if (descObj.containsKey("largeur")) largeur = (int) descObj.get("largeur");
        if (descObj.containsKey("hauteur")) hauteur = (int) descObj.get("hauteur");

        if (descObj.containsKey("vie")) vie = (int) descObj.get("vie");
        if (descObj.containsKey("energie")) energy = (int) descObj.get("energie");

        Rectangle rect = new Rectangle(x, y, largeur, hauteur);
        return new Robot(vie, energy, rect, drawing, attack, movement);

    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public enum Type {
        Robot, ObjectHitbox;
    }

}

