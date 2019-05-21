import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
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
	private int xPos, yPos, jumpSpeed, xVel, yVel;
	private boolean visible, isJumping, isFalling, movingLeft, movingRight;
	
	public Player(String img,int x,int y) {
		setImage(img);
		setxPos(x);
		setyPos(y);
		setVisible(true);
	}
	
	public boolean isVisible() {
		return visible;
	}

	private void setVisible(boolean b) {
		this.visible = b;
	}

	public void setImage(String img2) {
		ImageIcon ii = new ImageIcon(img2);
		this.img = ii.getImage();
	}
	
	public Image getImage() {
		if(img != null) {
			return this.img;
		}
		return null;
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
	}
	
	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
		if(movingLeft) {
			setXVel(-X_ACCEL);
		}
		else {
			setXVel(X_ACCEL);
		}
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(getxPos() +2,getyPos() +1,img.getWidth(null)-1, img.getHeight(null));
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

	

}
