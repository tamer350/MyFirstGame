
public class Player {
	
	/*Players position on board*/
	private int x;
	private int y;
	/*Players current speed*/
	private int xs = 0;
	private int ys = 0;
	/*Players acceleration*/
	private int xa = 2;
	private int ya = 2;
	/*Players weight*/
	private int xSize;
	private int ySize;
	
	private int maxSpeed = 15;
	
	public Player(int x, int y, int xSize, int ySize) {
		setXPos(x);
		setYPos(y);
		setXSize(xSize);
		setYSize(ySize);
	}
	
	public void xLine(int xl, int keyCode) {
		if(xl > 0 && keyCode == 68) {
			if(this.xs < this.maxSpeed) {
				this.xs += this.xa;
			}
		}
		else if(xl < 0 && keyCode == 68) {
			if(this.xs > 0) {
				this.xs -= this.xa;
			}	
		}
		this.x += this.xs;
		System.out.println(this.xs);
	}
	
	
	
	public int getXPos() {
		return this.x;
	}
	
	public void setXPos(int xPos) {
		this.x = xPos;
	}
	
	public int getYPos() {
		return this.y;
	}
	
	public void setYPos(int yPos) {
		this.y = yPos;
	}
	
	public int getXSpeed() {
		return xs;
	}
	
	public void setXSpeed(int xSpeed) {
		this.xs = xSpeed;
	}
	
	public int getYspeed() {
		return this.ys;
	}
	
	public void setYSpeed(int ySpeed) {
		this.ys = ySpeed;
	}
	
	public int getXaAccel() {
		return this.xa;
	}
	
	public void setXAccel(int xAccell) {
		this.xa = xAccell;
	}
	
	public int getYAccel() {
		return this.ya;
	}
	
	public void setYaccel(int yAccell) {
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
	
	public int getMaxSpeed() {
		return this.maxSpeed;
	}
	
	public void setMaxSpeed(int nMSpeed) {
		this.maxSpeed = nMSpeed;
	}
}
