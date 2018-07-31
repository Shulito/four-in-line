package ar.com.julian.main;

import ar.com.julian.ai.Ai;
import ar.com.julian.ai.AiFactory;
import ar.com.julian.board.Board;
import ar.com.julian.board.BoardState;
import ar.com.julian.menu.AnyKeyMenu;
import ar.com.julian.menu.ChooseColumnMenu;
import ar.com.julian.menu.DifficultyMenu;
import ar.com.julian.menu.MainMenu;
import ar.com.julian.menu.Menu;
import ar.com.julian.random.Random;

public class Main {

	public static void main(String[] args) {
		Menu main = new MainMenu();
		char option = main.show();

		while (option == '1') {
			// new game
			Menu difficulty = new DifficultyMenu();
			Ai ai = AiFactory.of(difficulty.show());

			boolean isUserTurn = Random.findRandomBoolean();

			Board board = new Board();
			BoardState state = BoardState.ON_GOING;

			// game loop
			while (state == BoardState.ON_GOING) {
				int columnToPlay;

				if (isUserTurn) {
					board.print();

					Menu columns = new ChooseColumnMenu(board.findAvailableColumns());
					columnToPlay = Character.getNumericValue(columns.show()) - 1;
				} else {
					columnToPlay = ai.chooseColumnToPlay(board);
				}

				board.playInColumn(isUserTurn, columnToPlay);
				state = board.checkState();

				if (state == BoardState.ON_GOING) {
					isUserTurn = !isUserTurn;
				}
			}

			// result
			board.print();
			System.out.println();

			if (state == BoardState.DRAW) {
				System.out.println("Draw");
			} else {
				if (isUserTurn) {
					System.out.println("You win!!");
				} else {
					System.out.println("AI wins!!");
				}
			}

			new AnyKeyMenu().show();

			// main menu
			option = main.show();
		}
	}

}
