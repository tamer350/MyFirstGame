import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JPanel{
	
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
	}
	
	public void paint(Graphics g) {
		g.drawRect(this.p.getXPos(), this.p.getYPos(), this.p.getXSize(), this.p.getYSize());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
