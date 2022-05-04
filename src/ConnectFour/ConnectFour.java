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
	private MinMax mm;
	public ConnectFour() {
		super();
		displayBoard = new Board(1);
		displayBoard.loadFile("data/input2.txt");
		add(displayBoard, BorderLayout.CENTER);
		addMouseListener(new GameListener());
		mm = new MinMax(displayBoard, 1);
	}
	
	// this is bad implemnetation bc what if piece placement fails
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
			displayBoard.dropPiece(1, col);
			Node startNode = new Node(getBoard(), 1, null);
			startNode.setMove(col);
			Node chosen = mm.minimaxNodeAB(startNode, 1, -999999, 999999, false);
			System.out.println(startNode.toString());
			// Now we have constructed the whole tree.
			// pick the best next node

			
			displayBoard.dropPiece(chosen.getNextPlayer(), chosen.getMove());
			
			System.out.println("Chose:" );
			while (chosen != null) {
				System.out.println(chosen.toString());
				chosen = chosen.getParent();
			}
			
			System.out.println(displayBoard.evaluate());
			
			repaint();	// Repaint the panel
		}

		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		
	}
	
	public Board getBoard() {
		return displayBoard;
	}
	
	public static void main(String[] args) {
		ConnectFour game = new ConnectFour();
		
		game.setSize(720,720);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		game.setVisible(true);
	}
}
