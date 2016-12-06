package view;


import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by JuIngong on 05/12/2016.
 */
public class MapView extends JPanel implements Observer {
    /**
     * Id de ObjectHitbox, JPanel
     */
    private HashMap<Integer, JPanel> listPanel;

    /**
     * @param listPanel <Id de ObjectHitbox, JPanel>
     */
    public MapView(HashMap<Integer, JPanel> listPanel) {
        super();
        setLayout(null);
        setBackground(Color.white);
        this.listPanel = listPanel;
        initListPanel();
    }


    @Override
    public void update(Observable o, Object arg) {
        HashMap<String, Object> data = (HashMap<String, Object>) arg;
        if (data.containsKey("move")) {
            moveObject((HashMap<String, Object>) data.get("move"));
        } else if (data.containsKey("hide")) {
            hideObject((int) data.get("hide"));
            repaint();
        } else if (data.containsKey("damage")) {
            damage(((HashMap<String, Object>) data.get("damage")));
            repaint();
        }
    }

    private void moveObject(HashMap<String, Object> data) {
        Point point = (Point) data.get("point");
        int id = (int) data.get("id");

        PanelRobot panel = (PanelRobot) listPanel.get(id);
        panel.setLocation(point);
        panel.hideAtt();

    }

    private void hideObject(int id) {
        listPanel.get(id).setVisible(false);
    }

    private void damage(HashMap<String, Object> data) {
        int life = (int) data.get("life");
        int id = (int) data.get("id");
        int sourceId = (int) data.get("sourceId");

        PanelRobot panel = (PanelRobot)listPanel.get(id);
        PanelRobot panelSource = (PanelRobot)listPanel.get(sourceId);
        panel.setOpacity(255*life/100);
        panelSource.showAtt();
    }

    private void initListPanel() {
        if (listPanel == null || listPanel.isEmpty()) {
            System.err.println("ERROR: initListPanel is null || empty");
            return;
        }

        for (Integer integer : listPanel.keySet()) {
            JPanel panel = listPanel.get(integer);
            add(panel);
        }
    }
}
