import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Stage {
	private Image img;
	private int xPos, yPos, height, width;
	private boolean visible;
	
	public Stage(String img,int x,int y, int width, int height) {
		setImage(img);
		setxPos(x);
		setyPos(y);
		setWidth(width);
		setHeight(height);
		setVisible(true);
	}

	private void setImage(String img2) {
		ImageIcon ii = new ImageIcon(img2);
		this.img = ii.getImage();
		
	}
	
	public Image getImg() {
		return this.img;
	}
	
	public void setxPos(int x) {
		this.xPos = x;
	}
	
	public int getxPos() {
		return this.xPos;
	}
	
	public void setyPos(int y) {
		this.yPos = y;
	}
	
	public int getyPos() {
		return this.yPos;
	}

	public void setWidth(int w) {
		this.width = w;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setHeight(int h) {
		this.height = h;
	}
	
	public int getHeight() {
		return this.height;
	}

	public void setVisible(boolean b) {
		this.visible = b;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(getxPos(),getyPos() ,getWidth(),getHeight());
		
	}
	
}
