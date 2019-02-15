import java.awt.Graphics;

import com.sun.prism.paint.Paint;

public class Canvas extends java.awt.Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Player p;
	
	private int frameXSize = 1200;
	private int frameYSize = 800;
	private int frameXLoc = 350;
	private int frameYLoc = 200;
	
	public Canvas(Player p) {
		this.p = p;
		this.setSize(frameXSize, frameYLoc);
	}
	
	/*public void paint(Graphics g) {
		if(this.p.getImages().size() > 0) {
			if(g.drawImage(this.p.getImages().get(this.p.getIdxImg()), this.p.getXPos(), this.p.getYPos(), null)) {
				System.out.println(this.p.getXSpeed());
			}
		}
	}*/
	
}
