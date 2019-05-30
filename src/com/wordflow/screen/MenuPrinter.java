package com.wordflow.screen;

import java.util.ResourceBundle;
import java.util.Scanner;

public class Messenger {

    private static ResourceBundle myBundle = ResourceBundle.getBundle("messenger.menu");

    public void printMenu(String key) {

        System.out.println(myBundle.getString(key));

    }

}
