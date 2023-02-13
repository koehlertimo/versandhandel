package de.volkswagen.fakultaet73.shoppingsystem.main;

import de.volkswagen.fakultaet73.shoppingsystem.system.ShoppingSystem;

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


//TODO: setter löschen ggfs. 
//TODO: Util Klasse erstellen
	//TODO: welcomescreen, clearscreen, randomnumber, inputString, inputInt
// TODO: Auswahl menü erstellen 
	// TODO: Kunde soll sich jederzeit ausloggen können
	// TODO: Kunde soll seine Daten ändern können (toString und Stringbuilder nutzen)
	// TODO: toString methode in Produktauswahl nutzen
//TODO: Kunden und Produkte sollen in csv datei gespeichert und geladen werden!
