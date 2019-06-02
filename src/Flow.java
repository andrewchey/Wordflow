import com.wordflow.note.Note;
import com.wordflow.notemanager.NoteManager;
import com.wordflow.screen.ScreenPrinter;
import com.wordflow.screen.ScreenScanner;
import com.wordflow.word.Word;

import java.util.Scanner;

public class Flow {
    public static void main(String[] args) {
        Flow myWordFlow = new Flow();
        myWordFlow.start();
    }

    private static ScreenPrinter screen = new ScreenPrinter();
    private static ScreenScanner scanner = new ScreenScanner();

    private void start() {
        NoteManager noteManager = new NoteManager();
        startMainMenu(noteManager);
    }

    private void startMainMenu(NoteManager myNoteManager) {
        while (true) {

            // Welcome
            screen.print("Greetings");

            // Print note list
            myNoteManager.printNoteList();

            // Print main menu
            screen.print("MainMenu");

            // Get user input
            int num = scanner.Scan("MenuOptions", 1);

            if (num == 1) {
                // Select the note
                String noteName = scanner.Scan("NoteName", "a");
                // Check whether user input has any matching note name
                if (myNoteManager.existNote(noteName)) {
                    startNoteMenu(myNoteManager.searchNoteByName(noteName));
                } else {
                    screen.print("NoteNameMisMatchError");
                }

            } else if (num == 2) {
                // Get note name from user input
                String noteName = scanner.Scan("NoteName", "a");

                // Create new note
                Note myNote = new Note(noteName);

                // Add new note to the list of notes
                myNoteManager.addNote(myNote);

            } else if (num == 3) {
                // Get note name from user input
                String noteToRemove = scanner.Scan("NoteName", "a");

                // Remove note if the note exists and if confirmed
                if (myNoteManager.existNote(noteToRemove)) {
                    if (scanner.ConfirmRemoval("ConfirmRemoval", noteToRemove)) {
                        myNoteManager.removeNoteByName(noteToRemove);
                    }
                } else {
                    screen.print("NoteNameMisMatchError");
                }
            } else {
                // Quit
                screen.print("Farewell");
                break;
            }

        }

    }

    private void startNoteMenu(Note note) {

        while (true) {

            // Welcome
            screen.printCustomMenu(note.getName());

            // Print word list
            note.printWordList();

            // Print note menu
            screen.print("NoteMenu");

            // Get user input
            int num = scanner.Scan("MenuOptions", 1);


            if (num == 1) {

                // Get word name from user input
                String wordName = scanner.Scan("WordName", "a");

                // Get word definition from user input
                String wordDefinition = scanner.Scan("WordDefinition", "a");

                // Get word tag from user input
                // doSomething

                // Create new word
                Word myWord = new Word(wordName, wordDefinition);

                // Add new word to the list of notes
                note.addWord(myWord);

            } else if (num == 2) {

                // Get word name from user input
                String wordName = scanner.Scan("WordName", "a");

                // Set word if the word exists
                if (note.existWord(wordName)) {

                    // Get new word name from user input
                    String newWordName = scanner.Scan("WordName", "a");

                    // Get new word definition from user input
                    String newWordDefinition = scanner.Scan("WordDefinition", "a");

                    // Get new word tag from user input
                    // doSomething

                    // Replace the word with new word
                    note.searchWordByName(wordName).setName(newWordName);
                    note.searchWordByName(wordName).setDef(newWordDefinition);

                } else {
                    screen.print("WordNameMisMatchError");
                }

            } else if (num == 3) {

                // Get word name from user input
                String wordToRemove = scanner.Scan("WordName", "a");

                // Remove note if the note exists and if confirmed
                if (note.existWord(wordToRemove)) {
                    if (scanner.ConfirmRemoval("ConfirmRemoval", wordToRemove)) {
                        note.removeWordByName(wordToRemove);
                    }
                } else {
                    screen.print("WordNameMisMatchError");
                }

            } else {
                screen.print("ExitToMain");
                break;
            }
        }

    }

}

