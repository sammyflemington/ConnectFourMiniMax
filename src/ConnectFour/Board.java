package ConnectFour;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JPanel;

/*
 * Handles storing board info, placing pieces, etc
 */
public class Board extends JPanel{
	private Integer[][] board; // 2d array for storing pieces
	private static int WIDTH = 7;
	private static int HEIGHT = 6;
	private int totalMoves = 0;
	private int currentPlayer = 0;
	
	public Board(int currentPlayer) {
		super();
		board = new Integer[WIDTH][HEIGHT];
		this.currentPlayer = currentPlayer;
	}
	public Board(Board b) {
		super();
		board = new Integer[WIDTH][HEIGHT];
		for(int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				board[i][j] = b.getBoard()[i][j];
			}
		}
		currentPlayer = b.getCurrentPlayer();
	}
	public Board(Integer[][] b, int cp) {
		super();
		board = b;
		currentPlayer = cp;
	}
	public int getCurrentPlayer() {
		return currentPlayer;
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
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	public Integer[][] getBoard(){
		return board.clone();
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
			totalMoves ++;
			//System.out.println(evaluate());
			return true; // success
		}
		return false; // failed to place piece!
	}
	public boolean checkCanPlace(int col) {
		if (board[col][0].equals(0)) {
			return true;
		}
		return false;
	}
	public ArrayList<String> getDiagonalsDownRight() {
		// Time for spaghetti code
		ArrayList<String> data = new ArrayList<String>();
		for (int i = -3; i < 3; i++) {
			String temp = "";
			for (int j = 0; j < 7; j++) { //longest possible diagonal is 6 long
				int row = j;
				int col = j + i;
				if (row >= 0 && row < WIDTH && col >= 0 && col < HEIGHT) {
					temp += board[row][col];
				}
			}
			data.add(temp);
		}
		return data;
	}
	
	public ArrayList<String> getDiagonalsDownLeft() {
		// Time for spaghetti code
		ArrayList<String> data = new ArrayList<String>();
		for (int i = 3; i < 8; i++) {
			String temp = "";
			for (int j = 0; j < 6; j++) { //longest possible diagonal is 6 long
				int row = j;
				int col = -j + i;
				if (row >= 0 && row < WIDTH && col >= 0 && col < HEIGHT) {
					temp += board[row][col];
				}
			}
			data.add(temp);
		}
		return data;
	}
	// Finds value of board for specified player (1 or 2)
	public int evaluate() {
		// https://stackoverflow.com/questions/20802509/java-for-loops-how-to-check-for-winner-in-connect-4-program
		// organize data for easy checking with built-in string functions
		ArrayList<String> data = new ArrayList<String>();
		// First, columns
		for (Integer[] col : board) {
			String colString = "";
			for (Integer row : col) {
				colString += row.toString();
			}
			data.add(colString);
		}
		
		// now rows
		for (int i = 0; i < HEIGHT; i++) {
			String rowString = "";
			for (Integer[] col : board) {
				rowString += col[i];
			}
			data.add(rowString);
		}
		

		data.addAll(getDiagonalsDownRight());

		data.addAll(getDiagonalsDownLeft());
		
		// Now check for four in a row!
		int points = 0;
		// Generate key string
		String key = "";
		for (int i = 0; i < 4; i++) {
			key += Integer.toString(1);
		}
		for (String s : data) {
			if (s.contains(key)) {
				points ++;
			}
		}
		
		// now check opposing player (2)
		key = "";
		for (int i = 0; i < 4; i++) {
			key += Integer.toString(2);
		}
		for (String s : data) {
			if (s.contains(key)) {
				points --;
			}
		}
		return points;
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
	
	public int getTotalMoves() {
		return totalMoves;
	}
	public int getNumCols() {
		return WIDTH;
	}
}
