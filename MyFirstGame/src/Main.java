import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
	
	private int frameXSize = 1200;
	private int frameYSize = 800;
	private int frameXLoc = 350;
	private int frameYLoc = 200;
	
	JFrame frame;
	
	public Main() {
		setUpPanel();
	}
	
	/*Just the panel set up*/
	public void setUpPanel() {
		JPanel panel = new JPanel();
		frame = new JFrame();
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(this.frameXSize, this.frameYSize);
		frame.setLocation(this.frameXLoc, this.frameYLoc);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
