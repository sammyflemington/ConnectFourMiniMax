package ConnectFour;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class ConnectFour extends JFrame{
	static Board displayBoard;
	private int turn = 1;
	public ConnectFour() {
		super();
		displayBoard = new Board();
		displayBoard.loadFile("data/input2.txt");
		add(displayBoard, BorderLayout.CENTER);
		addMouseListener(new GameListener());
	}
	
	private int nextTurn() {
		return ((turn += 1) % 2) + 1;
	}
	
	private class GameListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {			
		// Press and release of the mouse button
					
			int x = e.getX();
			int y = e.getY();
			int colWidth = getWidth() / displayBoard.getNumCols();
			int col = Math.round(x / colWidth);
			// try to place a piece here for player 
			displayBoard.dropPiece(nextTurn(), col);
			
			repaint();	// Repaint the panel
		}

		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		
	}
	public static void main(String[] args) {
		ConnectFour game = new ConnectFour();
		
		game.setSize(720,720);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		game.setVisible(true);
	}
}
