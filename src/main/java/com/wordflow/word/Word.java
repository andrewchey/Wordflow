package com.wordflow.word;

import java.util.ArrayList;

public class Word {

    // Fields
    private int index;
    private String name;
    private String def;
    private ArrayList<String> tag;

    // Constructor
    public Word(String name, String def) {
        this.name = name;
        this.def = def;
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

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public ArrayList<String> getTag() {
        return tag;
    }

    public void setTag(ArrayList<String> tag) {
        this.tag = tag;
    }

}
