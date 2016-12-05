package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by JuIngong on 05/12/2016.
 */
public class Wall extends JPanel{

    public Wall(Rectangle rectangle){
        setBackground(Color.black);
        setBounds(rectangle);
    }
}
