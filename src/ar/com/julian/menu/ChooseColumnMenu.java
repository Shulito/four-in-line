package ar.com.julian.menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class ChooseColumnMenu extends Menu {

	private String values;

	public ChooseColumnMenu(List<Integer> available) {
		super();

		StringBuilder sb = new StringBuilder();
		for (Integer i : available) {
			sb.append(i + 1);
		}

		values = sb.toString();

		addValues(values);
	}

	@Override
	public char show() {
		try {
			System.out.println();

			int selected = -1;

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			do {
				System.out.print("Column (" + values + "): ");
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

}
