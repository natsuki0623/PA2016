package pluginDrawing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
public class PickachuDrawing extends JPanel {
     
    private static final long   serialVersionUID    = 1L;
     
    protected Image buffer;    
     
    public PickachuDrawing(Image buffer){
        this.buffer = buffer;
    }  
         
    public void paintComponent(Graphics g) {
    	try {
			Image img1 = ImageIO.read(new File("pickachu.png"));
			 g.drawImage(buffer,50,50,null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
     }
    public class ImagePane extends JPanel {
        
        public static final long   serialVersionUID    = 1L;
         
        public Image buffer;    
         
        public ImagePane(String string) throws IOException{
            this.buffer = ImageIO.read(new File(string));
        }  
             
        public void paintComponent(Graphics g) {
           g.drawImage(buffer,0,0,null);
         }
    }
    
    public static void main(String[] args) throws IOException {
//    	JFrame f1 = new JFrame();
//		Image img1 = ImageIO.read(new File("C:\\Users\\antoi\\Desktop\\PA\\PA2016\\plugins\\Pickachu.png"));
//		PickachuDrawing picka = new PickachuDrawing(img1);
//		picka.paintComponent(g);
    	
    	    JFrame fen = new JFrame ("Ordinateur Qui Parle");
    	  
    	    JPanel pan = new ImagePane("/data/robot.png");
    	    pan.setBackground(Color.BLUE);
    	    fen.setContentPane(pan); 
    	  
    	    JTextField tape = new JTextField ();
    	  
    	    Font police = new Font ("Arial", Font.BOLD, 25);
    	    pan.add(tape);
    	    tape.setBounds(60,450,560,50);
    	    tape.setFont(police); 
    	  
    	    JButton bouton = new JButton ("Dis lui !");
    	    pan.add(bouton);
    	    bouton.setVisible(true);
    	    bouton.setBounds(650,450,150,50);
    	 
    	   fen.setSize(850,600);
    	   fen.pack();
    	   fen.setLocationRelativeTo(null);
    	   fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	   fen.setVisible(true);
    	
	}
}