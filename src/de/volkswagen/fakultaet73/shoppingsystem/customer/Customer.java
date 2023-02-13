package de.volkswagen.fakultaet73.shoppingsystem.customer;

import de.volkswagen.fakultaet73.shoppingsystem.system.ShoppingSystem;

/* 
 * This class contains constructors and methodes for the Customers Class
 * The object Customer is used to store data from the customers.
 * The data is to decrease the bureaucracy and speed up the whole process and UX.
 */
public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private String streetName;
	private int houseNumber;
	private int postalCode;
	private String city;
	private String country;

	/**
	 * Main Constructor for the object "Costumer",
	 * 
	 * @param firstName   first Name of the customer
	 * @param lastName    last Name of the customer
	 * @param streetName  streetname of the customer
	 * @param houseNumber housenumber of the customer
	 * @param postalCode  postalcode of the customer
	 * @param city        hometown of the customer
	 * @param country     country of the customer
	 */

	public Customer(int id, String firstName, String lastName, String streetName, int houseNumber, int postalCode,
			String city, String country) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetName = streetName;
		this.houseNumber = houseNumber;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
	}

	/**
	 * 2nd Constructor for the object "Costumer",
	 * 
	 * @param id          the unique Customer-ID
	 * @param firstName   first name of the customer
	 * @param lastName    last name of the customer
	 * @param streetName  street name of the customer
	 * @param houseNumber house number of the customer
	 * @param postalCode  postal code of the customer
	 * @param city        home town of the customer
	 * @param country     country of the customer
	 */

	public Customer(String firstName, String lastName, String streetName, int houseNumber, int postalCode, String city,
			String country) {
		this.id = generateCustomerId();
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetName = streetName;
		this.houseNumber = houseNumber;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
	}

	/*
	 * Methode to validate a generated customerID, restarts the process
	 * automatically otherwise
	 * 
	 * @return a valid customerID
	 */
	private int generateCustomerId() {
		int generatedCustomerId = 0;
		do {
			generatedCustomerId = generateRandomId();
			if (doesCustomerExists(generatedCustomerId)) {
				return generatedCustomerId;
			}
		} while (!doesCustomerExists(generatedCustomerId));

		return 0;
	}

	/*
	 * Methode to check that the generated customerID is not in use.
	 * 
	 * @param id the generated to that needs to checked
	 * 
	 * @return false if the ID is not in use otherwise false
	 */
	private boolean doesCustomerExists(int id) {
		ShoppingSystem shop = new ShoppingSystem();
		for (int i = 0; i < shop.customers.length; i++) {
			if (shop.customers[i].getId() == id) {
				return false;
			}
		}
		return true;
	}

	/*
	 * Method to generate a random number between 00001 to 99999
	 * 
	 * @return generated random number
	 */
	private int generateRandomId() {
		int min = 1;
		int max = 99999;
		int range = max - min + 1;
		return (int) (Math.random() * range) + min;
	}

	public int getId() {
		return id;
	}

	/*
	 * Check that the entered ID is not in use, if the ID is in use, "Number exists"
	 * 
	 * @param id the new ID that should be set to the object will be returned on the
	 * terminal if it's not in use, the new ID will be set.
	 */
	public void setId(int id) {
		if (!doesCustomerExists(id)) {
			this.id = id;
		} else {
			System.err.println("Diese Kundennummer existiert bereits!");
		}

	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreetName() {
		return this.streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getHouseNumber() {
		return this.houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public int getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
