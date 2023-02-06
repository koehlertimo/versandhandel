public class Main {


    /**
     * Die Methode ist die Main Methode, die den Ablauf des Programmes steuert!
     */


    /*
    TODO: Funktion das man bei der Kundennummer eingabe die möglichkeit hat sich zu registrieren
    TODO: Bei der Kundennummer eingabe dürfen nur zahlen eingegeben werden // auf input == 5 abfragen? - done
    TODO: Neue Java Docs generiernen und kommentare hinzufügen


   Maybe:
    TODO: Rekrusion entfernen
    TODO: Mehr produkte hinzufügen


     */


    public static boolean[][] customerMatrix = new boolean[89][999];


    public static void main(String[] args) {

        java.util.Scanner sc = new java.util.Scanner(System.in);
        customerMatrix[02][345] = true;
        int roseBlueProductId = 0001;
        String roseBlueProductName = "Rose Blau";
        double roseBlueProductPrice = 5.99;
        String roseBlueProductDescription = "Eine Blaue Rose aus dem Harz für zu viel Geld";
        double roseBlueProductTaxRateInPercent = 7.5;

        boolean isProgrammRunning = true;

        while(isProgrammRunning) {


            //print welcome screen
            welcomeScreen();
            //ask for customerId and welcome customer
            verifyUser();

            System.out.println("Wir habe leider nur noch \"" + roseBlueProductName + "\" verfügbar\n Eine \""
                    + roseBlueProductName + "\" kostet: " + roseBlueProductPrice + "€ Netto \n");

            int blueRoseBoughtAmount = getUserInput("Wie viel wollen Sie kaufen?");

            showBill(roseBlueProductId, blueRoseBoughtAmount, roseBlueProductPrice, roseBlueProductTaxRateInPercent, roseBlueProductName, roseBlueProductDescription);
            submitOrder();


            String restartInput;

            boolean isInputValid = false;

            do {
                System.out.println("Wollen Sie das 123 Programm neustarten? Ja / Nein");
                 restartInput = sc.nextLine();

                if (restartInput.toUpperCase().equals("JA")) {
                    isInputValid = true;

                } else if (restartInput.toUpperCase().equals("NEIN")) {
                    isProgrammRunning = false;
                    isInputValid = true;
                } else {
                    System.out.println("Fehlerhafte Eingabe, bitte versuchen Sie es erneut!");

                }
            }while(!isInputValid);
        }
    }

    /**
     * Die Methode wird dafür genutzt den Input des Nutzers zu bekommen, wie viele Artikel er kaufen möchte!
     */

    public static int getUserInput() {
        boolean isInputValid = false;
        int input = 0;
        do {
            try {
                java.util.Scanner sc = new java.util.Scanner(System.in);
                input = Integer.parseInt(sc.nextLine());
                isInputValid = true;

            } catch (NumberFormatException nfe) {
                System.out.println("Diese Eingabe war falsch! Bitte probiere es erneut \n");
            }
        } while (!isInputValid);
        return input;
    }

    public static int getUserInput(String text) {
        boolean isInputValid = false;
        int input = 0;
        do {
            try {
                java.util.Scanner sc = new java.util.Scanner(System.in);
                System.out.println(text);
                input = Integer.parseInt(sc.nextLine());
                isInputValid = true;

            } catch (NumberFormatException nfe) {
                System.out.println("Diese Eingabe war ungültig! Bitte probiere es erneut \n");
            }
        } while (!isInputValid);
        return input;
    }

    /**
     * Diese Methode wird dafür genutzt die Bestätigung des Nutzers zum Kaufvertrag zu bekommen.
     * Wenn er mit Ja bestätigt bekommt er ein Dankes Screen angezeigt!
     */

    public static void submitOrder() {
        System.out.println("\n\n Wollen Sie die Bestellung aufgeben? (Ja / Nein)");
        java.util.Scanner scanner2 = new java.util.Scanner(System.in);
        String inputOrderSubmit = scanner2.nextLine();
        if (inputOrderSubmit.toUpperCase().equals("JA")) {
            clearScreen();
            System.out.println("\n" +
                    "  _____              _        \n" +
                    " |  __ \\            | |       \n" +
                    " | |  | | __ _ _ __ | | _____ \n" +
                    " | |  | |/ _` | '_ \\| |/ / _ \\\n" +
                    " | |__| | (_| | | | |   <  __/\n" +
                    " |_____/ \\__,_|_| |_|_|\\_\\___|\n");
            System.out.println("Vielen Dank für Ihren Einkauf! Wir werden Ihre Bestellung so schnell wie möglich bearbeiten!");

        } else if (inputOrderSubmit.toUpperCase().equals("NEIN")) {
            clearScreen();
            System.out.println("Schade, vielleicht wird es ja was beim nächsten mal");
        } else {
            System.out.println("\n\n Die Eingabe ist ungültig! Bitte geben Sie entweder JA oder NEIN ein!");
            submitOrder();
        }
    }

    /**
     * Diese Methode wird dafür genutzt die Rechnung darzustellen!
     */
    public static void showBill(int id, int amount, double price, double taxRate, String name, String description) {
        System.out.println("\n\n********************");
        System.out.println("Rechnung:");
        System.out.println("\nID: " + id);
        System.out.println("Artikelbezeichnung: " + name);
        System.out.println("Beschreibung: " + description);
        System.out.println("Einzelpreis: " + price);
        System.out.println("Mehrwertsteuersatz: " + taxRate + "%");
        System.out.println("********************");
        System.out.println("Anzahl: " + amount);
        System.out.println("Mehrwertsteuer: " + calculatePriceWithTax(calculateNetFullPrice(amount, price), taxRate) + "€");
        System.out.println("Gesamtpreis Netto: " + calculateNetFullPrice(amount, price) + "€");
        System.out.println("____________________");
        System.out.println("Gesamtpreis Brutto: " + (Math.round((calculateNetFullPrice(amount, price) + calculatePriceWithTax(calculateNetFullPrice(amount, price), taxRate)) * 100.00) / 100.00) + "€");

    }

    /**
     * Diese Methode wird dafür genutzt den vollen Preis in Netto auszurechnen!
     */
    public static double calculateNetFullPrice(int amount, double price) {
        return Math.round(price * amount * 100.00) / 100.00;
    }


    /**
     * Diese Methode wird dafür gentuzt die Mehrwertsteuer in Euro auszurechnen!
     *
     * @param fullPrice
     * @param taxRate
     * @return
     */
    public static double calculatePriceWithTax(double fullPrice, double taxRate) {
        return Math.round(fullPrice * (taxRate / 100.00) * 100.00) / 100.00;
    }


    /**
     * Diese Methode wird verwendet, um einen Benutzer anhand einer Kundennummer zu verifizieren. Der Benutzer wird aufgefordert, seine Kundennummer einzugeben und die Methode überprüft dann, ob die eingegebene Kundennummer mit der übergebenen Kundennummer übereinstimmt. Wenn die Kundennummer gültig ist, wird dem Benutzer eine Begrüßungsnachricht angezeigt. Andernfalls wird der Benutzer aufgefordert, eine valide Kundennummer einzugeben und die Überprüfung wird erneut durchgeführt.
     */



    public static boolean isACustomer(String input) {

        String[] customerIdInput = splitIdString(input);
        return customerMatrix[Integer.parseInt(customerIdInput[0]) - 10][Integer.parseInt(customerIdInput[1])];

    }


    public static String[] splitIdString(String input) {

        String[] returnArray = new String[2];
        returnArray[0] = input.substring(0, 2);
        returnArray[1] = input.substring(2, 5);
        return returnArray;
    }


    public static void verifyUser() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.println("Bitte geben Sie eine Kundennummer an: ");
        String input = sc.nextLine();


        if (input.length() == 5) {
            if (isACustomer(input)) {
                clearScreen();
                System.out.println("Herzlichen Willkommen " + input);
            } else {
                System.out.println("Bitte geben Sie eine valide Kundennummer an!");
                verifyUser();
            }

        }else {System.out.println("Bitte geben Sie eine valide Kundennummer an!");
            verifyUser();}
    }


    /**
     * Zeigt die Willkommensanzeige an, indem die Konsole zuerst geleert und dann ein ASCII-Art-Text ausgegeben wird.
     */
    public static void welcomeScreen() {
        clearScreen();
        System.out.println(" __      __                           _ _                     _      _\n" +
                " \\ \\    / /                          | | |                   | |    | |\n" +
                "  \\ \\  / /__ _ __ ___  __ _ _ __   __| | |__   __ _ _ __   __| | ___| |\n" +
                "   \\ \\/ / _ \\ '__/ __|/ _` | '_ \\ / _` | '_ \\ / _` | '_ \\ / _` |/ _ \\ |\n" +
                "    \\  /  __/ |  \\__ \\ (_| | | | | (_| | | | | (_| | | | | (_| |  __/ |\n" +
                "     \\/ \\___|_|  |___/\\__,_|_| |_|\\__,_|_| |_|\\__,_|_| |_|\\__,_|\\___|_|\n");


        System.out.println("Wollen Sie sich anmelden oder registrieren?");
        java.util.Scanner sc = new java.util.Scanner(System.in);

        boolean isInputValid = false;
        String input = "";

        do {
            System.out.println("Bitte schreiben Sie:  \"SignIn\" oder \"SignUp\"");
            input = sc.nextLine();

            if (input.toUpperCase().equals("SIGNUP")) {
                registerUser();
                isInputValid = true;

            } else if (input.toUpperCase().equals("SIGNIN")) {
                isInputValid = true;

            } else {
                System.out.println("Die Eingabe war ungültig, probieren Sie es bitte erneut");
            }

        } while (!isInputValid);


    }


    /**
     * Löscht den Inhalt des Konsolenbildschirms, indem 15 Leerzeilen ausgegeben werden.
     */
    public static void clearScreen() {
        for (int i = 0; i <= 15; i++) {
            System.out.print("\n");
        }
    }

    public static void registerUser() {
        int min = 10000;
        int max = 99999;
        int range = max -min +1;
        int randomId = (int) (Math.random()*range)+min;

        String[] splitedId = splitIdString("" + randomId);


        do {
            if (isACustomer("" + randomId)) {
                randomId = (int) (Math.random()*range)+min;
                splitedId = splitIdString("" + randomId);
            } else {
                customerMatrix[Integer.parseInt(splitedId[0]) - 10][Integer.parseInt(splitedId[1])] = true;
                clearScreen();
                System.out.println("Ihre neue Kundennummer ist: " + randomId);
            }
        }while(isACustomer("" + randomId));
    }
}