package de.volkswagen.fakultaet73.shoppingsystem.main;

import de.volkswagen.fakultaet73.shoppingsystem.enities.ShoppingSystem;

/**
 * This is the Main class
 * 
 * @author Timo Köhler
 * @author Giovanni Minardi
 * @author Kevin Ochmann
 * @author Jonas Willke
 */
public class Main {

	/**
	 * This is the main method of the Main class and starts the Shopping System
	 */

	public static void main(String[] args) {
		ShoppingSystem shop = new ShoppingSystem();
		shop.start();
	}
}

/*
 * Backlog:
 */



//TODO: maybe randomIntGenerator fixen
//TODO: javadoc schreiben
//TODO: static checken
//TODO: convention checken
//TODO: maybe CustomerManagement and ProductManagement non static machen
//TODO: Customer and Product-Array in Shoppingsystem