package de.volkswagen.fakultaet73.versandhandel;

public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private String streetName;
	private int houseNumber;
	private int postalCode;
	private String city;
	private String country;

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
	public Customer(int id, String firstName, String lastName, String streetName, int houseNumber, int postalCode, String city,
			String country) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetName = streetName;
		this.houseNumber = houseNumber;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
	}

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

	private boolean doesCustomerExists(int id) {
		Versandhandel versandhandel = new Versandhandel();
		for (int i = 0; i < versandhandel.customers.length; i++) {
			if (versandhandel.customers[i].getId() == id) {
				return false;
			}
		}
		return true;
	}

	private int generateRandomId() {
		int min = 1;
		int max = 99999;
		int range = max - min + 1;
		return (int) (Math.random() * range) + min;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (!doesCustomerExists(id)) {
			this.id = id;
		} else {
			System.err.println("Diese Kundennummer existiert bereits!");
		}

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
