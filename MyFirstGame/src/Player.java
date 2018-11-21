
public class Player {
	
	/*Players position on board*/
	private double x;
	private double y;
	/*Players current speed*/
	private double xs = 0;
	private double ys = 0;
	/*Players acceleration*/
	private double xa = 2;
	private double ya = 2;
	/*Players weight*/
	private int xSize;
	private int ySize;
	
	public Player(double x, double y, int xSize, int ySize) {
		setXPos(x);
		setYPos(y);
		setXSize(xSize);
		setYSize(ySize);
	}
	
	public double getXPos() {
		return this.x;
	}
	
	public void setXPos(double xPos) {
		this.x = xPos;
	}
	
	public double getYPos() {
		return this.y;
	}
	
	public void setYPos(double yPos) {
		this.y = yPos;
	}
	
	public double getXSpeed() {
		return xs;
	}
	
	public void setXSpeed(double xSpeed) {
		this.xs = xSpeed;
	}
	
	public double getYspeed() {
		return this.ys;
	}
	
	public void setYSpeed(double ySpeed) {
		this.ys = ySpeed;
	}
	
	public double getXaAccel() {
		return this.xa;
	}
	
	public void setXAccel(double xAccell) {
		this.xa = xAccell;
	}
	
	public double getYAccel() {
		return this.ya;
	}
	
	public void setYaccel(double yAccell) {
		this.ya = yAccell;
	}
	
	public int getXSize() {
		return this.xSize;
	}
	
	public void setXSize(int xSize) {
		this.xSize = xSize;
	}
	
	public int getYSize() {
		return this.ySize;
	}
	
	public void setYSize(int ySize) {
		this.ySize = ySize;
	}
}
