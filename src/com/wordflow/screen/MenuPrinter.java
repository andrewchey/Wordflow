package com.wordflow.screen;

import java.util.ResourceBundle;
import java.util.Scanner;

public class MenuPrinter {

    private static ResourceBundle myBundle = ResourceBundle.getBundle("messenger");

    public void printMenu(String key) {

        System.out.println(myBundle.getString(key));

    }

}
