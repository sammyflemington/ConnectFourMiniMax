package ConnectFour;

import java.util.ArrayList;

public class Node {
	private Board board;
	private int nextPlayer;
	private int value;
	private ArrayList<Node> successors;
	
	public Node(Board b, int nextPlayerToMove) {
		board = b;
		nextPlayer = nextPlayerToMove;

	}
	public Board getBoard() {
		return board;
	}
	public void findSuccessors(int limit, int playerToOptimize) {
		if (limit > 0) {
			for (int i = 0; i < 7; i++) { // there are only 7 columns where we can place a piece
				if (board.checkCanPlace(i)) {
					// create a new branch
					Board b = new Board(board); // copy board
					b.dropPiece(nextPlayer, i);
					Node n = new Node(b, ((nextPlayer + 1) % 2 ) + 1);
					successors.add(n);
					n.findSuccessors(limit - 1, playerToOptimize);
				}
			}
		} else {
			// Return the best result
			
		}
	}
	
	public ArrayList<Node> getSuccessors(){
		return successors;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getNextPlayer() {
		return nextPlayer;
	}
}
