package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by JuIngong on 05/12/2016.
 */
public class CustomMapView extends JPanel {

    private Map<Point, JPanel> jPanelMap;

    private int row;
    private int col;
    private int width;
    private int height;

    public CustomMapView(int row, int col, int width, int height) {
        jPanelMap = new HashMap<>();
        this.row = row;
        this.col = col;
        this.width = width;
        this.height = height;
        setLayout(new GridLayout(row, col));
        setBackground(Color.white);
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < col; x++) {
                Cell c = new Cell();
                c.setBounds(x * width, y * height, width, height);
                add(c);
                jPanelMap.put(new Point(x * width, y * height), c);
            }
        }

        setLayout(null);
    }

    public void setMouseListener(MouseListener mouseListener) {
        for (Point point : jPanelMap.keySet()) {
            jPanelMap.get(point).addMouseListener(mouseListener);
        }
    }

    public void addPanel(JPanel panel) {
        Point p = panel.getLocation();
        jPanelMap.put(new Point(p.x, p.y), panel);
        add(panel);
    }

    public void removePanel(JPanel panel) {
        remove(panel);
        jPanelMap.remove(panel.getLocation());
    }

    public void removePanel(Point p) {
        JPanel panel = jPanelMap.get(p);

        if (panel == null) {
            return;
        }

        remove(panel);
        jPanelMap.remove(p);
    }

    public JPanel getPanel(Point p) {
        return jPanelMap.get(p);
    }

    public void checkCell(MouseListener mouseListener) {

        for (int y = 0; y < row; y++) {
            for (int x = 0; x < col; x++) {
                Point p = new Point(x * width, y * height);

                if (!jPanelMap.containsKey(p)) {
                    Cell c = new Cell();
                    c.addMouseListener(mouseListener);
                    c.setBounds(x * width, y * height, width, height);
                    add(c);
                    jPanelMap.put(p, c);
                }
            }
        }
    }

}
