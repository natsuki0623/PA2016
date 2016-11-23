package model.entity;

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

    /**
     * Damage done by hitting another object
     */
    protected int damage;

    protected String type;
    /**
     * Identifiant
     */
    private int id;
    /**
     * Nombre total d'objet
     */
    private static int nbTotalObjet = 0;

    public ObjectHitbox(Rectangle hitBox, int damage, String type) {
        id = nbTotalObjet;
        incrementNbTotal();
        this.type = type;
        this.hitBox = hitBox;
    }

    public ObjectHitbox(Rectangle hitBox, int damage) {
        id = nbTotalObjet;
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
    public static int getNbTotalObjet() {
        return nbTotalObjet;
    }

    public static void setNbTotalObjet(int aNbTotalObjet) {
        nbTotalObjet = aNbTotalObjet;
    }

    /**
     * Déplacement de la position de la hitBox
     * donc de la position du joueur, ennemis ou objet
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
    public void supprimer() {
        nbTotalObjet--;
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
        nbTotalObjet++;
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

        int x = 0, y = 0, largeur = 0, hauteur = 0, damage = 0;
        if (descObj.containsKey("x")) x = (int) descObj.get("x");
        if (descObj.containsKey("y")) y = (int) descObj.get("y");
        if (descObj.containsKey("largeur")) largeur = (int) descObj.get("largeur");
        if (descObj.containsKey("hauteur")) hauteur = (int) descObj.get("hauteur");
        if (descObj.containsKey("damage")) hauteur = (int) descObj.get("damage");

        Rectangle rect = new Rectangle(x, y, largeur, hauteur);

        return new ObjectHitbox(rect, damage);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public enum Type{
        Robot, Damage, ObjectHitbox;
    }

}

