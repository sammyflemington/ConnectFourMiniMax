package ConnectFour;

import java.util.ArrayList;

public class MinMax {
	Node start;
	public MinMax(Board b, int nextPlayer) {
	}
	// need to rewrite to work with Node objects since we need to return the node we wish to pursue
	public Node minimaxNode(Node n, int depth, boolean maximizingPlayer) {
		if (depth == 0) {
			return n;
		}
		
		if (maximizingPlayer) {
			int maxEval = -10000;
			n.findSuccessors();
			Node best = n.getSuccessors().get(0);
			for (Node child : n.getSuccessors()) {
				Node evalNode = minimaxNode(child, depth-1, false);
				int eval = evalNode.getValue();
				if (eval > maxEval)
					best = child; 
				maxEval = Math.max(maxEval, eval);
				//System.out.println(child);
			}
			return best;
		}else {
			int minEval = 10000;
			n.findSuccessors();
			Node best = n.getSuccessors().get(0);
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
		
		if (maximizingPlayer) {
			int maxEval = -10000;
			n.findSuccessors();
			// find the best possible next node
			Node best = n.getSuccessors().get(0);
			for (Node child : n.getSuccessors()) {
				Node evalNode = minimaxNodeAB(child, depth-1, alpha, beta, false);
				int eval = evalNode.getValue();
				alpha = Math.max(alpha, eval);
	
				if (eval > maxEval)
					best = child; 
				maxEval = Math.max(maxEval, eval);
				if (beta <= alpha) {
					break;
				}
				//System.out.println(child);
			}
			return best;
		}else {
			int minEval = 10000;
			n.findSuccessors();
			Node best = n.getSuccessors().get(0);
			for (Node child : n.getSuccessors()) {
				Node evalNode = minimaxNodeAB(child, depth-1, alpha, beta, true);
				int eval = evalNode.getValue();
				beta = Math.min(beta, eval);
				
				if (eval < minEval)
					best = child;
				minEval = Math.min(minEval, eval);
				
				if (beta <= alpha) {
					//System.out.println("Pruned!");
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
}
