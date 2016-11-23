package plateau;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class PluginSelecter {
	public PluginSelecter() {
		super();

		JFrame t = new JFrame();
		
		t.setSize(400, 700);
		JPanel listPane = new JPanel();

		listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
		Border blackline = BorderFactory.createLineBorder(Color.black, 1);

		//
		String[] listRobot = {"Manon", "Flo", "Antoine"};
		String[] listAttack = {"Cac", "midRange", "longRange"};
		String[] listMove = {"slow", "middle", "speed"};
		String[] listGraphic = {"Yoda", "Pikachu", "Ornithorynque"};
		JButton validate = new JButton();
		validate.setText("OK");
		validate.setSize(400, 30);

		listPane.add(new Label("Selectionner robot"));
		listPane.add(new JComboBox(listRobot));
		listPane.add(new Label("Selectionner type d'attaque"));
		listPane.add(new JComboBox(listAttack));
		listPane.add(new Label("Selectionner type de mouvement"));
		listPane.add(new JComboBox(listMove));
		listPane.add(new Label("Selectionner type de graphisme"));
		listPane.add(new JComboBox(listAttack));
		listPane.add(validate);
		
		
		listPane.setBorder(blackline);
		t.add(listPane);
		t.setVisible(true);
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {		
		PluginSelecter p = new PluginSelecter();
	}

}
