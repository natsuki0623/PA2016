package plateau;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Plateau {

	public Plateau() {
		super();

		JFrame t = new JFrame();
		
		t.setSize(1000, 1000);
		JPanel pan = new JPanel(new GridLayout(10, 10));
		Border blackline = BorderFactory.createLineBorder(Color.black, 1);
		for (int i = 0; i < 100; i++) {
			JPanel ptest = new JPanel();
			ptest.setBorder(blackline);
			pan.add(ptest);
		}
		pan.setBorder(blackline);
		t.add(pan);
		t.setVisible(true);
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
