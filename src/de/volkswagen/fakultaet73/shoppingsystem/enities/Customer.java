package de.volkswagen.fakultaet73.shoppingsystem.enities;

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
	 * Constructor for the object "Costumer",
	 * 
	 * @param id 		  ID of the customer
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id; 
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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Kundennummer: " + this.id +"\n");
		sb.append("Vorname: " + this.firstName +"\n");
		sb.append("Nachname: " + this.lastName +"\n");
		sb.append("Adresse: " + this.streetName +" "+ this.houseNumber + "\n\t " +
					this.postalCode + " " + this.city + " " + this.country + "\n");
		return sb.toString();
	}
	
	public String convertToCSVString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append(this.id + ";");
		sb.append(this.firstName + ";");
		sb.append(this.lastName + ";");
		sb.append(this.streetName + ";");
		sb.append(this.houseNumber + ";");
		sb.append(this.postalCode + ";");
		sb.append(this.city + ";");
		sb.append(this.country); 
		return sb.toString();
	}
	

}
