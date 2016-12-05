package controler;

import model.Model;
import model.entity.ObjectHitbox;
import model.entity.robot.Robot;
import model.plateau.Map;
import view.MapView;
import view.PanelRobot;
import view.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

    public Controler() {
        robotActions = new ArrayList<>();
        init();
    }

    public Controler(JFrame lastFrame, HashMap<String, Object> data){
        robotActions = new ArrayList<>();
        this.lastFrame = lastFrame;
        initMap(data);
    }

    private void initMap(HashMap<String, Object> data){

        ArrayList<ObjectHitbox> objectHitboxes = initObjetCollision(data);
        for (ObjectHitbox o : objectHitboxes) {
            if (o.getType().equals(ObjectHitbox.Type.Robot.name())) {
                ((Robot)o).setAttack(new att());
                ((Robot)o).setDrawing(new dra());
                ((Robot)o).setMovement(new deplacem());
            }
        }
        initFrame();
        mapView =  new MapView(toListPanel(objectHitboxes));
        Model.createModel(new Map(objectHitboxes));
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


    private void init() {
        ArrayList<ObjectHitbox> objectHitboxes = new ArrayList<>();


        for (int i = 0; i < 5; i++) {
            Robot robot = new Robot(100, 100, new Rectangle(i * 50, i * 50, 50, 50));
            robot.setAttack(new att());
            robot.setDrawing(new dra());
            robot.setMovement(new deplacem());
            objectHitboxes.add(robot);
        }
        Map map = new Map(objectHitboxes);
        initFrame();
        mapView = new MapView(toListPanel(objectHitboxes));
        Model.createModel(map);
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

    private ArrayList<ObjectHitbox> initObjetCollision(HashMap<String, Object> hm){
        if( hm == null || hm.isEmpty() ){
            return null;
        }

        ArrayList<ObjectHitbox> listObj = new ArrayList<>();
        List<HashMap<String, Object>> map = (List<HashMap<String, Object>>) hm.get("carte");

        for(int i = 0; i < map.size(); i++){
            HashMap<String, Object> descObj = map.get(i);
            ObjectHitbox obj = ObjectHitbox.createObjecHitbox(descObj);
            listObj.add(obj);
        }

        return listObj;
    }


    private HashMap<Integer, JPanel> toListPanel(List<ObjectHitbox> listObj) {
        HashMap<Integer, JPanel> listPanel = new HashMap<>();

        for (ObjectHitbox obj : listObj) {
            if (ObjectHitbox.Type.Robot.name().equals(obj.getType())) {
                listPanel.put(obj.getId(), new PanelRobot(((Robot) obj).getDrawing(), obj.getHitBox()));
            }
            else if(ObjectHitbox.Type.ObjectHitbox.name().equals(obj.getType())) {
                listPanel.put(obj.getId(), new Wall(obj.getHitBox()));
            }
        }
        return listPanel;
    }

    private void initFrame() {
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


        //init
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new Controler();
    }
}
