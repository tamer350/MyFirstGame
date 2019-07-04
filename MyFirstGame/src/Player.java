import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * OH GAWD WHY DID I LEAVE THIS TILL NOW
 * OH future Anish can work on this
 * it'll most likely change
 * thanks future Anish
 *  */
public class Player {

	private Image img;
	private final int INIT_JUMPSPEED = -20, JUMPMAX = 20, X_ACCEL = 3, Y_ACCEL = 2, X_MAX = 3, Y_MAX = 10;
	private int xPos, yPos, width, height, jumpSpeed, xVel, yVel;
	private boolean visible, isJumping, isFalling, movingLeft, movingRight, facingLeft = true, facingRight = false;
	private ArrayList<Image> left;
	private int leftIndex;
	private long start = System.currentTimeMillis();
	
	public Player(int x,int y, int width, int height) {
		setImages();
		setxPos(x);
		setyPos(y);
		setWidth(width);
		setHeight(height);
		setVisible(true);
	}

	public boolean isVisible() {
		return visible;
	}

	private void setVisible(boolean b) {
		this.visible = b;
	}

	public void setImages() {
		left = new ArrayList<>();
		ImageIcon ii = new ImageIcon("MyFirstGame/src/Images/Player/player_left1.png");
		img = ii.getImage();
		left.add(ii.getImage());
		ii = new ImageIcon("MyFirstGame/src/Images/Player/player_left2.png");
		left.add(ii.getImage());
		ii = new ImageIcon("MyFirstGame/src/Images/Player/player_left1.png");
		left.add(ii.getImage());
		ii = new ImageIcon("MyFirstGame/src/Images/Player/player_left3.png");
		left.add(ii.getImage());
		leftIndex = 0;
	}
	
	public void setImg(Image img) {
		this.img = img;
	}
	public Image getImage() {
		return this.img;
	}
	
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getxPos() {
		return xPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getyPos() {
		return yPos;
	}
	
	private void setHeight(int height) {
		this.height = height;
		
	}

	private void setWidth(int width) {
		this.width = width;
		
	}
	
	public int getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}

	public void moveX() {
		setxPos(getxPos() + getXVel());
	}
	
	public void moveY() {
		setyPos(getyPos()+getYVel());
	}
	
	
	public void setFalling(boolean b) {
		isFalling = b;
		if(isFalling) {
			setYVel(Y_ACCEL);
		}
		else {
			while(yVel > 0) {
				setYVel(-Y_ACCEL);	
			}		
		}
	}
	
	public boolean isFalling() {
		return this.isFalling;
	}
	
	public void setJumping(boolean b) {
		this.isJumping = b;
		if(isJumping && yVel != INIT_JUMPSPEED) {
			setYVel(INIT_JUMPSPEED);
		}
	}
	
	public boolean isJumping() {
		return this.isJumping;
	}
	
	private void setXVel(int xA) {
		if(movingRight && xVel < X_MAX || movingLeft && xVel > -X_MAX) {
			this.xVel += xA;
		}
		else if(!movingRight && !movingLeft){
			while(xVel != 0) {
				this.xVel += xA;
			}
		}
	}
	
	public int getXVel() {
		return this.xVel;
	}
	
	private void setYVel(int yA) {
		if(isJumping) {
			this.yVel = yA;
		}
		else if(isFalling && yVel < Y_MAX || !isFalling && yVel > 0) {
			this.yVel += yA;
		}
		
	}
	
	public int getYVel() {
		return this.yVel;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
		if(movingRight) {
			setXVel(X_ACCEL);
		}
		else {
			setXVel(-X_ACCEL);
		}
	
		this.facingLeft = false;
		this.facingRight = true;
	}
	
	public boolean isMovingRight() {
		return movingRight;
	}

	/**
	 * heres what we need to do, we need to make a timer that starts when player starts moving
	 * check time after every 150 millieseconds
	 * then update the image
	 * if we can do this, timer can go back to standard timer*/
	public void setMovingLeft(boolean movingLeft) {
		if(this.movingLeft == movingLeft) {
			return;
		}
		this.movingLeft = movingLeft;
		if(isMovingLeft()) {
			setXVel(-X_ACCEL);
		}
		else {
			leftIndex = 0;
			setImg(left.get(leftIndex));
			setXVel(X_ACCEL);
		}
		this.facingLeft = true;
		this.facingRight = false;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(getxPos() +2,getyPos() +1,getWidth()-1, getHeight());
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W) {
			setJumping(false);	
		}
		if(key == KeyEvent.VK_D) {
			setMovingRight(false);
		}
		if(key == KeyEvent.VK_A) {
			setMovingLeft(false);
		}
		
	}

	public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
		

		if(key == KeyEvent.VK_W && !isFalling) {
			setJumping(true);
		}
		if(key == KeyEvent.VK_D) {
			setMovingRight(true);
		}
		if(key == KeyEvent.VK_A) {
			setMovingLeft(true);
		}
		
		
	}

	public void drawImage(Graphics g) {
		if(isMovingLeft()) {
			long finish = System.currentTimeMillis();
			if(finish -start >= 75) {
				start = System.currentTimeMillis();
				leftIndex++;
				if(leftIndex >= left.size()) {
					leftIndex = 0;
				}
				setImg(left.get(leftIndex));
			}
		}
		g.drawImage(getImage(), getxPos(), getyPos(), getWidth(),getHeight(), null);
	}

}
