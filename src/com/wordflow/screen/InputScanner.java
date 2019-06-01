package com.wordflow.screen;

import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class InputScanner {

    private static ResourceBundle myBundle = ResourceBundle.getBundle("messenger");
//    Scanner scanner = new Scanner(System.in);

    public int InputScanner(String ask, int i) {

        boolean isCorrectResponse = false;
        Scanner scan = new Scanner(System.in);

        do {
            System.out.print(myBundle.getString(ask));
            if(scan.hasNextInt()){
                i = scan.nextInt();
                isCorrectResponse = true;
            }else{
                scan.nextLine();
                System.out.println(myBundle.getString("InvalidInputError"));
            }
        }while(!isCorrectResponse);

        return i;

    }

    public String InputScanner(String ask, String s) {

        boolean isCorrectResponse = false;
        Scanner scan = new Scanner(System.in);

        do {
            System.out.print(myBundle.getString(ask));
            if(scan.hasNext()){
                s = scan.nextLine();
                isCorrectResponse = true;
            }else{
                scan.nextLine();
                System.out.println(myBundle.getString("InvalidInputError"));
            }
        }while(!isCorrectResponse);

        return s;

    }

    /// NEED FIX
    public Boolean ConfirmRemoval(String obj) {

        Scanner scanner = new Scanner(System.in);
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
