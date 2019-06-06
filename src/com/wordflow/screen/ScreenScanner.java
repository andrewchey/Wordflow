package com.wordflow.screen;

import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ScreenScanner {

    private static ResourceBundle myBundle = ResourceBundle.getBundle("messenger");

    // Scanner for integer input
    public int ScanInt(String ask) {

        System.out.print(myBundle.getString(ask));
        Scanner scanner = new Scanner(System.in);

        int result;
        try {
            result = scanner.nextInt();
        } catch(InputMismatchException e) {
            do {
                System.out.print(myBundle.getString(ask));
                scanner.next();
            } while (!scanner.hasNextInt());
            result = scanner.nextInt();
        }

        return result;
    }

    // Scanner for string input
    public String Scan(String ask) {

        System.out.print(myBundle.getString(ask));
        Scanner scanner = new Scanner(System.in);

        String result = scanner.nextLine();

        return result;
    }

    // Scanner for removal confirmation
    public Boolean ConfirmRemoval(String ask, String objectName) {

        Scanner scanner = new Scanner(System.in);

        // Ask user one more time for confirmation
        System.out.print(myBundle.getString("ConfirmRemoval"));

        if (!scanner.hasNext(Pattern.compile("y|n"))) {
            do {
                System.out.print(myBundle.getString(ask));
                scanner.next();
            } while (!scanner.hasNext(Pattern.compile("y|n")));
        }
        return (scanner.nextLine()).equals("y");
    }

}


