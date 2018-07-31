package ar.com.julian.console;

public class Console {

	public static void clear() {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033[H\033[2J");
			}
		} catch (final Exception e) {
			// nothing
		}
	}

	private Console() {
		super();
	}

}
