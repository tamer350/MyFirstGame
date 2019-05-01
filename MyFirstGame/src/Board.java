import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.w3c.dom.css.Rect;


public class Board extends JPanel implements ActionListener{
	
	private final int B_WIDTH = 800, B_HEIGHT = 400, DELAY = 15;
	private Player p;
	private Stage stg;
	private Timer timer;
	
	
	public Board() {
		initUI();
	}
	
	private void initUI() {
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		addKeyListener(new TAdapter());
		setFocusable(true);
		
		p = new Player("MyFirstGame/src/Images/Player/hitbox0.png", 100, 200);
		stg = new Stage("MyFirstGame/src/Images/stage/wall_floor.png", 600, 100, 100, 200);
		timer = new Timer(DELAY, this);
		timer.start();

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		updatePlayer();
		repaint();
	}

	private void updatePlayer() {
		if(p.isVisible() && !checkCollisions()) {
			p.move();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(p.getImage(), p.getxPos(), p.getyPos(), this);
		g.drawImage(stg.getImg(), stg.getxPos(), stg.getyPos(), stg.getWidth(), stg.getHeight(), null);
		Toolkit.getDefaultToolkit().sync();
	}
	
	private boolean checkCollisions() {
		Rectangle play = p.getBounds();
		Rectangle wall = stg.getBounds();
		if(play.intersects(wall)) {
			return true;
		}
		return false;
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
			if(!checkCollisions()) {
				p.keyPressed(e);
			}
			
		}
		
	}
	
   
}


