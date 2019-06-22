package resources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class InputHelper {

	public static String getInput(String prompt) {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

		System.out.print(prompt);
		System.out.flush();

		try {
			return stdin.readLine();
		} catch (Exception e) {
			return "Error: " + e.getMessage();
		}
	}

	public static double getDoubleInput(String prompt) throws NumberFormatException {
		String input = getInput(prompt);
		return Double.parseDouble(input);

	}

	public static int getIntegerInput(String prompt) throws NumberFormatException {
		String input = getInput(prompt);
		return Integer.parseInt(input);

	}

	public static void processException(SQLException e) {
		System.err.println("Error message: " + e.getMessage());
		System.err.println("Error code: " + e.getErrorCode());
		System.err.println("SQL state: " + e.getSQLState());
	}
}