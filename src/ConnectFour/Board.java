package ConnectFour;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Handles storing board info, placing pieces, etc
 */
public class Board {
	private Integer[][] board; // 2d array for storing pieces
	private static int WIDTH = 7;
	private static int HEIGHT = 6;
	
	public Board() {
		super();
		board = new Integer[WIDTH][HEIGHT];
	}
	
	public void loadFile(String s) throws FileNotFoundException {
		File file = new File(s);
		Scanner reader = new Scanner(file);
		int j = 5;
		while (reader.hasNextLine()) {
			String line = reader.nextLine();
			if (line.length() < 5)
				break;
			
			for (int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				board[i][j] = Character.getNumericValue(c);
			}
			j--;
		}
	}
	
	public boolean dropPiece(int col, int piece) {
		// check if there is room for a piece in this column
		if (board[col][HEIGHT - 1].equals(0)){
			// OK to drop piece
			int i = HEIGHT - 1;
			while (board[col][i] == 0 && i > 0) {
				i--;// move down column until there is a piece or we hit bottom
			}
			// place piece
			board[col][i] = piece;
			return true; // success
		}
		return false; // failed to place piece!
	}
	
	// Finds value of board for specified player (1 or 2)
	public int evaluate(int piece) {
		return -1;
	}
}
