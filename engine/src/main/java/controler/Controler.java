package controler;

import model.Model;
import model.entity.ObjectHitbox;
import model.entity.robot.Robot;
import model.plateau.Map;
import view.MapView;
import view.PanelRobot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JuIngong on 22/11/2016.
 */
public class Controler {

    private List<Thread> robotActions;

    private JFrame frame;
    private MapView mapView;

    public Controler() {

        robotActions = new ArrayList<>();
        init();
    }


    private void init(){
        ArrayList<ObjectHitbox> objectHitboxes = new ArrayList<>();

        for(int i = 0; i<4; i++) {
            Robot robot = new Robot(100, 100, new Rectangle(i*50, i*50, 40, 40));
            robot.setAttack(new att());
            robot.setDrawing(new dra());
            robot.setMovement(new deplacem());
            robotActions.add(new Thread(new RobotAction(robot)));
            objectHitboxes.add(robot);
        }
        Map map = new Map(objectHitboxes);
        initFrame();
        mapView = new MapView(toListPanel(objectHitboxes));
        Model.createModel(map);
        Model model = Model.getModel();
        frame.setContentPane(mapView);
        model.addObserver(mapView);
        frame.validate();
        for (Thread t : robotActions ) {
            t.start();
        }
    }


    private HashMap<Integer, JPanel> toListPanel(List<ObjectHitbox> listObj){
        HashMap<Integer, JPanel> listPanel = new HashMap<>();

        for( ObjectHitbox obj : listObj){
            if( ObjectHitbox.Type.Robot.name().equals(obj.getType()) ){
                listPanel.put(obj.getId(), new PanelRobot(((Robot)obj).getDrawing(), obj.getHitBox()));
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
                for (Thread t : robotActions ) {
                    t.interrupt();
                }
                System.exit(1);
            }
        });

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int)(dim.getWidth()), (int)(dim.getHeight()));
        frame.setLocationRelativeTo(null);

        //init
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new Controler();
    }
}
