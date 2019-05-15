package com.wordflow.notemanager;

import java.util.ArrayList;

public class Note {
    private int index;
    private String name;
    ArrayList<Word> myWordList = new ArrayList<>();

    public Note(String name) {
        this.name = name;
    }

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

    public void printWordList() {
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

}
