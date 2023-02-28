package de.volkswagen.fakultaet73.shoppingsystem.management;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import de.volkswagen.fakultaet73.shoppingsystem.enities.Category;
import de.volkswagen.fakultaet73.shoppingsystem.enities.Product;
import de.volkswagen.fakultaet73.shoppingsystem.utils.Utils;

public class ProductManagement {
	
	public static Product[] products; 
	
	private ProductManagement() {};
	
	public static Product getProduct(int index) {
		return products[index];
	}
	
	public static void setProducts(Product[] products) {
		ProductManagement.products = products;
	}
	
	public static void writeDataToCSVFile() {
		try(
			BufferedWriter writer = new BufferedWriter(Files.newBufferedWriter(Paths.get("./ressources/products.csv")));
		) {
			writer.write("Id;Name;Category;Description;Price;Taxrate(%)");
			writer.newLine();
			for(Product product: ProductManagement.products) {
				writer.write(product.convertToCSVString());
				if(!(product == ProductManagement.products[ProductManagement.products.length-1])) {
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
				BufferedReader reader = new BufferedReader(Files.newBufferedReader(Paths.get("./ressources/products.csv")));
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
				Utils.setProductSampleData();
				System.err.println("Fehler beim Laden der Produktdaten!\n" +
					"Es wurden Beispieldaten eingefügt!");
			}
		return data;
	}
	
	
	public static void turnCSVArrayToProductsArray(String[] arr) {
		Product[] newProducts = new Product[arr.length-1]; 
		for(int i = 1; i < arr.length; i++) {
			try {
				String[] product = arr[i].split(";"); 
				newProducts[i-1] = new Product(Integer.parseInt(product[0]), product[1], Category.germanNameToEnum(product[2]), product[3],
						 Double.parseDouble(product[4]), Double.parseDouble(product[5]));
				
				
			}catch(NumberFormatException nfe) {
				System.err.println("Die product CSV Datei enthält einen Fehler: " + nfe);
			}
		}
		
		ProductManagement.products = newProducts;
	}
	public static void loadData() {
		try {
			ProductManagement.turnCSVArrayToProductsArray(ProductManagement.readDataFromCSVFile());
		} catch (Exception e) {
		}
	}
}

