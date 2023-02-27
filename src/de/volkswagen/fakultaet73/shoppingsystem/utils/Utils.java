package de.volkswagen.fakultaet73.shoppingsystem.utils;

import de.volkswagen.fakultaet73.shoppingsystem.enities.Customer;
import de.volkswagen.fakultaet73.shoppingsystem.enities.Product;
import de.volkswagen.fakultaet73.shoppingsystem.management.CustomerManagement;
import de.volkswagen.fakultaet73.shoppingsystem.management.ProductManagement;

public final class Utils {
	private Utils() {
	}
	
	/**
	 * This functions clears the screen of the terminal by printing 15 linebreaks
	 */
	public static void clearScreen() {
		for (int i = 0; i <= 15; i++) {
			System.out.print("\n");
		}
	}

	
	/**
	 * This Method prints a ascii art with the text "Versandhandel" to the console
	 */
	public static void printWelcomeArt() {
		System.out.println(" __      __                           _ _                     _      _\n"
				+ " \\ \\    / /                          | | |                   | |    | |\n"
				+ "  \\ \\  / /__ _ __ ___  __ _ _ __   __| | |__   __ _ _ __   __| | ___| |\n"
				+ "   \\ \\/ / _ \\ '__/ __|/ _` | '_ \\ / _` | '_ \\ / _` | '_ \\ / _` |/ _ \\ |\n"
				+ "    \\  /  __/ |  \\__ \\ (_| | | | | (_| | | | | (_| | | | | (_| |  __/ |\n"
				+ "     \\/ \\___|_|  |___/\\__,_|_| |_|\\__,_|_| |_|\\__,_|_| |_|\\__,_|\\___|_|\n");

	}

	/**
	 * This Method prints a ascii art with the text "Danke" to the console
	 */
	public static void printThankYourArt() {
		System.out.println("\n" + "  _____              _        \n" + " |  __ \\            | |       \n"
				+ " | |  | | __ _ _ __ | | _____ \n" + " | |  | |/ _` | '_ \\| |/ / _ \\\n"
				+ " | |__| | (_| | | | |   <  __/\n" + " |_____/ \\__,_|_| |_|_|\\_\\___|\n");

	}
	
	/**
	 * This Method gets a user input from a scanner and returns the input. 
	 * Before asking for the input a string is printed to the console
	 * 
	 * @param The String thats being printed before the input
	 * @return the method returns the User Input as a String
	 */

	public static String getUserInputAsString(String text) {
		java.util.Scanner sc = new java.util.Scanner(System.in);
		System.out.print(text);
		return sc.nextLine();
	}

	/**
	 * This Method gets the user input from a scanner and turns it to a Integer. It
	 * also catches a possible NumberFormatException and throws an error.
	 * 
	 * @return The Method returns the input as a Integer
	 */
	public static int getUserInputAsInt() {
		java.util.Scanner sc = new java.util.Scanner(System.in);
		boolean isInputValid = false;
		int input = 0;
		do {
			try {
				input = Integer.parseInt(sc.nextLine());
				isInputValid = true;

			} catch (NumberFormatException nfe) {
				System.err.println("Diese Eingabe war ungültig! Bitte probiere es erneut \n");
			}
		} while (!isInputValid);
		return input;
	}

	/**
	 * This Method gets the user input from a scanner and turns it to a Integer. It
	 * also prints out a text before asking for a input. It also catches a possible
	 * NumberFormatException and throws an error.
	 * 
	 * @param text It requires a String as parameter for the println method
	 * @return The Method returns the input as a Integer
	 */

	public static int getUserInputAsInt(String text) {
		java.util.Scanner sc = new java.util.Scanner(System.in);
		boolean isInputValid = false;
		int input = 0;
		do {
			try {
				System.out.print(text);
				input = Integer.parseInt(sc.nextLine());
				isInputValid = true;

			} catch (NumberFormatException nfe) {
				System.err.println("Diese Eingabe war ungültig! Bitte probiere es erneut \n");
			}
		} while (!isInputValid);
		return input;
	}

	/**
	 * This method gets the user input from a scanner and turns it to a Integer. It
	 * also prints out a text with Placeholders using the printf method before
	 * asking for the input. It also catches a possible NumberFormatException and
	 * throws an error.
	 * 
	 * @param textWithPlaceholder It requires a string for the printf method with
	 *                            placeholders.
	 * @param placeholder         the placeholder is a string thats placed in the
	 *                            text String using the prinf method.
	 * @return The Method returns the input as a Integer.
	 */
	public static int getUserInputAsInt(String textWithPlaceholder, String placeholder) {
		java.util.Scanner sc = new java.util.Scanner(System.in);
		boolean isInputValid = false;
		int input = 0;
		do {
			try {
				System.out.printf(textWithPlaceholder, placeholder);
				input = Integer.parseInt(sc.nextLine());
				isInputValid = true;

			} catch (NumberFormatException nfe) {
				System.err.println("Diese Eingabe war ungültig! Bitte probiere es erneut \n");
			}
		} while (!isInputValid);
		return input;
	}
	/*
	 * Method to generates a random based on the number of digits
	 * @param numberOfDigits number of digits of the number that needs to be generated
	 * @return generated random number
	 */
	public static int generateRandomInt(int numberOfDigits) {
		int min = (int) Math.pow(10, numberOfDigits-1);
		int max = (int) Math.pow(10, numberOfDigits)-1;
		int range = max - min;
		return (int) (Math.random() * range);
	}
	
	public static void setSampleData() {
		ProductManagement.setProducts(new Product[] { 
				new Product(1, "Blaue Rose", "eine Rose in Blau, die ist schön", 4.99, 19.0),
				new Product(2, "Schwarze Rose", "eine Rose in Schwarz, die ist schön", 6.99, 19.0),
				new Product(3, "Bauschaum", "Mega Bauschaum, richtig schaumig", 16.99, 0.0) });
		CustomerManagement.setCustomers(new Customer[] {
				new Customer(1, "Timo", "Köhler", "Steinbreite", 41, 38440, "Wolfsburg", "DE"),
				new Customer(2, "Kevin", "Ochmann", "Isar-Straße", 4, 38120, "Braunschweig", "DE"),
				new Customer(3, "Jonas", "Willke", "Bornheider Weg", 9, 38179, "Schwülper", "DE"),
				new Customer(4, "Giovanni", "Minardi", "Friedrich-Ebert-Straße", 63, 38440, "Wolfsburg", "DE")});

	}
	
	public static String[] extendArray(String[] arr) {
		String[] biggerArray = new String[arr.length + 1];
		for (int i = 0; i < arr.length; i++) {
			biggerArray[i] = arr[i];
		}
		return biggerArray;
	}
	
	public static void importDataFromCSV() {
		CustomerManagement.loadData();
		ProductManagement.loadData();
	}
	
	public static void saveAllData() {
		CustomerManagement.writeDataToCSVFile();
		ProductManagement.writeDataToCSVFile();
	}
		
		


}





