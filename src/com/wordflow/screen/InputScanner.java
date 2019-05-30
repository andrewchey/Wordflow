package com.wordflow.screen;

import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class InputScanner {

    private static ResourceBundle myBundle = ResourceBundle.getBundle("messenger");
    Scanner scanner = new Scanner(System.in);

    public int InputScanner(String ask, int i) {
        do {
            System.out.print(myBundle.getString(ask));
            try {
                i = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
            } finally {
                scanner.nextLine();
            }
        } while (true);

        return i;

    }

    public String InputScanner(String ask, String s) {
        do {
            System.out.print(myBundle.getString(ask));
            try {
                s = scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
            } finally {
                scanner.nextLine();
            }
        } while (true);

        return s;

    }

    public Boolean ConfirmRemoval(String obj) {
        String s;
        do {
            System.out.print(myBundle.getString("ConfirmRemoval") + obj + "'?");
            try {
                s = scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
            } finally {
                scanner.nextLine();
            }
        } while (true);

        if (s.equals("y")) {
            return true;
        } else {
            return false;
        }
    }

}
