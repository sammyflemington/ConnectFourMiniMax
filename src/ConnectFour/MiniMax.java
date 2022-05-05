package ConnectFour;

import java.util.ArrayList;

public class MiniMax {
	Node start;
	public MiniMax(Board b, int nextPlayer) {
	}
	// need to rewrite to work with Node objects since we need to return the node we wish to pursue
	public Node minimaxNode(Node n, int depth, boolean maximizingPlayer) {
		if (depth == 0) {
			return n;
		}
		n.findSuccessors();
		Node best = null;
		if (n.getSuccessors().size() > 0)
			best = n.getSuccessors().get(0);
		else
			return n;
		
		if (maximizingPlayer) {
			int maxEval = -10000;
			for (Node child : n.getSuccessors()) {
				Node evalNode = minimaxNode(child, depth-1, false);
				int eval = evalNode.getValue();
				if (eval > maxEval)
					best = child; 
				maxEval = Math.max(maxEval, eval);
			}
			
			return best;
		}else {
			int minEval = 10000;
			for (Node child : n.getSuccessors()) {
				Node evalNode = minimaxNode(child, depth-1, true);
				int eval = evalNode.getValue();
				if (eval < minEval)
					best = child;
				minEval = Math.min(minEval, eval);
			}
			return best;
		}
	}
	
	public Node minimaxNodeAB(Node n, int depth, int alpha, int beta, boolean maximizingPlayer) {
		if (depth == 0) {
			return n;
		}
		// Find successive nodes now so we can return if there are none.
		n.findSuccessors();
		Node best = null;
		if (n.getSuccessors().size() > 0)
			best = n.getSuccessors().get(0);
		else
			return n;
		
		if (maximizingPlayer) {
			int maxEval = -10000;
			for (Node child : n.getSuccessors()) {
				Node evalNode = minimaxNode(child, depth-1, false);
				int eval = evalNode.getValue();
				if (eval > maxEval)
					best = child; 
				maxEval = Math.max(maxEval, eval);
				
				alpha = Math.max(alpha, maxEval);
				if (beta <= alpha) {
					break;
				}
			}
			
			return best;
		}else {
			int minEval = 10000;
			for (Node child : n.getSuccessors()) {
				Node evalNode = minimaxNode(child, depth-1, true);
				int eval = evalNode.getValue();
				if (eval < minEval)
					best = child;
				minEval = Math.min(minEval, eval);
				beta = Math.min(beta,  minEval);
				if (beta <= alpha) {
					break;
				}
			}
			return best;
		}
	}
	
	public int minimax(Node n, int depth, boolean maximizingPlayer) {
		if (depth == 0) {
			return n.getBoard().evaluate();
		}
		
		if (maximizingPlayer) {
			int maxEval = -10000;
			n.findSuccessors();
			for (Node child : n.getSuccessors()) {
				int eval = minimax(child, depth - 1, false);
				maxEval = Math.max(maxEval, eval);
			}
			return maxEval;
		}else {
			int minEval = 10000;
			n.findSuccessors();
			for (Node child : n.getSuccessors()) {
				int eval = minimax(child, depth - 1, true);
				minEval = Math.min(minEval, eval);
			}
			return minEval;
		}
	}
	
	public static void main(String[] args) {
		// arg 0 is the input file, arg 1 is computer-next or human-next, arg 2 is depth.
		Board board = new Board(1);
		board.loadFile(args[0]);
		
		
	}
}
