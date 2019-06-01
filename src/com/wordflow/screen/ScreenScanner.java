package com.wordflow.screen;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ScreenScanner {

    private static ResourceBundle myBundle = ResourceBundle.getBundle("messenger");

    // Scanner for integer input
    public int Scan(String ask, int outputType) {

        System.out.print(myBundle.getString(ask));
        Scanner scanner = new Scanner(System.in);

        try {
            int result = scanner.nextInt();
            return result;
        } catch(Exception e) {
            do {
                askAgainForValidInput(ask, scanner);
            }while(!scanner.hasNextInt());
            int result = scanner.nextInt();
            return result;
        }
    }

    // Scanner for string input
    public String Scan(String ask, String outputType) {

        System.out.print(myBundle.getString(ask));
        Scanner scanner = new Scanner(System.in);

        return scanner.next();
    }

    // Scanner for removal confirmation
    public Boolean ConfirmRemoval(String ask, String objectName) {

        Scanner scanner = new Scanner(System.in);

        // Ask user one more time for confirmation
        System.out.print(myBundle.getString("ConfirmRemoval"));

        if (!scanner.hasNext(Pattern.compile("y|n"))) {
            do {
                askAgainForValidInput(ask, scanner);
            } while (!scanner.hasNext(Pattern.compile("y|n")));
        }
        return (scanner.nextLine()).equals("y");
    }

    private void askAgainForValidInput(String ask, Scanner scanner) {
        System.out.println(myBundle.getString("InvalidInputError"));
        System.out.print(myBundle.getString(ask));
        scanner.nextLine();
    }
}


