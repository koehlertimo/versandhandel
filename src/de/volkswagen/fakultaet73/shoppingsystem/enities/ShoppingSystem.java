package de.volkswagen.fakultaet73.shoppingsystem.enities;

import de.volkswagen.fakultaet73.shoppingsystem.management.CustomerManagement;
import de.volkswagen.fakultaet73.shoppingsystem.management.Login;
import de.volkswagen.fakultaet73.shoppingsystem.management.Menu;
import de.volkswagen.fakultaet73.shoppingsystem.management.ProductManagement;
import de.volkswagen.fakultaet73.shoppingsystem.utils.Utils;	

/**
 * This class represents a Shopping System
 */
public class ShoppingSystem {
	public static boolean isProgrammRunning = true;

	private java.util.Scanner sc = new java.util.Scanner(System.in);
	
	public static Login login = new Login();
	/**
	 * This method start is the starting point of the ShoppingSystem Class and is
	 * being called from the main method.
	 */
	public void start() {
		Utils.importDataFromCSV();
		
		Menu menu = new Menu();
		
		while (isProgrammRunning) {
			login.start();
			do {
				menu.start();
			}while(login.getLoggedInCustomer() != null);
		}

	}
}