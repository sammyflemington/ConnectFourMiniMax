package ConnectFour;

import java.util.ArrayList;

public class Node {
	private Board board;
	private int nextPlayer;
	private int value;
	private ArrayList<Node> successors;
	private Node parent = null;
	private int move = -1;
	
	public Node(Board b, int nextPlayerToMove, Node p) {
		board = b;
		nextPlayer = nextPlayerToMove;
		parent = p;
		successors = new ArrayList<Node>();
	}
	public Board getBoard() {
		return board;
	}
	
	public int getMove() {
		return move;
	}
	
	public Node getParent() {
		return parent;
	}
	public void findSuccessors() {
		for (int i = 0; i < 7; i++) { // there are only 7 columns where we can place a piece
			if (board.checkCanPlace(i)) {
				// create a new branch
				Board b = new Board(board); // copy board
				b.dropPiece(nextPlayer, i);
				Node n = new Node(b, ((nextPlayer + 1) % 2 ) + 1, this);
				n.setMove(i);
				successors.add(n);
			}
		}
	}
	
	public void setMove(int i) {
		move = i;
	}
	public ArrayList<Node> getSuccessors(){
		return successors;
	}
	
	public int getValue() {
		return board.evaluate();
	}
	
	public int getNextPlayer() {
		return nextPlayer;
	}
	
	public String toString() {
		String s = "";
		for (Integer[] r : board.getBoard()) {
			for (Integer c : r) {
				s += String.valueOf((int) c);
			}
			s += '\n';
		}
		s += '\n';
		s += String.valueOf(getValue());
		return s;
	}
}
