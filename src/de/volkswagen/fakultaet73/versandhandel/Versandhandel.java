package de.volkswagen.fakultaet73.versandhandel;

public class Versandhandel {

	java.util.Scanner sc = new java.util.Scanner(System.in);

	Customer loggedInCustomer;

	Product[] products = new Product[] { new Product(1, "Blaue Rose", "eine Rose in Blau, die ist schön", 4.99, 19.0),
			new Product(2, "Schwarze Rose", "eine Rose in Schwarz, die ist schön", 6.99, 19.0),
			new Product(3, "Bauschaum", "Mega Bauschaum, richtig schaumig", 16.99, 7.5) };

	Customer[] customers = new Customer[] {
			new Customer(1, "Timo", "Köhler", "Steinbreite", 41, 38440, "Wolfsburg", "DE"),
			new Customer(2, "Kevin", "Ochmann", "Isar-Straße", 4, 38120, "Braunschweig", "DE"),
			new Customer(3, "Jonas", "Willke", "Bornheider Weg", 9, 38179, "Schwülper", "DE"),
			new Customer(4, "Giovanni", "Minardi", "Friedrich-Ebert-Straße", 63, 38440, "Wolfsburg", "DE") };

	public void start() {

		boolean isProgrammRunning = true;

		while (isProgrammRunning) {

			// print welcome screen
			welcomeScreen();
			// ask for customerId and welcome customer
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

	public boolean restartProgram() {

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

	public Product selectProduct(Product[] product) {
		System.out.println("--- Unsere Produkte ---");
		for (int i = 0; i < product.length; i++) {
			System.out.printf("ID: %d - %s - %.2f€ \n", product[i].getId(), product[i].getName(),
					product[i].calcFullPrice());
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

	public int getUserInput() {
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

	public int getUserInput(String text) {
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

	public int getUserInput(String textWithPlaceholder, String product) {
		boolean isInputValid = false;
		int input = 0;
		do {
			try {
				System.out.printf(textWithPlaceholder, product);
				input = Integer.parseInt(sc.nextLine());
				isInputValid = true;

			} catch (NumberFormatException nfe) {
				System.err.println("Diese Eingabe war ungültig! Bitte probiere es erneut \n");
			}
		} while (!isInputValid);
		return input;
	}

	/**
	 * Diese Methode wird dafür genutzt die Bestätigung des Nutzers zum Kaufvertrag
	 * zu bekommen. Wenn er mit Ja bestätigt bekommt er ein Dankes Screen angezeigt!
	 */

	public void submitOrder() {
		System.out.println("\n\n Wollen Sie die Bestellung aufgeben? (Ja / Nein)");
		String inputOrderSubmit = sc.nextLine();
		if (inputOrderSubmit.toUpperCase().equals("JA")) {
			clearScreen();
			System.out.println("\n" + "  _____              _        \n" + " |  __ \\            | |       \n"
					+ " | |  | | __ _ _ __ | | _____ \n" + " | |  | |/ _` | '_ \\| |/ / _ \\\n"
					+ " | |__| | (_| | | | |   <  __/\n" + " |_____/ \\__,_|_| |_|_|\\_\\___|\n");
			System.out.println(
					"Vielen Dank für Ihren Einkauf! Wir werden Ihre Bestellung so schnell wie möglich bearbeiten!");

		} else if (inputOrderSubmit.toUpperCase().equals("NEIN")) {
			clearScreen();
			System.out.println("Schade, vielleicht wird es ja was beim nächsten mal");
		} else {
			System.out.println("\n\n Die Eingabe ist ungültig! Bitte geben Sie entweder JA oder NEIN ein!");
			submitOrder();
		}
	}

	/**
	 * Diese Methode wird dafür genutzt die Rechnung darzustellen!
	 */
	public void showBill(Product product, int amount) {
		System.out.printf("\n%s %s \n%s %d \n%d %s \n \n", loggedInCustomer.getFirstName(),
				loggedInCustomer.getLastName(), loggedInCustomer.getStreetName(), loggedInCustomer.getHouseNumber(),
				loggedInCustomer.getPostalCode(), loggedInCustomer.getCity());

		System.out.printf("Danke %s für Ihren Einkaufen! \nFolgende Positionen stellen wir in Rechnung: \n",
				loggedInCustomer.getFirstName());
		System.out
				.println("__________________________________________________________________________________________");
		System.out.println("Pos.\tBeschreibung\t\t\tMenge\tPreis\t\tGesamtpreis");
		System.out.printf("%s\t%s\t\t\t%d\t%.2f€\t\t%.2f€\n", "001", product.getName(), amount, product.calcFullPrice(),
				product.calcFullPrice(amount));
		System.out.println(product.getDescription());
		System.out
				.println("__________________________________________________________________________________________");
		System.out.printf("\t\t\t\t\t\tGesamt netto\t%.2f€\n", (product.getPrice() * amount));
		System.out.printf("\t\t\t\t\t\tUmsatzsteuer\t%.2f€\n", product.calcTaxAmount(amount));
		System.out.println("\t\t\t\t\t\t__________________________________________");
		System.out.printf("\t\t\t\t\t\tGesamt brutto\t%.2f€\n\n", product.calcFullPrice(amount));

	}

	/**
	 * Diese Methode wird verwendet, um einen Benutzer anhand einer Kundennummer zu
	 * verifizieren. Der Benutzer wird aufgefordert, seine Kundennummer einzugeben
	 * und die Methode überprüft dann, ob die eingegebene Kundennummer mit der
	 * übergebenen Kundennummer übereinstimmt. Wenn die Kundennummer gültig ist,
	 * wird dem Benutzer eine Begrüßungsnachricht angezeigt. Andernfalls wird der
	 * Benutzer aufgefordert, eine valide Kundennummer einzugeben und die
	 * Überprüfung wird erneut durchgeführt.
	 */

	public boolean isACustomer(int input) {
		for (int i = 0; i < customers.length; i++) {
			if (customers[i].getId() == input) {
				loggedInCustomer = customers[i];
				return true;
			}
		}
		return false;
	}

	public void verifyUser() {
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

	public void registerUser() {
		clearScreen();
		extendCustomerArray();

		System.out.println("Bitte geben Sie folgende Daten an: ");
		customers[customers.length - 1] = new Customer(getUserInputAsString("Vorname: "),
				getUserInputAsString("Nachname: "), getUserInputAsString("Straßenname: "), getUserInput("Hausnummer: "),
				getUserInput("Postleitzahl: "), getUserInputAsString("Ort: "), getUserInputAsString("Land: "));

		System.out.printf("Deine Kundennummer lautet %s \n", customers[customers.length - 1].getId());

	}

	public void extendCustomerArray() {
		Customer[] biggerArray = new Customer[customers.length + 1];
		for (int i = 0; i < customers.length; i++) {
			biggerArray[i] = customers[i];
		}
		customers = biggerArray;
	}

	public String getUserInputAsString(String text) {
		System.out.print(text);
		return sc.nextLine();
	}

	/**
	 * Zeigt die Willkommensanzeige an, indem die Konsole zuerst geleert und dann
	 * ein ASCII-Art-Text ausgegeben wird.
	 */
	public void welcomeScreen() {
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
	 * Löscht den Inhalt des Konsolenbildschirms, indem 15 Leerzeilen ausgegeben
	 * werden.
	 */
	public void clearScreen() {
		for (int i = 0; i <= 15; i++) {
			System.out.print("\n");
		}
	}
}
