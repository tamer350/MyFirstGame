import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.*;

import sun.java2d.pipe.DrawImage;

public class Main extends JPanel implements KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int frameXSize = 1200;
	private int frameYSize = 800;
	private int frameXLoc = 350;
	private int frameYLoc = 200;
	
	private Player p;
	
	JFrame frame;
	
	public Main() {
		setUpPanel();
		this.p = new Player(100, 400, 50, 75);
		
		repaint();
	}
	
	/*Just the panel set up*/
	public void setUpPanel() {
		//JPanel panel = new JPanel();
		frame = new JFrame();
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(this.frameXSize, this.frameYSize);
		frame.setLocation(this.frameXLoc, this.frameYLoc);
		frame.addKeyListener(this);
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		if(this.p.getImages().size() > 0) {
			g.drawImage(this.p.getImages().get(this.p.getIdxImg()), this.p.getXPos(), this.p.getYPos(), null);
		}
		
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		
		if(key == 68){
			p.xLine(1,key);
		}
		
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		int playsXs = p.getXSpeed();
		while(playsXs > 0){
			
			p.xLine(-1, key);
			this.repaint();	
			playsXs = p.getXSpeed();
			
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

	
}
