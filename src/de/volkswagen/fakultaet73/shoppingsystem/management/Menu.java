package de.volkswagen.fakultaet73.shoppingsystem.management;

import de.volkswagen.fakultaet73.shoppingsystem.enities.Customer;
import de.volkswagen.fakultaet73.shoppingsystem.enities.Product;
import de.volkswagen.fakultaet73.shoppingsystem.enities.ShoppingSystem;
import de.volkswagen.fakultaet73.shoppingsystem.utils.Utils;

public class Menu {
	private java.util.Scanner sc = new java.util.Scanner(System.in);
		
	public void start() {
		Utils.clearScreen();
		System.out.println("\n"
				+ "  __  __                  \n"
				+ " |  \\/  |                 \n"
				+ " | \\  / | ___ _ __  _   _ \n"
				+ " | |\\/| |/ _ \\ '_ \\| | | |\n"
				+ " | |  | |  __/ | | | |_| |\n"
				+ " |_|  |_|\\___|_| |_|\\__,_|\n"
				+ "                          \n"
				+ "                          \n"
				+ "");
		
		System.out.println("Bitte wählen Sie eine Option aus:");
		System.out.println("[1]Produkt auswählen \n[2]Daten bearbeiten \n[3]Ausloggen ");
		
		String input = sc.nextLine();
		boolean isInputValid = false;
		
		do {
			switch (input) {
				case "1": {
					selectProduct();
					isInputValid = true;
					break;
				} case "2": {
					CustomerManagement.showAndChangeUserData();
					isInputValid = true;
					break;
				} case "3": {
					isInputValid = true;
					Utils.saveAllData();
					ShoppingSystem.login.setLoggedInCustomer(null);
					break;
				} default : {
					System.out.println("Diese Eingabe war ungültig, bitte probieren Sie es erneut");
				}
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

	private void selectProduct() {
		Product[] products = ProductManagement.products;
		System.out.println("--- Unsere Produkte ---");
		for (int i = 0; i < products.length; i++) {
			System.out.print(products[i].toString());
			System.out.println(products[i].getDescription());
			System.out.println("\n");
		}

		int input;
		boolean isInputValid = false;
		Integer choosenProductIndex = null;
		do {
			System.out.println(
					"Welches Produkt möchten Sie zum Warenkorb hinzufügen? Bitte geben Sie die ID des Produktes an!");
			input = Utils.getUserInputAsInt();
			for (int i = 0; i < products.length; i++) {
				if (input == products[i].getId()) {
					choosenProductIndex = i;
					isInputValid = true;
				}
			}
			if (choosenProductIndex == null) {
				System.err.println("Die angegebene Produkt ID ist nicht vorhanden, bitte probieren Sie es erneut! \n");
			}
		} while (!isInputValid);
		createAndShowBill(products[choosenProductIndex], getBoughtAmount(products[choosenProductIndex]));
	}
	
	private int getBoughtAmount(Product selectedProduct) {
		return Utils.getUserInputAsInt("Sie haben %s gewählt. \nWie viele wollen Sie kaufen? \n",
				selectedProduct.getName());
	}
	
	/**
	 * This method shows the user the bill with all the informations about the
	 * loggedInUser and the product.
	 * 
	 * @param product it requires a product that the user want to buy
	 * @param amount  it requires the amount that the user want to buy of the
	 *                product
	 */
	private void createAndShowBill(Product product, int amount) {
		Customer loggedInCustomer = ShoppingSystem.login.loggedInCustomer;
		
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
		submitOrder();

	}
	private void submitOrder() {
		boolean isInputValid = false;
		do {
			System.out.println("\n\n Wollen Sie die Bestellung aufgeben? (Ja / Nein)");
			String inputOrderSubmit = sc.nextLine();
			if (inputOrderSubmit.toUpperCase().equals("JA")) {
				Utils.clearScreen();
				Utils.printThankYourArt();
				System.out.println(
						"Vielen Dank für Ihren Einkauf! Wir werden Ihre Bestellung so schnell wie möglich bearbeiten!");
				isInputValid = true;
			} else if (inputOrderSubmit.toUpperCase().equals("NEIN")) {
				Utils.clearScreen();
				System.out.println("Schade, vielleicht wird es ja was beim nächsten mal");
				isInputValid = true;
			} else {
				System.err.println("\n\n Die Eingabe ist ungültig! Bitte geben Sie entweder JA oder NEIN ein!");
			}
		} while (!isInputValid);
		System.out.println("Drücken Sie Enter um ins Menü zurückzukehren!");
		sc.nextLine();
	}
}
