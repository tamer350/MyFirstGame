import javax.swing.JFrame;
import java.awt.EventQueue;

/**
 * My First Game is coming together, this is the main class that will hold
 * the frame and panel, it'll only have frame commands, like close, minimise and 
 * other toolbar ones if they come to mind. will work on this documentation once 
 * I have a fully working game.
 * */
public class Game extends JFrame{
	
	/**
	 * Gets Called by the main method, no clue why it needs to be here 
	 * but it helps the game run without errors
	 * */
	public Game() {
		initGame();
	}
	
	/**
	 * InitGame adds a new panel called board, which holds the logic
	 * sets size, title and position of window 
	 * */
	private void initGame() {
		this.add(new Board());
		
		setResizable(false);
		pack();
		
		setTitle("My First Game");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Called on start up, will use args for saved games hopefully
	 * */
	public static void main(String[] args) {
		EventQueue.invokeLater(()-> {
			Game ex = new Game();
			ex.setVisible(true);
		});
	}

}
