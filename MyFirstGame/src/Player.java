import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;


public class Player {

	private Image img;
	private final int INIT_JUMPSPEED = -20, JUMPMAX = 20, X_ACCEL = 4, Y_ACCEL = 2, X_MAX = 4;
	private int xPos, yPos, jumpSpeed, xVel, yVel;
	private boolean visible, isJumping;
	
	public Player(String img,int x,int y) {
		setImage(img);
		setxPos(x);
		setyPos(y);
		setVisible(true);
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

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public boolean isVisible() {
		return visible;
	}

	public void move() {
		
		setxPos(getxPos() + getXVel());
	}
	
	public void jump() {
		if(isJumping) {
			this.yPos += this.jumpSpeed;
			jumpSpeed += Y_ACCEL;
			if(jumpSpeed > JUMPMAX) {
				setIsJumping(false);
				jumpSpeed = INIT_JUMPSPEED;
			}
		}
	}

	

	private void setIsJumping(boolean b) {
		this.isJumping = b;
	}
	
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_D) {
			while(getXVel() > 0) {
				setXVel(-this.X_ACCEL);
			}
		}
		if(key == KeyEvent.VK_A) {
			while(getXVel() < 0) {
				setXVel(this.X_ACCEL);
			}
		}
		
	}

	public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W && isJumping == false) {
			setIsJumping(true);
			jumpSpeed = INIT_JUMPSPEED;
		}
		if(key == KeyEvent.VK_D) {
			if(getXVel() < X_MAX) {
				setXVel(this.X_ACCEL);
			}
		}
		if(key == KeyEvent.VK_A) {
			if(getXVel() > -X_MAX) {
				setXVel(-this.X_ACCEL);
			}
		}
		
		
	}

	private void setXVel(int xA) {
		this.xVel += xA;
		
	}
	
	public int getXVel() {
		return this.xVel;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(getxPos(),getyPos(),img.getWidth(null), img.getHeight(null));
	}

}
