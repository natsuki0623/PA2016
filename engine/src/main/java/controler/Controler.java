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

    private JFrame lastFrame;

    private boolean launch = false;


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
        MapView mapView = new MapView(toListPanel(objectHitboxes));
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
        ObjectHitbox obj;

        for (int i = 0; i < map.size(); i++) {
            HashMap<String, Object> descObj = map.get(i);
            if (((String) descObj.get("type")).equals(ObjectHitbox.Type.Robot.name())) {
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


                obj = ObjectHitbox.createObjecHitbox(descObj, attack1, drawing1, movement);
            } else {
                obj = ObjectHitbox.createObjecHitbox(descObj);
            }
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

    public HashMap<String, Object> buildHashmap(HashMap<String, Object> old) {
        HashMap<String, Object> data = new HashMap<>();
        java.util.List<HashMap<String, Object>> map = new ArrayList<HashMap<String, Object>>();
        if (old.containsKey("hauteur")) {
            data.put("hauteur", old.get("hauteur"));
        }
        if (old.containsKey("largeur")) {
            data.put("largeur", old.get("largeur"));
        }
        data.put("carte", map);

        for (ObjectHitbox obj : Model.getModel().getMap().getObjets()) {
            HashMap<String, Object> desc = new HashMap<>();
            map.add(desc);

            Rectangle rect = obj.getHitBox();
            desc.put("largeur", rect.width);
            desc.put("hauteur", rect.height);
            desc.put("x", rect.x);
            desc.put("y", rect.y);
            desc.put("type", obj.getType());
            if (obj.getType().equals(ObjectHitbox.Type.Robot.name())) {
                List<HashMap<String, Object>> map2 = (List<HashMap<String, Object>>) old.get("carte");

                for (int i = 0; i < map2.size(); i++) {
                    HashMap<String, Object> descObj = map2.get(i);
                    if (descObj.containsKey("path")) {
                        File file = (File) descObj.get("path");
                        desc.put("path", file);
                    }
                }
                desc.put("attack", ((model.entity.robot.Robot) obj).getAttack().getClass().getName());
                desc.put("draw", ((model.entity.robot.Robot) obj).getDrawing().getClass().getName());
                desc.put("move", ((model.entity.robot.Robot) obj).getMovement().getClass().getName());
                desc.put("vie", ((model.entity.robot.Robot) obj).getLife());
                desc.put("energie", ((model.entity.robot.Robot) obj).getEnergy());
            }
        }
        return data;
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

        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu();

        menuBar.add(gameMenu);

        JMenuItem loadSave = new JMenuItem();
        //Le chargement
        JMenuItem saveSave = new JMenuItem();

        //On ajoute les items au menu
        gameMenu.setText("Partie");
        gameMenu.add(saveSave);
        gameMenu.add(loadSave);

        loadSave.setText("Charger sauvegarde");
        saveSave.setText("Sauvegarder partie");
        frame.setJMenuBar(menuBar);

        //TODO Don't work

        saveSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileOutputStream fos = new FileOutputStream("robot.ser");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(buildHashmap(data));
                    oos.close();
                    fos.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });

        loadSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launch = true;
                HashMap<String, Object> dataB;
                try {
                    FileInputStream fis = new FileInputStream("robot.ser");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    dataB = (HashMap) ois.readObject();
                    ois.close();
                    fis.close();
                } catch (IOException | ClassNotFoundException ioe) {
                    ioe.printStackTrace();
                    return;
                }
                if (dataB != null) {
                    robotActions = new ArrayList<>();
                    initMap(dataB);
                }
            }

        });


        //init
        frame.setVisible(true);

    }

}
