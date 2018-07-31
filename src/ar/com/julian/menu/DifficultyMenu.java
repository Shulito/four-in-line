package ar.com.julian.menu;

public class DifficultyMenu extends Menu {

	public DifficultyMenu() {
		super();

		addTitle("Select difficulty level");
		addOptions("1: Easy", "2: Medium", "3: Hard");
		addValues("123");
	}

}
