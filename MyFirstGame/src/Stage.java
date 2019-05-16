import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Stage {
	
	private Image img;	//image from image folder, currently just a block of colour
	private int xPos, yPos, //position of block
				height, width; //Dimension of block
	private boolean visible; // will be used when I implement stage movement
	
	/**
	 * Constructor, image file location, positioning, dimension, very simple
	 * */
	public Stage(String img,int x,int y, int width, int height) {
		setImage(img);
		setxPos(x);
		setyPos(y);
		setWidth(width);
		setHeight(height);
		setVisible(true);
	}
	
	/**
	 * Sets the Image to be drawn on panel
	 * */
	private void setImage(String img2) {
		//to do: if statement to check if img2 is a string
		ImageIcon ii = new ImageIcon(img2); //ImageIcon has worked so far, so kept using it
		this.img = ii.getImage();
		
	}
	
	/**
	 * Returns the Image from the file location
	 * */
	public Image getImg() {
		return this.img;
	}
	
	/**
	 * Sets X value of Image
	 * */
	public void setxPos(int x) {
		this.xPos = x;
	}
	
	/**
	 * Returns X value of Image
	 * */
	public int getxPos() {
		return this.xPos;
	}
	
	/**
	 * Sets Y value of Image
	 * */
	public void setyPos(int y) {
		this.yPos = y;
	}
	
	/**
	 * Returns Y value of Image 
	 * */
	public int getyPos() {
		return this.yPos;
	}
	
	/**
	 * Sets the width of the Image
	 * */
	public void setWidth(int w) {
		this.width = w;
	}
	
	/**
	 * Returns the width value of the Image
	 * */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Sets the height of the Image
	 * */
	public void setHeight(int h) {
		this.height = h;
	}
	
	/**
	 * Returns the height value of the Image
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Checks if the Image is on the screen or not
	 */
	public void setVisible(boolean b) {
		this.visible = b;
	}
	
	/**
	 * Bounds are made by Rectangles, it has simple get methods needed for collision detection
	 * */
	public Rectangle getBounds() {
		return new Rectangle(getxPos(),getyPos(),getWidth(),getHeight());
		
	}
	
}
