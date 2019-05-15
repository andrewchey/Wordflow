package com.wordflow.notemanager;

import java.util.ArrayList;

public class NoteManager {

    ArrayList<Note> myNoteList = new ArrayList<>();

    public ArrayList<Note> getMyNoteList() {
        return myNoteList;
    }

    public void setMyNoteList(ArrayList<Note> myNoteList) {
        this.myNoteList = myNoteList;
    }

    public void printNoteList() {
        for (int i = 0; i < myNoteList.size(); i++) {
            System.out.println(" - " + searchNoteByIndex(i).getName());
        }
    }

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
