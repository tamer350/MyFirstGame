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


public class Board extends JPanel implements ActionListener{
	
	private final int B_WIDTH = 800, B_HEIGHT = 400, DELAY = 15;
	private Player p;
	private ArrayList<Stage>wall = new ArrayList<>();
	private ArrayList<Stage>floor = new ArrayList<>();
	private Timer timer;
	private String wallPic = "MyFirstGame/src/Images/stage/wall.png", floorPic = "MyFirstGame/src/Images/stage/floor.png";
	
	
	public Board() {
		initUI();
	}
	
	private void initUI() {
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		addKeyListener(new TAdapter());
		setFocusable(true);
		
		p = new Player("MyFirstGame/src/Images/Player/hitbox1.png", 300, 0);
		floor.add(new Stage(floorPic, 0, 100, 100, 10));
		floor.add(new Stage(floorPic, 100, 200, 100, 10));
		floor.add(new Stage(floorPic, 200, 300, 200, 10));
		floor.add(new Stage(floorPic, 400, 250, 100, 10));
		floor.add(new Stage(floorPic, 500, 200, 100, 10));
		floor.add(new Stage(floorPic, 600, 150, 100, 10));
		floor.add(new Stage(floorPic, 700, 100, 100, 10));
		wall.add(new Stage(wallPic, 80, 110, 20, 150));
		wall.add(new Stage(wallPic, 180, 210, 20, 150));
		wall.add(new Stage(wallPic, 400, 260, 20, 150));
		wall.add(new Stage(wallPic, 500, 210, 20, 150));
		wall.add(new Stage(wallPic, 600, 160, 20, 150));
		wall.add(new Stage(wallPic, 700, 110, 20, 150));
		timer = new Timer(DELAY, this);
		timer.start();

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		updatePlayer();
		repaint();
	}

	private void updatePlayer() {
		if(!collisionX() && (p.isMovingRight() ||p.isMovingLeft())) {
			p.moveX();
		}
		
		if(p.isJumping()) {
			p.moveY();
			p.setJumping(false);
		}
		else if(!collisionD()) {
			p.setFalling(true);
			p.moveY();
		}
		else if(p.isFalling()){
			p.setFalling(false);
		}
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(p.getImage(), p.getxPos(), p.getyPos(), this);
		for(Stage stage: wall) {
			g.drawImage(stage.getImg(), stage.getxPos(), stage.getyPos(), stage.getWidth(), stage.getHeight(), null);
		}
		for(Stage stage: floor) {
			g.drawImage(stage.getImg(), stage.getxPos(), stage.getyPos(), stage.getWidth(), stage.getHeight(), null);
		}
		Toolkit.getDefaultToolkit().sync();
	}
	
	public boolean collisionD() {
		if(p.isVisible()) {
			Rectangle pRect = p.getBounds();
			int pY = (int)pRect.getMaxY();
			int pXMin = (int)pRect.getMinX();
			int pXMax = (int)pRect.getMaxX();
			
			for(Stage s: floor) {
				Rectangle sRect = s.getBounds();
				for(int i = pXMin; i < pXMax; i++) {
					if(sRect.contains(new Point(i,pY))) {
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean collisionX() {
		if(p.isVisible()) {
			Rectangle pRect = p.getBounds();
			int pX;
			if(p.isMovingLeft()) {
				pX = (int)pRect.getMinX();
			}
			else if(p.isMovingRight()) {
				pX = (int)pRect.getMaxX();
			}
			else {
				return false;
			}
			int pYMin = (int)pRect.getMinY();
			int pYMax = (int)pRect.getMaxY();
			for(Stage s: wall) {
				Rectangle sRect = s.getBounds();
				for(int i = pYMin; i < pYMax; i++) {
					if(sRect.contains(new Point(pX, i))){
						return true;
					}
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


