package com.wordflow.note;

import com.wordflow.word.Word;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class Note {

    private static ResourceBundle myBundle = ResourceBundle.getBundle("messenger");

    // Fields
    private int index;
    private String name;
    private ArrayList<Word> myWordList = new ArrayList<>();

    // Constructor
    public Note(String name) {
        this.name = name;
    }

    // Getter, Setter
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Methods
    // Print all words in a note {}
    public void printWordList() {
        System.out.println(myBundle.getString("ListWord"));
        for (int i = 0; i < myWordList.size(); i++) {
            System.out.println(" - " + searchWordByIndex(i).getName() + ": " + searchWordByIndex(i).getDef());
        }
    }

    public void addWord(Word word) {
        myWordList.add(word);
    }

    public int size() {
        return myWordList.size();
    }

    public Word searchWordByIndex(int i) {
        return myWordList.get(i);
    }

    public Word searchWordByName(String s) {
        for (int i = 0; i < myWordList.size(); i++) {
            if (myWordList.get(i).getName().equals(s)) {
                return myWordList.get(i);
            }
        }
        return null;
    }

    public void removeWordByName(String s) {
        Word wordToRemove = searchWordByName(s);
        myWordList.remove(wordToRemove);
    }

    public void removeNoteByIndex(int i) {
        myWordList.remove(i);
    }

    public boolean existWord(String s) { if (searchWordByName(s) == null) {return false;} else {return true;} }

}
