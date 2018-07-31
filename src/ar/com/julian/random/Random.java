package ar.com.julian.random;

import java.util.concurrent.ThreadLocalRandom;

public class Random {

	public static int findRandomInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max);
	}

	public static boolean findRandomBoolean() {
		return ThreadLocalRandom.current().nextBoolean();
	}

	private Random() {
		super();
	}

}
