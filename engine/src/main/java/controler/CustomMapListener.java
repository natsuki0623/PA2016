package controler;

import model.entity.ObjectHitbox;
import model.entity.robot.*;
import view.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Created by JuIngong on 05/12/2016.
 */
public class CustomMapListener implements KeyListener, MouseListener {

    private CustomMapControler controler;
    private boolean click;

    public CustomMapListener(CustomMapControler controler) {
        this.controler = controler;
    }

    @Override
    public void keyTyped(KeyEvent e) throws UnsupportedOperationException {

    }

    @Override
    public void keyPressed(KeyEvent e) throws UnsupportedOperationException {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            startGame();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            controler.getFrame().dispose();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JPopupMenu popupMenu = controler.getPopupMenu();
        if (popupMenu.isShowing()) {
            popupMenu.setVisible(false);
            return;
        }

        if (e.getButton() == MouseEvent.BUTTON3) {//clic droit
            popupMenu.setLocation(e.getLocationOnScreen().x, e.getLocationOnScreen().y);
            popupMenu.setVisible(true);
        } else if (e.getButton() == MouseEvent.BUTTON1 && controler.getType() != null) {//clic gauche
            controler.changePanel((JPanel) e.getSource());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            click = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            click = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof Cell) {
            ((Cell) e.getSource()).setColorBorder(Color.GREEN);
        }

        if (click && controler.getType() != null) {
            controler.changePanel((JPanel) e.getSource());
        }
        e.consume();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof Cell) {
            ((Cell) e.getSource()).setColorBorder(Color.BLACK);
        }

        e.consume();
    }

    private void startGame() {
        HashMap<String, Object> data = new HashMap<>();
        java.util.List<HashMap<String, Object>> map = new ArrayList<HashMap<String, Object>>();
        data.put("hauteur", controler.getRows());
        data.put("largeur", controler.getCols());
        data.put("carte", map);

        for (ObjectHitbox obj : controler.getObjectHitboxes()) {
            HashMap<String, Object> desc = new HashMap<>();
            map.add(desc);

            Rectangle rect = obj.getHitBox();
            desc.put("largeur", rect.width);
            desc.put("hauteur", rect.height);
            desc.put("x", rect.x);
            desc.put("y", rect.y);
            desc.put("type", obj.getType());
            if (obj.getType().equals(ObjectHitbox.Type.Robot.name())) {
                desc.put("attack", ((model.entity.robot.Robot) obj).getAttack());
                desc.put("draw", ((model.entity.robot.Robot) obj).getDrawing());
                desc.put("move", ((model.entity.robot.Robot) obj).getMovement());
            }
        }

        new Controler(controler.getFrame(), data);

        controler.getFrame().setVisible(false);
    }

}
