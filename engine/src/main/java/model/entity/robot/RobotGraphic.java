package model.entity.robot;

import IPlugin.IDrawing;

import java.awt.*;

/**
 * Class created on 16/11/2016
 *
 * @author JuIngong
 */
public class RobotGraphic extends Component {

    private Robot robot;
    private IDrawing drawing;

    public RobotGraphic(Robot robot, IDrawing drawing) {
        this.robot = robot;
        this.drawing = drawing;
    }

    @Override
    public void paint(Graphics g) {
        drawing.draw(g);
        super.paint(g);
    }
}
