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
import java.io.File;
import java.util.ArrayList;


import javax.swing.JPanel;
import javax.swing.Timer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;




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
		
		p = new Player(0, 0,100,100);//give player its own folder instead
		initStage();
		timer = new Timer(DELAY,this);
		timer.start();

	}
	private void initStage() {
		try {
			File xmlFile = new File("MyFirstGame/src/levels/Levels.xml");
			DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuild = dbFact.newDocumentBuilder();
			Document doc = dBuild.parse(xmlFile);
			Node stageinfile = doc.getElementsByTagName("stage").item(0);
			NodeList blocksList = stageinfile.getChildNodes();
			for(int i = 0; i<blocksList.getLength(); i++) {
				Node child = blocksList.item(i);
				if(child.getNodeType() == Node.ELEMENT_NODE) {
					Element block = (Element)child;
					int xpos = Integer.parseInt(block.getAttribute("xpos"));
					int ypos = Integer.parseInt(block.getAttribute("ypos"));
					String type = block.getElementsByTagName("type").item(0).getTextContent();
					
					if(type.equalsIgnoreCase("wall")) {
						int height = Integer.parseInt(block.getElementsByTagName("length").item(0).getTextContent()) * 10;
						stage.add(new Stage(xpos, ypos, 10, height, Stage.type.WALL));
					}
					else if(type.equalsIgnoreCase("floor")) {
						int width = Integer.parseInt(block.getElementsByTagName("length").item(0).getTextContent()) * 10;
						stage.add(new Stage(xpos, ypos, width, 10, Stage.type.FLOOR));
					}
					
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		updatePlayer();
		updateStage();
		repaint();
	}
	
	
	private void updateStage() {
		int xposNew = 0;
		if(!collisionX() && p.isMovingRight() && p.getxPos() >= 300 && stage.get(stage.size()-1).getxPos() + stage.get(stage.size()-1).getWidth() >= this.B_WIDTH) {
			xposNew = -3;
		}
		else if(!collisionX() && p.isMovingLeft() && stage.get(0).getxPos() <=0 && p.getxPos() <= this.B_WIDTH -300) {
			xposNew = 3;
		}
		
		if(xposNew != 0) {
		for(Stage s: stage) {
			s.setxPos(s.getxPos() + xposNew);
		}
		} 
		
	}

	private void updatePlayer() {
		if(!collisionX()//checks collision x axis 
				&&((p.isMovingRight()//and if characters moving right
						&& (p.getxPos() <= 300)//and if characters position is at 300 
						|| stage.get(stage.size()-1).getxPos() + stage.get(stage.size()-1).getWidth() <= this.B_WIDTH)//or if the end of the stage is shown
				||(p.isMovingLeft()//or if player is moving left
						&& (stage.get(0).getxPos() >= 0)//and beginning of stage is shown
						|| p.getxPos() >= this.B_WIDTH - 300))) {//or if characters position is at width - 300 
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
					p.setMovingRight(false);
					break;
				case KeyEvent.VK_A:
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


