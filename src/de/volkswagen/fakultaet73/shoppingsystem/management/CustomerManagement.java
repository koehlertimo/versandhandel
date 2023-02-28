package de.volkswagen.fakultaet73.shoppingsystem.management;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import de.volkswagen.fakultaet73.shoppingsystem.enities.*;
import de.volkswagen.fakultaet73.shoppingsystem.utils.Utils;

public class CustomerManagement {

	public static Customer[] customers;

	private CustomerManagement() {
	}

	/*
	 * Methode to validate a generated customerID, restarts the process
	 * automatically otherwise
	 * 
	 * @return a valid customerID
	 */
	private static int generateCustomerId() {
		int generatedCustomerId = 0;
		do {
			generatedCustomerId = Utils.generateRandomInt(5);
			if (doesCustomerExists(generatedCustomerId)) {
				return generatedCustomerId;
			}
		} while (!doesCustomerExists(generatedCustomerId));

		return 0;
	}

	public static Customer[] getCustomers() {
		return customers;
	}

	public static void setCustomers(Customer[] customers) {
		CustomerManagement.customers = customers;
	}

	/*
	 * Method to check that the generated customerID is not in use.
	 * 
	 * @param id the generated to that needs to checked
	 * 
	 * @return false if the ID is not in use otherwise false
	 */
	public static boolean doesCustomerExists(int id) {
		for (int i = 0; i < customers.length; i++) {
			if (customers[i].getId() == id) {
				return true;
			}
		}
		return false;
	}

	/*
	 * This methods checks by an id if a customer exists and returns the customer
	 * object
	 */
	public static Customer findCustomerById(int id) {
		for (int i = 0; i < customers.length; i++) {
			if (customers[i].getId() == id) {
				return customers[i];
			}
		}
		return null;
	}

	public static void addCustomerToArray(Customer customer) {
		Customer[] biggerArray = new Customer[customers.length + 1];
		for (int i = 0; i < customers.length; i++) {
			biggerArray[i] = customers[i];
		}
		customers = biggerArray;
		customers[customers.length - 1] = customer;
	}

	public static void showAndChangeUserData() {
		Customer user = ShoppingSystem.login.getLoggedInCustomer();
		int input;

		do {
			System.out.println(user.toString());
			System.out.println("[0] Beenden \n[1] Vorname \n[2] Nachname \n[3] Straßenname \n[4] Hausnummer \n"
					+ "[5] Postleitzahl \n[6] Stadt \n[7] Land \n");
			input = Utils
					.getUserInputAsInt("Welche Daten wünschen Sie zu verändern? " + "Bitte geben Sie ihre Auswahl an.");

			switch (input) {

			case 0:
				break;
			case 1:
				user.setFirstName(Utils.getUserInputAsString("Vorname: "));
				break;
			case 2:
				user.setLastName(Utils.getUserInputAsString("Nachname: "));
				break;
			case 3:
				user.setStreetName(Utils.getUserInputAsString("Straßenname: "));
				break;
			case 4:
				user.setHouseNumber(Utils.getUserInputAsInt("Hausnummer: "));
				break;
			case 5:
				user.setPostalCode(Utils.getUserInputAsInt("Postleitzahl: "));
				break;
			case 6:
				user.setCity(Utils.getUserInputAsString("Stadt: "));
				break;
			case 7:
				user.setCountry(Utils.getUserInputAsString("Land: "));
				break;
			default:
				System.err.println("Eingabe ist ungültig, versuchen Sie es erneut!");
				break;
			}

		} while (input != 0);
		CustomerManagement.writeDataToCSVFile();
	}
	
	public static void writeDataToCSVFile() {
		try(
			BufferedWriter writer = new BufferedWriter(Files.newBufferedWriter(Paths.get("./ressources/costumers.csv")));
		) {
			writer.write("Id;Firstname;Lastname;Street;Housenumber;Postalcode;City;Country");
			writer.newLine();
			for(Customer customer: CustomerManagement.customers) {
				writer.write(customer.convertToCSVString());
				if(!(customer == CustomerManagement.customers[CustomerManagement.customers.length-1])) {
					writer.newLine();
				}
			}
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String[] readDataFromCSVFile() {
		String[] data = new String[0]; 
		
		try(
				BufferedReader reader = new BufferedReader(Files.newBufferedReader(Paths.get("./ressources/costumers.csv")));
			) {
				boolean isLineValid = true;
				String line;
				while(isLineValid){
					line = reader.readLine(); 
					if(line == null) {
						isLineValid = false;
					}else {
						data = Utils.extendArray(data);
						data[data.length-1] = line;
					}
				}
			} catch (Exception e) {
				Utils.setCustomerSampleData();
				System.err.println("Fehler beim Laden der Kundendaten!\n" +
						"Es wurden Beispieldaten eingefügt!(1)");
			}
		return data;
	}
	
	public static void turnCSVArrayToCustomerArray(String[] arr) {
		Customer[] newCustomers = new Customer[arr.length-1]; 
		for(int i = 1; i < arr.length; i++) {
			try {
				String[] customer = arr[i].split(";"); 
				newCustomers[i-1] = new Customer(Integer.parseInt(customer[0]), customer[1], customer[2],
						customer[3], Integer.parseInt(customer[4]), Integer.parseInt(customer[5]), customer[6], customer[7]);
				
				
			}catch(NumberFormatException nfe) {
				Utils.setCustomerSampleData();
				System.err.println("Fehler beim Laden der Kundendaten!\n" +
						"Es wurden Beispieldaten eingefügt!(2)");
			}
		}
		
		CustomerManagement.customers = newCustomers;
	}
	public static void loadData() {
		try {
			CustomerManagement.turnCSVArrayToCustomerArray(CustomerManagement.readDataFromCSVFile());
		} catch (Exception e){
			Utils.setCustomerSampleData();
			System.err.println("Fehler beim Laden der Kundendaten!\n" +
					"Es wurden Beispieldaten eingefügt!(3)");
		}
	}
	public static void deleteUser(){
		int id = Utils.getUserInputAsInt("Id: ");
		String firstName = Utils.getUserInputAsString("Vorname: ");
		String lastName = Utils.getUserInputAsString("Nachmane: ");
		Customer user = findCustomerById(id);

		if(user == null){
			System.err.println("Der Nutzer konnte nicht gefunden werden!");
			return;
		}
		if (user.getFirstName().equalsIgnoreCase(firstName) && user.getLastName().equalsIgnoreCase(lastName)){
			for (int i=0; i < customers.length; i++){
				if (customers[i] == user){
					deleteUserFromArray(i);
					writeDataToCSVFile();
					loadData();
					System.out.println("Ihr Benutzerkonto wurde erfolgreich gelöscht!");
					return;
				}
			}
		} else {
			System.err.println("Dieser Nutzer konnte nicht gefunden werden!");
		}
	}
	public static void deleteUserFromArray(int index){
		Customer[] newArray = new Customer[customers.length-1];
		customers[index] = customers[customers.length-1];
		for(int i = 0; i< newArray.length; i++){
			newArray[i] = customers[i];
		}
		customers = newArray;
	}

}
