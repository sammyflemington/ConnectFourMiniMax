package ConnectFour;

import java.util.ArrayList;

public class Node {
	private Board board;
	private int thisPlayer;
	private int value;
	private ArrayList<Node> successors;
	private Node parent = null;
	private int move = -1;
	
	public Node(Board b, int thisPlayer, Node p) {
		board = b;
		this.thisPlayer = thisPlayer;
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
		try {
			for (int i = 0; i < 7; i++) { // there are only 7 columns where we can place a piece
				if (board.checkCanPlace(i)) {
					// create a new branch
					Board b = new Board(board); // copy board
					if (!b.dropPiece(nextPlayer(thisPlayer), i)) break;
					Node n = new Node(b, nextPlayer(thisPlayer), this);
					//System.out.println(n.toString());
					n.setMove(i);
					successors.add(n);
				}
			}
		}catch(IndexOutOfBoundsException e) {
			System.out.println(e);
		}
	}
	
	public int nextPlayer(int player) {
		if (player == 1) {
			return 2;
		}else {
			return 1;
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
		return thisPlayer;
	}
	
	public String toString() {
		String s = String.valueOf(getValue()) + '\n';
		for (Integer[] r : board.getBoard()) {
			for (Integer c : r) {
				s += String.valueOf((int) c);
			}
			s += '\n';
		}
		return s;
	}
	
//	public static void main(String[] args) {
//		Node n = new Node(new Board(1), 1, null);
//		
//		System.out.println(n.nextPlayer(1));
//		System.out.println(n.nextPlayer(2));
//	}
}
