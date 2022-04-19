package ConnectFour;

import java.util.ArrayList;

public class MinMax {
	Node start;
	public MinMax(Board b, int nextPlayer) {
		
		start = new Node(b,nextPlayer);
	}
	
	public int minimax(Node n, int depth, boolean maximizingPlayer) {
		if (depth == 0) {
			return n.getBoard().evaluate();
		}
		
		if (maximizingPlayer) {
			int maxEval = -10000;
			n.findSuccessors(depth, 1);
			for (Node child : n.getSuccessors()) {
				int eval = minimax(child, depth - 1, false);
				maxEval = Math.max(maxEval, eval);
			}
			return maxEval;
		}else {
			int minEval = 10000;
			n.findSuccessors(depth, 2);
			for (Node child : n.getSuccessors()) {
				int eval = minimax(child, depth - 1, true);
				minEval = Math.min(minEval, eval);
			}
			return minEval;
		}
	}
}
