package controler;

import model.entity.ObjectHitbox;
import model.entity.robot.Robot;
import view.Cell;
import view.CustomMapView;
import view.PanelRobot;
import view.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JuIngong on 05/12/2016.
 */
public class CustomMapControler implements ActionListener {

    private static final int SIZE = 50;

    private JFrame frame;

    private CustomMapView view;
    private JPopupMenu popupMenu;

    private List<ObjectHitbox> objectHitboxes;

    private int cols;
    private int rows;

    private String type;

    private CustomMapListener listener;

    public CustomMapControler() {
        listener = new CustomMapListener(this);
        objectHitboxes = new ArrayList<>();
        initPopup();
        initView();
    }

    private void initPopup() {
        popupMenu = new JPopupMenu();

        JMenuItem wall = new JMenuItem(ObjectHitbox.Type.ObjectHitbox.name());
        popupMenu.add(wall);
        JMenuItem robot = new JMenuItem(ObjectHitbox.Type.Robot.name());
        popupMenu.add(robot);
        JMenuItem eraser = new JMenuItem("Remove");
        popupMenu.add(eraser);

        // ------------------------------------------- listener
        wall.addActionListener(this);
        robot.addActionListener(this);
        eraser.addActionListener(this);
    }

    private void initView() {
        //fenetre
        frame = new JFrame("Création carte");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int) dim.getWidth(), (int) dim.getHeight());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.addKeyListener(listener);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                popupMenu.setVisible(false);
                System.exit(1);
            }
        });

        //CarteVue
        cols = (int) (dim.getWidth() / SIZE);
        rows = (int) (dim.getHeight() / SIZE);
        view = new CustomMapView(rows, cols, SIZE, SIZE);
        view.setMouseListener(listener);

        frame.add(view, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // ------------------------------------------- MOUSE

    public void changePanel(JPanel c) {
        JPanel panel = null;
        Point pos = c.getLocation();

        if (panelExist(pos, type)) return;

        removePanel(pos);

        if (type.equals(ObjectHitbox.Type.ObjectHitbox.name())) {
            Rectangle rect = new Rectangle(pos.x, pos.y, SIZE, SIZE);
            panel = new Wall(rect);
            objectHitboxes.add(new model.entity.ObjectHitbox(rect));
        } else if (type.equals(ObjectHitbox.Type.Robot.name())) {
            Rectangle rect = new Rectangle(pos.x, pos.y, SIZE, SIZE);
            //TODO Selection plugin
            panel = new PanelRobot(new dra(), rect);
            objectHitboxes.add(new Robot(100, 100, rect));
        } else {
            panel = new Cell();
            panel.setBounds(pos.x, pos.y, SIZE, SIZE);
        }

        panel.addMouseListener(listener);
        replacePanel(pos, panel);

        view.repaint();
    }

    private void replacePanel(Point pos, JPanel panel) {
        JPanel oldPanel = view.getPanel(pos);

        view.removePanel(oldPanel);
        view.addPanel(panel);
        view.checkCell(listener);
    }


    private void removePanel(Point p) {
        for (ObjectHitbox obj : objectHitboxes) {
            if (obj.getDefaultPosition().equals(p)) {
                objectHitboxes.remove(obj);
                return;
            }
        }
    }

    private boolean panelExist(Point point, String type) {
        for (ObjectHitbox obj : objectHitboxes) {
            if (obj.getDefaultPosition().equals(point)
                    && obj.getType().equals(type)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        popupMenu.setVisible(false);
        type = e.getActionCommand();
    }

    public JFrame getFrame() {
        return frame;
    }

    public CustomMapView getView() {
        return view;
    }

    public JPopupMenu getPopupMenu() {
        return popupMenu;
    }

    public List<ObjectHitbox> getObjectHitboxes() {
        return objectHitboxes;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public String getType() {
        return type;
    }

    public CustomMapListener getListener() {
        return listener;
    }

    public static void main(String[] args) {
        new CustomMapControler();
    }
}
