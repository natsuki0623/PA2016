package entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by JuIngong on 23/11/2016.
 */
public class ObjectHitboxTest {
    ObjectHitbox o1;
    ObjectHitbox o2;
    ObjectHitbox o3;
    Rectangle r1;
    Rectangle r2;
    Rectangle r3;

    @Before
    public void init(){
        r1 = new Rectangle(new Point(10,10),new Dimension(10,10));
        r2 = new Rectangle(new Point(30,20),new Dimension(10,10));
        r3 = new Rectangle(new Point(15,15),new Dimension(20,10));

        o1 = new ObjectHitbox(r1, 0);
        o2 = new ObjectHitbox(r2, 0);
        o3 = new ObjectHitbox(r3, 0);
    }

    @Test
    public void isTouche(){
        assertTrue(o1.isTouch(o3.getHitBox()));
        assertTrue(o1.isTouch(o3));
        assertTrue(!o1.isTouch(o2.getHitBox()));
        assertTrue(!o1.isTouch(o2));
        assertTrue(o2.isTouch(o3.getHitBox()));
        assertTrue(o2.isTouch(o3));

        assertTrue(o3.isTouch(o1.getHitBox()));
        assertTrue(o3.isTouch(o1));
        assertTrue(!o2.isTouch(o1.getHitBox()));
        assertTrue(!o2.isTouch(o1));
        assertTrue(o3.isTouch(o2.getHitBox()));
        assertTrue(o3.isTouch(o2));
    }

    @Test
    public void getPosition(){
        assertEquals(new Point(15,15),o1.getPosition());
        assertEquals(new Point(35,25),o2.getPosition());
        assertEquals(new Point(25,20),o3.getPosition());
    }

    @Test
    public void getNbTotal(){
        assertEquals(3,o1.getNbTotalObjet());
        ObjectHitbox o1 = new ObjectHitbox(r1,0);
        assertEquals(4,o1.getNbTotalObjet());
        ObjectHitbox o2 = new ObjectHitbox(r2,0);
        assertEquals(5,o1.getNbTotalObjet());
        ObjectHitbox o3 = new ObjectHitbox(r3,0);
        assertEquals(6,o1.getNbTotalObjet());
        o1.supprimer();
        assertEquals(5,o1.getNbTotalObjet());
        o2.supprimer();
        assertEquals(4,o1.getNbTotalObjet());
        o3.supprimer();
        assertEquals(3,o1.getNbTotalObjet());
    }

    @Test
    public void setPosition(){
        o1.setPosition(new Point(35,15));
        assertEquals(new Point(35,15),o1.getPosition());
        o1.setPosition(new Point(10,10));
        assertEquals(new Point(10,10),o1.getPosition());
        assertEquals(new Point(35,25),o2.getPosition());
        assertEquals(new Point(25,20),o3.getPosition());
    }


    @After
    public void delete(){
        o1.supprimer();
        o2.supprimer();
        o3.supprimer();
    }

}