package ar.com.julian.ai.mcts;

import ar.com.julian.ai.Ai;
import ar.com.julian.board.Board;

public class MCTSAi implements Ai {

	private int numberOfIterations;

	public MCTSAi(int numberOfIterations) {
		if (numberOfIterations <= 0) {
			throw new IllegalArgumentException("The number of iterations has to be greater than zero");
		}

		this.numberOfIterations = numberOfIterations;
	}

	@Override
	public int chooseColumnToPlay(Board board) {
		State root = new State(null, board, -1, false);

		for (int i = 0; i < numberOfIterations; i++) {
			State current = root;

			while (current.isExpandable()) {
				current = current.getChildren().stream().max((node1, node2) -> {
					return Double.compare(node1.calculateUCB1(), node2.calculateUCB1());
				}).get();
			}

			current.expand();
			current = current.getRandomChild();
			current.backprop(current.rollout());
		}

		State best = root.getChildren().stream().max((node1, node2) -> {
			return Double.compare(node1.getAverageRewards(), node2.getAverageRewards());
		}).get();

		return best.getPlayedColumn();
	}

}
