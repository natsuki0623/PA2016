package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by JuIngong on 05/12/2016.
 */
public class Cell extends JPanel{
    public Cell(){
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.white);
    }

    public void setColorBorder(Color color){
        setBorder(BorderFactory.createLineBorder(color));
    }
}
