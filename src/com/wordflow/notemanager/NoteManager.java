package com.wordflow.notemanager;

import com.wordflow.note.Note;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class NoteManager {

    private static ResourceBundle myBundle = ResourceBundle.getBundle("messenger");

    // Fields
    private ArrayList<Note> myNoteList = new ArrayList<>();

    // Getter, Setter
    public ArrayList<Note> getMyNoteList() {
        return myNoteList;
    }

    public void setMyNoteList(ArrayList<Note> myNoteList) {
        this.myNoteList = myNoteList;
    }

    public void printNoteList() {
        System.out.println(myBundle.getString("ListNote"));
        for (int i = 0; i < myNoteList.size(); i++) {
            System.out.println(" - " + searchNoteByIndex(i).getName());
        }
    }

    // Methods
    public void addNote(Note note) {
        myNoteList.add(note);
    }

    public int size() {
        return myNoteList.size();
    }

    public Note searchNoteByIndex(int i) {
        return myNoteList.get(i);
    }

    public Note searchNoteByName(String s) {
        for (int i = 0; i < myNoteList.size(); i++) {
            if (myNoteList.get(i).getName().equals(s)) {
                return myNoteList.get(i);
            }
        }
        return null;
    }

    public void removeNoteByName(String s) {
        Note noteToRemove = searchNoteByName(s);
        myNoteList.remove(noteToRemove);
    }

    public void removeNoteByIndex(int i) {
        myNoteList.remove(i);
    }

}
