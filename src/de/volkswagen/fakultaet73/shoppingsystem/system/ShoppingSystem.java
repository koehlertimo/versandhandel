package de.volkswagen.fakultaet73.shoppingsystem.system;

import de.volkswagen.fakultaet73.shoppingsystem.customer.Customer;
import de.volkswagen.fakultaet73.shoppingsystem.product.Product;

/**
 * This class represents a Shopping System
 */
public class ShoppingSystem {

	private java.util.Scanner sc = new java.util.Scanner(System.in);

	Customer loggedInCustomer;

	Product[] products = new Product[] { new Product(1, "Blaue Rose", "eine Rose in Blau, die ist schön", 4.99, 19.0),
			new Product(2, "Schwarze Rose", "eine Rose in Schwarz, die ist schön", 6.99, 19.0),
			new Product(3, "Bauschaum", "Mega Bauschaum, richtig schaumig", 16.99, 0.0) };

	public Customer[] customers = new Customer[] {
			new Customer(1, "Timo", "Köhler", "Steinbreite", 41, 38440, "Wolfsburg", "DE"),
			new Customer(2, "Kevin", "Ochmann", "Isar-Straße", 4, 38120, "Braunschweig", "DE"),
			new Customer(3, "Jonas", "Willke", "Bornheider Weg", 9, 38179, "Schwülper", "DE"),
			new Customer(4, "Giovanni", "Minardi", "Friedrich-Ebert-Straße", 63, 38440, "Wolfsburg", "DE")};

	/**
	 * This method start is the starting point of the ShoppingSystem Class and is
	 * being called from the main method.
	 */
	public void start() {

		boolean isProgrammRunning = true;

		while (isProgrammRunning) {
			welcomeScreen();
			verifyUser();
			Product selectedProduct = selectProduct(products);
			int amount = getUserInput("Sie haben %s gewählt. \nWie viele wollen Sie kaufen? \n",
					selectedProduct.getName());
			clearScreen();
			showBill(selectedProduct, amount);
			submitOrder();
			isProgrammRunning = restartProgram();
		}
	}

	/**
	 * This method asks the user if he wants to restart the Application
	 * 
	 * @return The method returns a boolean value if the user wants to restart the
	 *         program
	 */

	private boolean restartProgram() {
		String restartInput;
		boolean isInputValid = false;
		do {
			System.out.println("Wollen Sie das Programm neustarten? Ja / Nein");
			restartInput = sc.nextLine();
			if (restartInput.toUpperCase().equals("JA")) {
				isInputValid = true;
			} else if (restartInput.toUpperCase().equals("NEIN")) {
				isInputValid = true;
				return false;
			} else {
				System.out.println("Fehlerhafte Eingabe, bitte versuchen Sie es erneut!");
			}
		} while (!isInputValid);

		return true;
	}
	
	/**
	 * 
	 * The console is cleared a welcomescreen ascii-Art is shown. Afterwards the
	 * user is asked if he wants to sign un or sign in
	 */
	private void welcomeScreen() {
		clearScreen();
		System.out.println(" __      __                           _ _                     _      _\n"
				+ " \\ \\    / /                          | | |                   | |    | |\n"
				+ "  \\ \\  / /__ _ __ ___  __ _ _ __   __| | |__   __ _ _ __   __| | ___| |\n"
				+ "   \\ \\/ / _ \\ '__/ __|/ _` | '_ \\ / _` | '_ \\ / _` | '_ \\ / _` |/ _ \\ |\n"
				+ "    \\  /  __/ |  \\__ \\ (_| | | | | (_| | | | | (_| | | | | (_| |  __/ |\n"
				+ "     \\/ \\___|_|  |___/\\__,_|_| |_|\\__,_|_| |_|\\__,_|_| |_|\\__,_|\\___|_|\n");

		System.out.println("Wollen Sie sich anmelden oder registrieren?");

		boolean isInputValid = false;
		String input = "";

		do {
			System.out.println("Bitte schreiben Sie:  \"SignIn\" oder \"SignUp\"");
			input = sc.nextLine();

			if (input.toUpperCase().equals("SIGNUP")) {
				registerUser();
				isInputValid = true;

			} else if (input.toUpperCase().equals("SIGNIN")) {
				isInputValid = true;

			} else {
				System.out.println("Die Eingabe war ungültig, probieren Sie es bitte erneut");
			}

		} while (!isInputValid);
	}

	/**
	 * This method shows the user all of the products form the Products array and
	 * let him choose one product
	 * 
	 * @param product The method gets a array with the type of Product to choose
	 *                from
	 * @return The method returns a Product that the user chose
	 */

	private Product selectProduct(Product[] product) {
		System.out.println("--- Unsere Produkte ---");
		for (int i = 0; i < product.length; i++) {
			System.out.printf("ID: %d - %s - %.2f€ \n", product[i].getId(), product[i].getName(),
					product[i].getPrice());
			System.out.println(product[i].getDescription());
			System.out.println("\n");
		}
		
		int input;
		boolean isInputValid = false;
		Integer choosenProductIndex = null;
		do {
			System.out.println(
					"Welches Produkt möchten Sie zum Warenkorb hinzufügen? Bitte geben Sie die ID des Produktes an!");
			input = getUserInput();
			for (int i = 0; i < product.length; i++) {
				if (input == product[i].getId()) {
					choosenProductIndex = i;
					isInputValid = true;
				}
			}
			if (choosenProductIndex == null) {
				System.err.println("Die angegebene Produkt ID ist nicht vorhanden, bitte probieren Sie es erneut! \n");
			}
		} while (!isInputValid);
		return product[choosenProductIndex];
	}
	
	/**
	 * The User gets gets verified by its coustomerId. The user inputs its
	 * CoustomerId. If it is identical to an existing coustomerIds a welcome screen
	 * is shown, else he is asked to put in a valid Id.
	 */

	/**
	 * The Id is compared to all existing Id's in the customer Array
	 * 
	 * @param input Customer ID
	 * @return true if the id is existing in the customer Array otherwise false
	 *
	 */
	private boolean isACustomer(int input) {
		for (int i = 0; i < customers.length; i++) {
			if (customers[i].getId() == input) {
				loggedInCustomer = customers[i];
				return true;
			}
		}
		return false;
	}

	/**
	 * if the customer id is a valid id the screen is cleared and a welcome screen
	 * is shown, else the user is asked to input a valid id
	 */

	private void verifyUser() {
		int input;

		boolean isInputValid = false;
		do {
			input = getUserInput("Bitte geben Sie eine Kundennummer an: ");
			if (isACustomer(input)) {
				clearScreen();
				System.out.println("Herzlichen Willkommen " + loggedInCustomer.getFirstName() + " "
						+ loggedInCustomer.getLastName() + "\n");
				isInputValid = true;
			} else {
				System.out.println("Bitte geben Sie eine valide Kundennummer an!");
			}
		} while (!isInputValid);
	}

	/**
	 * the screen is cleared, the customer array extended by one and the user is
	 * asked to input his customer Data Which is stored in the costumer Array
	 * Afterwards the new customer Id is shown on screen
	 */
	private void registerUser() {
		clearScreen();
		extendCustomerArray();

		System.out.println("Bitte geben Sie folgende Daten an: ");
		customers[customers.length - 1] = new Customer(getUserInputAsString("Vorname: "),
				getUserInputAsString("Nachname: "), getUserInputAsString("Straßenname: "), getUserInput("Hausnummer: "),
				getUserInput("Postleitzahl: "), getUserInputAsString("Ort: "), getUserInputAsString("Land: "));

		System.out.printf("Deine Kundennummer lautet %s \n", customers[customers.length - 1].getId());

	}

	/**
	 * This method extends the global customer array by 1 index
	 */

	private void extendCustomerArray() {
		Customer[] biggerArray = new Customer[customers.length + 1];
		for (int i = 0; i < customers.length; i++) {
			biggerArray[i] = customers[i];
		}
		customers = biggerArray;
	}
	
	/**
	 * This method shows the user the bill with all the informations about the
	 * loggedInUser and the product.
	 * 
	 * @param product it requires a product that the user want to buy
	 * @param amount  it requires the amount that the user want to buy of the
	 *                product
	 */
	private void showBill(Product product, int amount) {
		System.out.printf("\n%s %s \n%s %d \n%d %s \n \n", loggedInCustomer.getFirstName(),
				loggedInCustomer.getLastName(), loggedInCustomer.getStreetName(), loggedInCustomer.getHouseNumber(),
				loggedInCustomer.getPostalCode(), loggedInCustomer.getCity());

		System.out.printf("Danke %s für Ihren Einkaufen! \nFolgende Positionen stellen wir in Rechnung: \n",
				loggedInCustomer.getFirstName());
		System.out
				.println("__________________________________________________________________________________________");
		System.out.println("Pos.\tBeschreibung\t\t\tMenge\tPreis\t\tGesamtpreis");
		System.out.printf("%s\t%s\t\t\t%d\t%.2f€\t\t%.2f€\n", "001", product.getName(), amount, product.getPrice(),
				product.calcFullPrice(amount));
		System.out.println(product.getDescription());
		System.out
				.println("__________________________________________________________________________________________");
		System.out.printf("\t\t\t\t\t\tGesamt netto\t%.2f€\n", (product.calcNetPrice(amount)));
		System.out.printf("\t\t\t\t\t\tUmsatzsteuer\t%.2f€\n", product.calcTaxAmount(amount));
		System.out.println("\t\t\t\t\t\t__________________________________________");
		System.out.printf("\t\t\t\t\t\tGesamt brutto\t%.2f€\n\n", product.calcFullPrice(amount));

	}
	
	/**
	 * This method asks the user if he want to submit the order and mistyping in the
	 * input.
	 */

	private void submitOrder() {
		boolean isInputValid = false;
		do {
			System.out.println("\n\n Wollen Sie die Bestellung aufgeben? (Ja / Nein)");
			String inputOrderSubmit = sc.nextLine();
			if (inputOrderSubmit.toUpperCase().equals("JA")) {
				clearScreen();
				System.out.println("\n" + "  _____              _        \n" + " |  __ \\            | |       \n"
						+ " | |  | | __ _ _ __ | | _____ \n" + " | |  | |/ _` | '_ \\| |/ / _ \\\n"
						+ " | |__| | (_| | | | |   <  __/\n" + " |_____/ \\__,_|_| |_|_|\\_\\___|\n");
				System.out.println(
						"Vielen Dank für Ihren Einkauf! Wir werden Ihre Bestellung so schnell wie möglich bearbeiten!");
				isInputValid = true;
			} else if (inputOrderSubmit.toUpperCase().equals("NEIN")) {
				clearScreen();
				System.out.println("Schade, vielleicht wird es ja was beim nächsten mal");
				isInputValid = true;
			} else {
				System.err.println("\n\n Die Eingabe ist ungültig! Bitte geben Sie entweder JA oder NEIN ein!");
			}
		} while (!isInputValid);

	}

	/**
	 * the user input is stored as a String
	 * 
	 * @param User input as a String
	 * @return the method returns the User Input as a String
	 */

	private String getUserInputAsString(String text) {
		System.out.print(text);
		return sc.nextLine();
	}

	/**
	 * This Method gets the user input from a scanner and turns it to a Integer. It
	 * also catches a possible NumberFormatException and throws an error.
	 * 
	 * @return The Method returns the input as a Integer
	 */
	private int getUserInput() {
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

	private int getUserInput(String text) {
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
	private int getUserInput(String textWithPlaceholder, String placeholder) {
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
	/**
	 * clearing the console by printing 15 empty lines
	 */
	public void clearScreen() {
		for (int i = 0; i <= 15; i++) {
			System.out.print("\n");
		}
	}
}
