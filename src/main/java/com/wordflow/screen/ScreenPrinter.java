package com.wordflow.screen;

import com.wordflow.wordnet.WordNet;

import java.util.ResourceBundle;
import java.util.Scanner;

public class ScreenPrinter {

    private static ResourceBundle myBundle = ResourceBundle.getBundle("messenger");

    public void print(String key) {
        System.out.println(myBundle.getString(key));
    }

    public void printCustomMenu(String s) {
        System.out.println("====================\n" + s + "\n====================");
    }

    public void printFlow(WordNet.Item item) {
        print("Flow");
        System.out.println(item.getSynonyms());
    }
}
