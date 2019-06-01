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
                // Select the note
                String noteName = scanner.Scan("NoteName", "a");

                // Create new note
                Note myNote = new Note(noteName);

                // Add new note to the list of notes
                myNoteManager.addNote(myNote);

            } else if (num == 3) {
                // Select the note
                String noteToRemove = scanner.Scan("NoteName", "a");

                // Remove note if the note exists.
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

    private void startNoteMenu(Note myNote) {

        while (true) {

            // Welcome
            screen.printCustomMenu(myNote.getName());

            // Print word list
            myNote.printWordList();

            // Print note menu
            screen.print("NoteMenu");

            // Get user input
            int num = scanner.Scan("MenuOptions", 1);


            if (num == 1) {
                // Confirm selection
                System.out.println("Note menu: New word selected.");

                // Get word name from user input
                System.out.print("(INPUT) Enter new word name: ");
                Scanner scanner1 = new Scanner(System.in);
                String wordName = scanner1.nextLine();

                // Get word definition from user input
                System.out.print("(INPUT) Enter the new word's definition: ");
                Scanner scanner2 = new Scanner(System.in);
                String wordDef = scanner2.nextLine();

                // Get word tag from user input
                // doSomething

                // Create new word
                Word myWord = new Word(wordName, wordDef);
                String resultNewNote = "Successfully created a new note named '" + wordName + "': " + wordDef + "\n";
                System.out.println(resultNewNote);

                // Add new word to the list of notes
                myNote.addWord(myWord);

            } else if (num == 2) {
                System.out.println("Enter the name of the word to be deleted: ");
                Scanner scanner1 = new Scanner(System.in);
                String wordToRemove = scanner1.nextLine();

                System.out.print("(INPUT) Are you sure you want to delete word: '" + wordToRemove + "'? (y/n)");
                Scanner scanner2 = new Scanner(System.in);
                String response = scanner2.nextLine();
                if (response.equals("y")) {
                    // Remove word
                    try {
                        myNote.removeWordByName(wordToRemove);
                        System.out.println("Successfully removed word: '" + wordToRemove + "'.");
                    } catch (Exception e) {
                        System.out.println("[ERROR] There is no word called '" + wordToRemove + "'.");
                    }
                } else {
                    continue;
                }
            } else {
                System.out.println("Exiting '" + myNote.getName() + "'...");
                break;
            }
        }

    }

}

