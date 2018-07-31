package ar.com.julian.menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AnyKeyMenu extends Menu {

	@Override
	public char show() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			br.readLine();

			return ' ';
		} catch (Throwable t) {
			throw new IllegalStateException(t);
		}
	}

}
