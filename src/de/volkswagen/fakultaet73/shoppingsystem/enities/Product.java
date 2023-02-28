package de.volkswagen.fakultaet73.shoppingsystem.enities;

public class Product {
	private int id;
	private String name;
	private Category category;

	private String description;
	private double price;
	private double taxRateInPercent;

	/**
	 * This is the constructor of the product Class!
	 * 
	 * @param productId          Id number of the product
	 * @param productName        Name of the product
	 * @param productDescription Description of the product
	 * @param productPrice       price of the product
	 * @param taxRateInPercent   tax rate of the product
	 */

	public Product(int productId, String productName, Category category, String productDescription, double productPrice,
			double taxRateInPercent) {
		this.id = productId;
		this.name = productName;
		this.category = category;
		this.description = productDescription;
		this.price = productPrice;
		this.taxRateInPercent = taxRateInPercent;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTaxRateInPercent() {
		return this.taxRateInPercent;
	}

	public void setTaxRateInPercent(double taxRateInPercent) {
		this.taxRateInPercent = taxRateInPercent;
	}

	/**
	 * Calculates the tax Amount with the product full price and the taxAmount in
	 * percent
	 * 
	 * @return the tax amount of one product
	 */
	public double calcTaxAmount() {
		return this.price * this.taxRateInPercent / 100;
	}

	/**
	 * Calculates the tax amount for multiple products with the full product price
	 * and the taxAmount in percent
	 * 
	 * @param amount The amount of selected products
	 * @return the tax amount of all selected products
	 */

	public double calcTaxAmount(int amount) {
		return calcTaxAmount() * amount;
	}

	/**
	 * Calculates the net price without tax for one product
	 * 
	 * @return net price of one product
	 */

	public double calcNetPrice() {
		return this.price - calcTaxAmount();
	}

	/**
	 * Calculates the net price without tax with for the given sets of products
	 * 
	 * @param amount The amount of selected products
	 * @return net Price of all selected products
	 */

	public double calcNetPrice(int amount) {
		return calcNetPrice() * amount;
	}

	/**
	 * This Method calculates the full price with tax for the given sets of products
	 * 
	 * @param amount The amount of selected products
	 * @return full price with tax of all selected products
	 */

	public double calcFullPrice(int amount) {
		return this.price * amount;
	}
	
	public String toString() {
		return String.format("ID: %d - %s - %s - %.2f€ \n%s\n", this.id, this.name, this.category.getGermanTitle(), this.price, this.description);
	}
	
	public String convertToCSVString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append(this.id + ";");
		sb.append(this.name + ";");
		sb.append(this.category.getGermanTitle() + ";");
		sb.append(this.description + ";");
		sb.append(this.price + ";");
		sb.append(""+ this.taxRateInPercent);
		return sb.toString();
	}

}
