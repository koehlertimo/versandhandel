package de.volkswagen.fakultaet73.versandhandel;

public class Product {
	private int id;
	private String name;
	private String description;
	private double price;
	private double taxRateInPercent;

	public Product(int productId, String productName, String productDescription, double productPrice,
			double taxRateInPercent) {
		this.id = productId;
		this.name = productName;
		this.description = productDescription;
		this.price = productPrice;
		this.taxRateInPercent = taxRateInPercent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTaxRateInPercent() {
		return taxRateInPercent;
	}

	public void setTaxRateInPercent(double taxRateInPercent) {
		this.taxRateInPercent = taxRateInPercent;
	}

	public double calcTaxAmount() {
		return price * taxRateInPercent / 100;
	}

	public double calcTaxAmount(int amount) {
		return calcTaxAmount() * amount;
	}

	public double calcFullPrice() {
		return price + calcTaxAmount();
	}

	public double calcFullPrice(int amount) {
		return calcFullPrice() * amount;
	}

}
