package ar.com.julian.ai.mcts;

import java.util.List;

import ar.com.julian.ai.Ai;
import ar.com.julian.board.Board;
import ar.com.julian.random.Random;

public class EasyAi implements Ai {

	@Override
	public int chooseColumnToPlay(Board board) {
		List<Integer> columns = board.findAvailableColumns();
		int chosen = Random.findRandomInt(0, columns.size());

		return columns.get(chosen);
	}

}
