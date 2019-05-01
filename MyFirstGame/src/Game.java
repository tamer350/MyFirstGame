import javax.swing.JFrame;
import java.awt.EventQueue;

public class Game extends JFrame{
	
	public Game() {
		initGame();
	}
	
	private void initGame() {
		this.add(new Board());
		
		setResizable(false);
		pack();
		
		setTitle("My First Game");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(()-> {
			Game ex = new Game();
			ex.setVisible(true);
		});
	}

}
