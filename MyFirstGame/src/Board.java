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
	private ArrayList<Stage>stage = new ArrayList<>();
	private Timer timer;
	private String wallPic = "MyFirstGame/src/Images/stage/wall.png", floorPic = "MyFirstGame/src/Images/stage/floor.png";
	private boolean moveR = false, moveL = false;
	
	
	public Board() {
		initUI();
	}
	
	private void initUI() {
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		addKeyListener(new TAdapter());
		setFocusable(true);
		
		p = new Player(300, 0,100,100);//give player its own folder instead
		stage.add(new Stage(floorPic, 0, 100, 100, 10, Stage.type.FLOOR));
		stage.add(new Stage(floorPic, 100, 200, 100, 10, Stage.type.FLOOR));
		stage.add(new Stage(floorPic, 200, 300, 200, 10, Stage.type.FLOOR));
		stage.add(new Stage(floorPic, 400, 250, 100, 10, Stage.type.FLOOR));
		stage.add(new Stage(floorPic, 500, 200, 100, 10, Stage.type.FLOOR));
		stage.add(new Stage(floorPic, 600, 150, 100, 10, Stage.type.FLOOR));
		stage.add(new Stage(floorPic, 700, 100, 100, 10, Stage.type.FLOOR));
		stage.add(new Stage(wallPic, 90, 110, 10, 150, Stage.type.WALL));
		stage.add(new Stage(wallPic, 190, 210, 10, 150, Stage.type.WALL));
		stage.add(new Stage(wallPic, 400, 260, 10, 150, Stage.type.WALL));
		stage.add(new Stage(wallPic, 500, 210, 10, 150, Stage.type.WALL));
		stage.add(new Stage(wallPic, 600, 160, 10, 150, Stage.type.WALL));
		stage.add(new Stage(wallPic, 700, 110, 10, 150, Stage.type.WALL));
		stage.add(new Stage(floorPic, 800, 100, 100, 10, Stage.type.FLOOR));
		stage.add(new Stage(floorPic, 900, 200, 100, 10, Stage.type.FLOOR));
		stage.add(new Stage(floorPic, 1000, 300, 200, 10, Stage.type.FLOOR));
		stage.add(new Stage(floorPic, 1200, 250, 100, 10, Stage.type.FLOOR));
		stage.add(new Stage(floorPic, 1300, 200, 100, 10, Stage.type.FLOOR));
		stage.add(new Stage(floorPic, 1400, 150, 100, 10, Stage.type.FLOOR));
		stage.add(new Stage(floorPic, 1500, 100, 100, 10, Stage.type.FLOOR));
		timer = new Timer(DELAY,this);
		timer.start();

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		updatePlayer();
		updateStage();
		repaint();
	}
	
	//moves stage need more work to be done
	private void updateStage() {
		/*int xposNew = 0;
		if(moveR && !collisionX()) {
			xposNew = -3;
		}
		else if(moveL && !collisionX()) {
			xposNew = 3;
		}
		
		if(xposNew != 0) {
		for(Stage s: stage) {
			s.setxPos(s.getxPos() + xposNew);
		}
		}*/
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
		p.drawImage(g);
		for(Stage s: stage) {
			g.drawImage(s.getImg(), s.getxPos(), s.getyPos(), s.getWidth(), s.getHeight(), null);
		}
		Toolkit.getDefaultToolkit().sync();
	}
	
	public boolean collisionD() {
		if(p.isVisible()) {
			Rectangle pRect = p.getBounds();
			int pY = (int)pRect.getMaxY();
			int pXMin = (int)pRect.getMinX();
			int pXMax = (int)pRect.getMaxX();
			
			for(Stage s: stage) {
				if(s.getType() == Stage.type.FLOOR) {
				Rectangle sRect = s.getBounds();
				for(int i = pXMin; i < pXMax; i++) {
					if(sRect.contains(new Point(i,pY))) {
						return true;
					}
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
			if(p.isMovingLeft() || moveL) {
				pX = (int)pRect.getMinX()+1;
			}
			else if(p.isMovingRight() || moveR) {
				pX = (int)pRect.getMaxX()-1;
			}
			else {
				return false;
			}
			int pYMin = (int)pRect.getMinY();
			int pYMax = (int)pRect.getMaxY();
			for(Stage s: stage) {
				if(s.getType() == Stage.type.WALL) {
				Rectangle sRect = s.getBounds();
				for(int i = pYMin; i < pYMax; i++) {
					if(sRect.contains(new Point(pX, i))){
						return true;
					}
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
			//p.keyReleased(e);
			int key = e.getKeyCode();
			switch (key){
				case KeyEvent.VK_D:
					//moveR = false;
					p.setMovingRight(false);
					break;
				case KeyEvent.VK_A:
					//moveL = false;
					p.setMovingLeft(false);
					break;
				case KeyEvent.VK_W:
					p.keyReleased(e);
					break;
			}
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			//p.keyPressed(e);
			int key = e.getKeyCode();
			switch (key){
				case KeyEvent.VK_D:
					//moveR = true;
					p.setMovingRight(true);
					break;
				case KeyEvent.VK_A:
					p.setMovingLeft(true);
					break;
				case KeyEvent.VK_W:
					p.keyPressed(e);
					break;
			}
		}
		
	}
	
   
}


