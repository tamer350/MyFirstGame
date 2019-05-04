import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.w3c.dom.css.Rect;


public class Board extends JPanel implements ActionListener{
	
	private final int B_WIDTH = 800, B_HEIGHT = 400, DELAY = 15;
	private Player p;
	private ArrayList<Stage>stg = new ArrayList<>();;
	private Timer timer;
	
	
	public Board() {
		initUI();
	}
	
	private void initUI() {
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		addKeyListener(new TAdapter());
		setFocusable(true);
		
		p = new Player("MyFirstGame/src/Images/Player/hitbox0.png", 300, 200);
		stg.add(new Stage("MyFirstGame/src/Images/stage/wall_floor.png", 600, 100, 100, 200));
		stg.add(new Stage("MyFirstGame/src/Images/stage/wall_floor.png", 100, 100, 100, 200));
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
			Rectangle pRect = p.getBounds();
			if(p.getXVel() > 0) {
				if(!collision((int)pRect.getMaxX(), (int)pRect.getMinY(), (int)pRect.getHeight())) {
					p.move();
				}
				p.jump();
			}
			else if(p.getXVel() < 0) {
				if(!collision((int)pRect.getMinX(), (int)pRect.getMinY(), (int)pRect.getHeight())) {
					p.move();
				}
				p.jump();
			}
			else {
				p.move();
				p.jump();
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(p.getImage(), p.getxPos(), p.getyPos(), this);
		for(Stage stage: stg) {
			g.drawImage(stage.getImg(), stage.getxPos(), stage.getyPos(), stage.getWidth(), stage.getHeight(), null);
		}
		Toolkit.getDefaultToolkit().sync();
	}
	
	private boolean collision(int playerMaxX, int playerMinY, int playerHeight) {
		for(Stage stage : stg) {
		Rectangle stageR = stage.getBounds();
		
		for(int i = playerMinY; i < playerMinY+playerHeight; i++) {
			Point p = new Point(playerMaxX, i);
			if(stageR.contains(p)) {
				return true;
			}
		}
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
			p.keyPressed(e);
		}
		
	}
	
   
}


