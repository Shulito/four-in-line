package ar.com.julian.ai;

import ar.com.julian.ai.mcts.EasyAi;
import ar.com.julian.ai.mcts.MCTSAi;

public class AiFactory {

	public static Ai of(char option) {
		if (option == '1') {
			return new EasyAi();
		} else if (option == '2') {
			return new MCTSAi(10000);
		} else {
			return new MCTSAi(30000);
		}
	}

	private AiFactory() {
		super();
	}

}
