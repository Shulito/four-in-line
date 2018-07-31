package ar.com.julian.board;

import java.util.ArrayList;
import java.util.List;

import ar.com.julian.console.Console;

public class Board {

	private char[][] positions;

	public Board() {
		positions = new char[6][7];
		fill(' ');
	}

	public BoardState checkState() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (wins(i, j)) {
					return BoardState.WIN;
				}
			}
		}

		return findAvailableColumns().size() > 0 ? BoardState.ON_GOING : BoardState.DRAW;
	}

	public Board playInColumn(boolean isUser, int column) {
		for (int i = 5; i >= 0; i--) {
			if (positions[i][column] == ' ') {
				if (isUser) {
					positions[i][column] = 'X';
				} else {
					positions[i][column] = 'O';
				}

				break;
			}
		}

		return this;
	}

	private boolean wins(int row, int column) {
		char toCheck = positions[row][column];

		if (toCheck == ' ') {
			return false;
		}

		// rows
		if (isPositionChar(row - 3, column, toCheck) && isPositionChar(row - 2, column, toCheck)
				&& isPositionChar(row - 1, column, toCheck) && isPositionChar(row, column, toCheck)
				|| isPositionChar(row - 2, column, toCheck) && isPositionChar(row - 1, column, toCheck)
						&& isPositionChar(row, column, toCheck) && isPositionChar(row + 1, column, toCheck)
				|| isPositionChar(row - 1, column, toCheck) && isPositionChar(row, column, toCheck)
						&& isPositionChar(row + 1, column, toCheck) && isPositionChar(row + 2, column, toCheck)
				|| isPositionChar(row, column, toCheck) && isPositionChar(row + 1, column, toCheck)
						&& isPositionChar(row + 2, column, toCheck) && isPositionChar(row + 3, column, toCheck)) {

			return true;
		}

		// colums
		if (isPositionChar(row, column - 3, toCheck) && isPositionChar(row, column - 2, toCheck)
				&& isPositionChar(row, column - 1, toCheck) && isPositionChar(row, column, toCheck)
				|| isPositionChar(row, column - 2, toCheck) && isPositionChar(row, column - 1, toCheck)
						&& isPositionChar(row, column, toCheck) && isPositionChar(row, column + 1, toCheck)
				|| isPositionChar(row, column - 1, toCheck) && isPositionChar(row, column, toCheck)
						&& isPositionChar(row, column + 1, toCheck) && isPositionChar(row, column + 2, toCheck)
				|| isPositionChar(row, column, toCheck) && isPositionChar(row, column + 1, toCheck)
						&& isPositionChar(row, column + 2, toCheck) && isPositionChar(row, column + 3, toCheck)) {

			return true;
		}

		// diagonal left
		if (isPositionChar(row - 3, column - 3, toCheck) && isPositionChar(row - 2, column - 2, toCheck)
				&& isPositionChar(row - 1, column - 1, toCheck) && isPositionChar(row, column, toCheck)
				|| isPositionChar(row - 2, column - 2, toCheck) && isPositionChar(row - 1, column - 1, toCheck)
						&& isPositionChar(row, column, toCheck) && isPositionChar(row + 1, column + 1, toCheck)
				|| isPositionChar(row - 1, column - 1, toCheck) && isPositionChar(row, column, toCheck)
						&& isPositionChar(row + 1, column + 1, toCheck) && isPositionChar(row + 2, column + 2, toCheck)
				|| isPositionChar(row, column, toCheck) && isPositionChar(row + 1, column + 1, toCheck)
						&& isPositionChar(row + 2, column + 2, toCheck)
						&& isPositionChar(row + 3, column + 3, toCheck)) {

			return true;
		}

		// diagonal right
		if (isPositionChar(row + 3, column - 3, toCheck) && isPositionChar(row + 2, column - 2, toCheck)
				&& isPositionChar(row + 1, column - 1, toCheck) && isPositionChar(row, column, toCheck)
				|| isPositionChar(row + 2, column - 2, toCheck) && isPositionChar(row + 1, column - 1, toCheck)
						&& isPositionChar(row, column, toCheck) && isPositionChar(row - 1, column + 1, toCheck)
				|| isPositionChar(row + 1, column - 1, toCheck) && isPositionChar(row, column, toCheck)
						&& isPositionChar(row - 1, column + 1, toCheck) && isPositionChar(row - 2, column + 2, toCheck)
				|| isPositionChar(row, column, toCheck) && isPositionChar(row - 1, column + 1, toCheck)
						&& isPositionChar(row - 2, column + 2, toCheck)
						&& isPositionChar(row - 3, column + 3, toCheck)) {

			return true;
		}

		return false;
	}

	public boolean isPositionChar(int row, int column, char toCheck) {
		if (row < 0 || row > 5 || column < 0 || column > 6) {
			return false;
		}

		return positions[row][column] == toCheck;
	}

	public List<Integer> findAvailableColumns() {
		List<Integer> available = new ArrayList<>(7);

		for (int i = 0; i < 7; i++) {
			if (isColumnAvailable(i)) {
				available.add(i);
			}
		}

		return available;
	}

	private boolean isColumnAvailable(int column) {
		for (int i = 5; i >= 0; i--) {
			if (positions[i][column] == ' ') {
				return true;
			}
		}

		return false;
	}

	public void addChar(int column, char c) {
		for (int i = 5; i >= 0; i--) {
			if (positions[i][column] == ' ') {
				positions[i][column] = c;
				break;
			}
		}
	}

	private void fill(char toFill) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				positions[i][j] = toFill;
			}
		}
	}

	public void print() {
		Console.clear();

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(positions[i][j]);

				if (j == 6) {
					System.out.println();
				}
			}
		}

		System.out.println("-------");
	}

	public Board clone() {
		Board clone = new Board();

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				clone.positions[i][j] = positions[i][j];
			}
		}

		return clone;
	}

}
