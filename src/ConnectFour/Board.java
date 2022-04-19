package ConnectFour;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;

/*
 * Handles storing board info, placing pieces, etc
 */
public class Board extends JPanel{
	private Integer[][] board; // 2d array for storing pieces
	private static int WIDTH = 7;
	private static int HEIGHT = 6;
	
	public Board() {
		super();
		board = new Integer[WIDTH][HEIGHT];
	}
	
	public void loadFile(String s) {
		try {
			File file = new File(s);
			Scanner reader = new Scanner(file);
			int j = 0;
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				if (line.length() < 5)
					break;
				
				for (int i = 0; i < line.length(); i++) {
					char c = line.charAt(i);
					board[i][j] = Character.getNumericValue(c);
				}
				j++;
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	public boolean dropPiece(int piece, int col) {
		// check if there is room for a piece in this column
		if (board[col][0].equals(0)){
			// OK to drop piece
			int i = HEIGHT - 1;
			while (board[col][i] != 0 && i > 0) {
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
	
	public void paintComponent(Graphics g) {
		// Must call super method to do the preliminary stuffs
		super.paintComponent(g);

		// Recalculate cell size in case the window gets resized
		int cellWidth = Math.round(getWidth() / WIDTH);
		int cellHeight = Math.round(getHeight() / HEIGHT);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// draw cells in correct color
				switch(board[i][j]) {
					case 0: g.setColor(Color.WHITE); break;
					case 1: g.setColor(Color.GREEN); break;
					case 2: g.setColor(Color.RED); break;
					default: g.setColor(Color.BLACK); break;
				}
				// Draw cell
				g.fillOval(i * cellWidth, j * cellHeight, cellWidth, cellHeight);
				// black outline
				g.setColor(Color.BLACK);
				g.drawRect(i * cellWidth, j * cellHeight, cellWidth, cellHeight);
			}
		}
		
		repaint();
	}
	public int getNumCols() {
		return WIDTH;
	}
}
