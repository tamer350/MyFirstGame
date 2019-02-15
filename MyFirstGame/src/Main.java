import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.*;

import sun.java2d.pipe.DrawImage;

public class Main extends JPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int frameXSize = 1200;
	private int frameYSize = 800;
	private int frameXLoc = 350;
	private int frameYLoc = 200;
	
	private Player player ;
	Thread printer;
	
	JFrame frame;
	//Canvas can;
	
	public Main() {
		this.player = new Player(100, 400, 50, 75);
		setUpPanel();
		printer = new Thread(this);
		printer.start();
	}
	
	public void run() {
		while(true) {
			repaint();
		}
	}
	
	/*Just the panel set up*/
	public void setUpPanel() {
		frame = new JFrame();
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(this.frameXSize, this.frameYSize);
		frame.setLocation(this.frameXLoc, this.frameYLoc);
		frame.addKeyListener(player);
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		if(player != null) {
			g.drawImage(this.player.getImages().get(this.player.getIdxImg()), this.player.getXPos(), this.player.getYPos(), null);
		}
	}
	
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

	
}
