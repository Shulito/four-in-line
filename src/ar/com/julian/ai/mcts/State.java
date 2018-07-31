package ar.com.julian.ai.mcts;

import java.util.ArrayList;
import java.util.List;

import ar.com.julian.board.Board;
import ar.com.julian.board.BoardState;
import ar.com.julian.random.Random;

public class State {

	private double rewards;
	private long numberOfSimulations;
	private Board board;
	private int playedColumn;
	private State parent;
	private List<State> children;
	private boolean userHasToPlay;

	public State(State parent, Board board, int playedColumn, boolean userHasToPlay) {
		super();

		this.parent = parent;
		this.board = board.clone();
		this.rewards = 0.0;
		this.numberOfSimulations = 0;
		this.playedColumn = playedColumn;
		this.userHasToPlay = userHasToPlay;
		this.children = new ArrayList<>();
	}

	public void expand() {
		if (!children.isEmpty()) {
			return;
		}

		List<Integer> available = board.findAvailableColumns();

		for (Integer i : available) {
			State child = new State(this, board.clone().playInColumn(userHasToPlay, i), i, !userHasToPlay);
			children.add(child);
		}
	}

	public double rollout() {
		Board simulationBoard = board.clone();
		BoardState state = simulationBoard.checkState();

		boolean turnOfUser = userHasToPlay;
		while (state == BoardState.ON_GOING) {
			List<Integer> available = simulationBoard.findAvailableColumns();
			int chosen = available.get(Random.findRandomInt(0, available.size()));

			simulationBoard.playInColumn(turnOfUser, chosen);

			state = simulationBoard.checkState();

			if (state == BoardState.ON_GOING) {
				turnOfUser = !turnOfUser;
			}
		}

		if (state == BoardState.WIN && turnOfUser == false) {
			// won
			return 1;
		} else if (state == BoardState.DRAW) {
			// draw
			return 0.5;
		} else {
			// lost
			return 0;
		}
	}

	public void backprop(double reward) {
		rewards += reward;
		numberOfSimulations++;

		if (parent != null) {
			parent.backprop(reward);
		}
	}

	public List<State> getChildren() {
		return children;
	}

	public State getRandomChild() {
		return children.get(Random.findRandomInt(0, children.size()));
	}

	public double calculateUCB1() {
		if (numberOfSimulations == 0) {
			return Double.MAX_VALUE;
		}

		return rewards / ((double) numberOfSimulations)
				+ Math.sqrt((2 * Math.log(getTotalNumberOfSimulations())) / ((double) numberOfSimulations));
	}

	public long getTotalNumberOfSimulations() {
		if (parent != null) {
			return parent.getTotalNumberOfSimulations();
		}

		return numberOfSimulations;
	}

	public boolean isExpandable() {
		return !children.isEmpty() && board.checkState() == BoardState.ON_GOING;
	}

	public double getAverageRewards() {
		return rewards / (double) numberOfSimulations;
	}

	public long getNumberOfSimulations() {
		return numberOfSimulations;
	}

	public int getPlayedColumn() {
		return playedColumn;
	}

}
