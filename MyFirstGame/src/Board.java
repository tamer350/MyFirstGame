import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener{
	
	private final int B_WIDTH = 800, B_HEIGHT = 400, DELAY = 15;
	private Player p;
	private Timer timer;
	
	
	public Board() {
		initUI();
	}
	
	private void initUI() {
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		addKeyListener(new TAdapter());
		setFocusable(true);
		
		p = new Player("MyFirstGame/src/Images/Player/hitbox0.png", 100, 200);
		
		timer = new Timer(DELAY, this);
		timer.start();

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		updatePlayer();
		repaint();
	}

	private void updatePlayer() {
		if(p.isVisible()) {
			p.move();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(p.getImage(), p.getxPos(), p.getyPos(), this);
		Toolkit.getDefaultToolkit().sync();
	}
	
    private class TAdapter extends KeyAdapter{
		
    	public TAdapter() {
		}
		@Override
		public void keyReleased(KeyEvent e) {
			p.keyReleased(e);
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			p.keyPressed(e);
			
		}
	}
	
   
}


