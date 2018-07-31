package ar.com.julian.menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import ar.com.julian.console.Console;

public abstract class Menu {

	protected String title;
	protected String[] options;
	protected String values;

	public char show() {
		try {
			Console.clear();

			System.out.println(title);
			System.out.println();

			for (String option : options) {
				System.out.println(option);
			}

			System.out.println();

			int selected = -1;

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			do {
				System.out.print("Option: ");
				String line = br.readLine();

				if (line.length() == 1) {
					selected = values.indexOf(line);
				}
			} while (selected == -1);

			return values.charAt(selected);
		} catch (Throwable t) {
			throw new IllegalStateException(t);
		}
	}

	public void addValues(String values) {
		this.values = values;
	}

	public void addOptions(String... options) {
		this.options = options;
	}

	public void addTitle(String title) {
		this.title = title;
	}

	public Menu() {
		super();
	}

}
