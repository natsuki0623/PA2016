package controler;

import IPlugin.IAttack;
import IPlugin.IDrawing;
import IPlugin.IMovement;
import loader.PluginsLoader;
import model.Model;
import model.entity.ObjectHitbox;
import model.entity.robot.Robot;
import model.plateau.Map;
import view.MapView;
import view.PanelRobot;
import view.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JuIngong on 22/11/2016.
 */
public class Controler {

    private List<RobotAction> robotActions;

    private JFrame frame;
    private MapView mapView;

    private JFrame lastFrame;

    private boolean launch = false;

    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenuItem loadSave;
    private JMenuItem saveSave;


    public Controler(JFrame lastFrame, HashMap<String, Object> data) {
        robotActions = new ArrayList<>();
        this.lastFrame = lastFrame;
        initMap(data);
    }

    private void initMap(HashMap<String, Object> data) {

        ArrayList<ObjectHitbox> objectHitboxes = initObjetCollision(data);

        if (!launch) {
            initFrame(data);
        }
        mapView = new MapView(toListPanel(objectHitboxes));
        Model.createModel(new Map(objectHitboxes), robotActions, frame.getSize());
        for (ObjectHitbox o : objectHitboxes) {
            if (o.getType().equals(ObjectHitbox.Type.Robot.name())) {
                robotActions.add(new RobotAction((Robot) o));
            }
        }
        Model model = Model.getModel();
        frame.setContentPane(mapView);
        model.addObserver(mapView);
        frame.validate();
    }

    private ArrayList<ObjectHitbox> initObjetCollision(HashMap<String, Object> hm) {
        if (hm == null || hm.isEmpty()) {
            return null;
        }

        ArrayList<ObjectHitbox> listObj = new ArrayList<>();
        List<HashMap<String, Object>> map = (List<HashMap<String, Object>>) hm.get("carte");

        for (int i = 0; i < map.size(); i++) {
            HashMap<String, Object> descObj = map.get(i);
            File file = null;
            if (descObj.containsKey("path")) {
                file = (File) descObj.get("path");
            }
            String move = null, attack = null, drawing = null;

            if (descObj.containsKey("move")) {
                move = (String) descObj.get("move");
            }
            if (descObj.containsKey("attack")) {
                attack = (String) descObj.get("attack");
            }
            if (descObj.containsKey("draw")) {
                drawing = (String) descObj.get("draw");
            }
            PluginsLoader pl = new PluginsLoader();
            IAttack attack1 = null;
            IDrawing drawing1 = null;
            IMovement movement = null;
            try {
                pl.init(file);
                for (Class<?> p : pl.getPlugins()) {
                    if (p.getInterfaces()[0].getName().equals("IPlugin.IAttack") && p.getName().equals(attack)) {
                        attack1 = (IAttack) p.newInstance();
                    } else if (p.getInterfaces()[0].getName().equals("IPlugin.IDrawing") && p.getName().equals(drawing)) {
                        drawing1 = (IDrawing) p.newInstance();
                    } else if (p.getInterfaces()[0].getName().equals("IPlugin.IMovement") && p.getName().equals(move)) {
                        movement = (IMovement) p.newInstance();
                    }
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }


            ObjectHitbox obj = ObjectHitbox.createObjecHitbox(descObj, attack1, drawing1, movement);
            listObj.add(obj);
        }

        return listObj;
    }


    private HashMap<Integer, JPanel> toListPanel(List<ObjectHitbox> listObj) {
        HashMap<Integer, JPanel> listPanel = new HashMap<>();

        for (ObjectHitbox obj : listObj) {
            if (ObjectHitbox.Type.Robot.name().equals(obj.getType())) {
                PanelRobot panelRobot = new PanelRobot(((Robot) obj).getDrawing(), obj.getHitBox());
                panelRobot.setAttack(((Robot) obj).getAttack());
                listPanel.put(obj.getId(), panelRobot);

            } else if (ObjectHitbox.Type.ObjectHitbox.name().equals(obj.getType())) {
                listPanel.put(obj.getId(), new Wall(obj.getHitBox()));
            }
        }
        return listPanel;
    }

    private void initFrame(HashMap<String, Object> data) {
        //Frame
        frame = new JFrame("RobotWar");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                lastFrame.setVisible(true);
            }
        });

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int) (dim.getWidth()), (int) (dim.getHeight()));
        frame.setLocationRelativeTo(null);

        this.menuBar = new JMenuBar();
        this.gameMenu = new JMenu();

        this.menuBar.add(gameMenu);

        this.loadSave = new JMenuItem();
        //Le chargement
        this.saveSave = new JMenuItem();

        //On ajoute les items au menu
        this.gameMenu.setText("Partie");
        this.gameMenu.add(this.saveSave);
        this.gameMenu.add(this.loadSave);

        this.loadSave.setText("Charger sauvegarde");
        this.saveSave.setText("Sauvegarder partie");
        frame.setJMenuBar(this.menuBar);

        //TODO Don't work

        this.saveSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileOutputStream fos =
                            new FileOutputStream("hashmap.ser");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(data);
                    oos.close();
                    fos.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });

        this.loadSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launch = true;
                HashMap<String, Object> dataB;
                try {
                    FileInputStream fis = new FileInputStream("hashmap.ser");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    dataB = (HashMap) ois.readObject();
                    ois.close();
                    fis.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    return;
                } catch (ClassNotFoundException c) {
                    c.printStackTrace();
                    return;
                }
                // Display content using Iterator
                robotActions = new ArrayList<>();
                initMap(dataB);
            }

        });


        //init
        frame.setVisible(true);

    }

}
