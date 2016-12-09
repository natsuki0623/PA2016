package controler;

import IPlugin.*;
import model.entity.ObjectHitbox;
import model.entity.robot.Robot;
import view.Cell;
import view.CustomMapView;
import view.PanelRobot;
import view.Wall;

import javax.swing.*;

import loader.PluginsLoader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JuIngong on 05/12/2016.
 */
public class CustomMapControler implements ActionListener {

    private static final int SIZE = 50;

    private JFrame frame;
    private JFrame framePlugin;

    private CustomMapView view;
    private JPopupMenu popupMenu;

    private List<ObjectHitbox> objectHitboxes;
    private List<IAttack> iAttacks;
    private List<IDrawing> iDrawings;
    private List<IMovement> iMovements;

    private IDrawing drawing;
    private IAttack attack;
    private IMovement movement;

    private int cols;
    private int rows;

    private String type;

    private File filePlugin;

    private CustomMapListener listener;

    public CustomMapControler() throws ClassNotFoundException {
        iAttacks = new ArrayList<>();
        iMovements = new ArrayList<>();
        iDrawings = new ArrayList<>();
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

    private void initView() throws ClassNotFoundException {
        //fenetre
        frame = new JFrame("Création carte");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int) dim.getWidth(), (int) dim.getHeight());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.addKeyListener(listener);


        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new java.io.File("."));
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.showOpenDialog(null);
        filePlugin = fc.getSelectedFile();
        PluginsLoader pl = new PluginsLoader();
        pl.init(filePlugin);
        for (Class<?> p : pl.getPlugins()) {
            try {
                if (p.getInterfaces()[0].getName().equals("IPlugin.IAttack")) {
                    iAttacks.add((IAttack) p.newInstance());
                } else if (p.getInterfaces()[0].getName().equals("IPlugin.IDrawing")) {
                    iDrawings.add((IDrawing) p.newInstance());
                } else if (p.getInterfaces()[0].getName().equals("IPlugin.IMovement")) {
                    iMovements.add((IMovement) p.newInstance());
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        framePlugin = new JFrame("Selection des Plugins");
        framePlugin.setSize(500, 1000);
        JPanel panelPlugin = new JPanel();
        panelPlugin.setLayout(new BoxLayout(panelPlugin, BoxLayout.Y_AXIS));
        ArrayList<JCheckBox> array1 = new ArrayList<>();
        for (int i = 0; i < iAttacks.size(); i++) {
            JCheckBox checkb = new JCheckBox();
            checkb.setText(iAttacks.get(i).getClass().getName());
            array1.add(checkb);
            panelPlugin.add(array1.get(i));
        }
        ArrayList<JCheckBox> array2 = new ArrayList<>();
        for (int i = 0; i < iDrawings.size(); i++) {
            JCheckBox checkb = new JCheckBox();
            checkb.setText(iDrawings.get(i).getClass().getName());
            array2.add(checkb);
            panelPlugin.add(array2.get(i));
        }
        ArrayList<JCheckBox> array3 = new ArrayList<>();
        for (int i = 0; i < iMovements.size(); i++) {
            JCheckBox checkb = new JCheckBox();
            checkb.setText(iMovements.get(i).getClass().getName());
            array3.add(checkb);
            panelPlugin.add(array3.get(i));
        }
        JButton bPlugin = new JButton("Valider");
        panelPlugin.add(bPlugin);
        framePlugin.add(panelPlugin);

        bPlugin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (JCheckBox jCheckBox : array1) {
                    if (jCheckBox.isSelected()) {
                        for (IAttack a : iAttacks) {
                            if (a.getClass().getName().equals(jCheckBox.getText())) {
                                attack = a;
                            }
                        }
                    }
                }
                for (JCheckBox jCheckBox : array2) {
                    if (jCheckBox.isSelected()) {
                        for (IDrawing a : iDrawings) {
                            if (a.getClass().getName().equals(jCheckBox.getText())) {
                                drawing = a;
                            }
                        }
                    }
                }
                for (JCheckBox jCheckBox : array3) {
                    if (jCheckBox.isSelected()) {
                        for (IMovement a : iMovements) {
                            if (a.getClass().getName().equals(jCheckBox.getText())) {
                                movement = a;
                            }
                        }
                    }
                }
                if (movement != null && drawing != null && attack != null) {
                    framePlugin.setVisible(false);
                }
            }
        });


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
        framePlugin.setVisible(true);
    }

    // ------------------------------------------- MOUSE

    public void changePanel(JPanel c) {
        JPanel panel = null;
        Point pos = c.getLocation();

        if (panelExist(pos, type)) {
            return;
        }

        removePanel(pos);

        if (type.equals(ObjectHitbox.Type.ObjectHitbox.name())) {
            Rectangle rect = new Rectangle(pos.x, pos.y, SIZE, SIZE);
            panel = new Wall(rect);
            objectHitboxes.add(new model.entity.ObjectHitbox(rect));
        } else if (type.equals(ObjectHitbox.Type.Robot.name())) {
            Rectangle rect = new Rectangle(pos.x, pos.y, SIZE, SIZE);
            panel = new PanelRobot(drawing, rect);
            objectHitboxes.add(new Robot(100, 100, rect, drawing, attack, movement));
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
            if (obj.getDefaultPosition().equals(point) && obj.getType().equals(type)) {
                return true;
            }
        }

        return false;
    }

    public File getFilePlugin() {
        return filePlugin;
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

    public JFrame getFramePlugin() {
        return framePlugin;
    }

    public CustomMapListener getListener() {
        return listener;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        new CustomMapControler();
    }
}
