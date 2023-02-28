package de.volkswagen.fakultaet73.shoppingsystem.management;

import de.volkswagen.fakultaet73.shoppingsystem.enities.Customer;
import de.volkswagen.fakultaet73.shoppingsystem.utils.Utils;

/**
 * 
 * This Class represents the login system
 * 
 * @author VW5UANH
 *
 */
public class Login {
	private java.util.Scanner sc = new java.util.Scanner(System.in);
	public  Customer loggedInCustomer;

	/**
	 * This is the constructor for the login class without any parameter. The
	 * loggedInCustomer is being set to null
	 */
	public Login() {
		this.loggedInCustomer = null;
	}

	/**
	 * This is the constructor for the login. It has the loggedInCustomer as a
	 * parameter
	 * 
	 * @param loggedInCustomer
	 */
	public Login(Customer loggedInCustomer) {
		this.loggedInCustomer = loggedInCustomer;
	}

	public void setLoggedInCustomer(Customer loggedInCustomer) {
		this.loggedInCustomer = loggedInCustomer;
	}

	public Customer getLoggedInCustomer() {
		return this.loggedInCustomer;
	}

	/**
	 * This methods starts the login/signup process and asks the user if he wants to
	 * signin or signup. If he choose signin it calls the verifyUser method. I he
	 * wants to signup , it class the registerUser method
	 */
	public void start() {
		Utils.printWelcomeArt();
		System.out.println("Wollen Sie sich anmelden oder registrieren?");

		boolean isInputValid = false;
		String input = "";

		do {
			System.out.println("Bitte schreiben Sie:  \"SignIn\" oder \"SignUp\" oder \"Exit\"");
			input = sc.nextLine();

			if (input.equalsIgnoreCase("SIGNUP")) {
				registerUser();
				isInputValid = true;

			} else if (input.equalsIgnoreCase("SIGNIN")) {
				verifyUser();
				isInputValid = true;

			}else if (input.equalsIgnoreCase("EXIT")) {
				loggedInCustomer = null;
				System.exit(0);
				isInputValid = true;
			} else if(input.equalsIgnoreCase("DELETE")) {
				CustomerManagement.deleteUser();
			}else {
				System.out.println("Die Eingabe war ungültig, probieren Sie es bitte erneut");
			}

		} while (!isInputValid);
	}

	/**
	 * This method asks the user for a customerId and validates it using the
	 * doesCustomerExists method form the Utils Class
	 */

	private void verifyUser() {
		int input;

		boolean isInputValid = false;
		do {
			input = Utils.getUserInputAsInt("Bitte geben Sie eine Kundennummer an: ");
			if (CustomerManagement.doesCustomerExists(input)) {
				Utils.clearScreen();
				this.loggedInCustomer = CustomerManagement.findCustomerById(input);
				isInputValid = true;
			} else {
				System.out.println("Bitte geben Sie eine valide Kundennummer an!");
			}
		} while (!isInputValid);
	}

	/**
	 * This Method creates a new customer with data provided by the user. Afterwards
	 * the new customer is being added to the CustomerArray
	 */
	private void registerUser() {
		Utils.clearScreen();

		System.out.println("Bitte geben Sie folgende Daten an: ");
		
		int newId;
		
		do {
		 newId = Utils.generateRandomInt(5); 
		
		if(!CustomerManagement.doesCustomerExists(newId)) {
			Customer newCustomer = new Customer(newId,Utils.getUserInputAsString("Vorname: "),
					Utils.getUserInputAsString("Nachname: "), Utils.getUserInputAsString("Straßenname: "),
					Utils.getUserInputAsInt("Hausnummer: "), Utils.getUserInputAsInt("Postleitzahl: "),
					Utils.getUserInputAsString("Ort: "), Utils.getUserInputAsString("Land: "));

			CustomerManagement.addCustomerToArray(newCustomer);
			this.loggedInCustomer = newCustomer;
			System.out.printf("Deine Kundennummer lautet %s \n", newCustomer.getId());
			CustomerManagement.writeDataToCSVFile();
		}
		}while(CustomerManagement.doesCustomerExists(newId));
	}

}
