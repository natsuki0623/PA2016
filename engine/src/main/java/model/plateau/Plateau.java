package model.plateau;

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
		JPanel pan = new JPanel();
		Border blackline = BorderFactory.createLineBorder(Color.black, 1);
		pan.setBorder(blackline);
		t.add(pan);
		t.setVisible(true);
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Plateau p1 = new Plateau();
	}
	
}
