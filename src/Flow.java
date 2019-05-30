import com.wordflow.note.Note;
import com.wordflow.notemanager.NoteManager;
import com.wordflow.screen.InputScanner;
import com.wordflow.screen.MenuPrinter;
import com.wordflow.word.Word;

import java.util.Scanner;

public class Flow {
    public static void main(String[] args) {
        Flow myWordFlow = new Flow();
        myWordFlow.start();
    }

    private static MenuPrinter myMessenger = new MenuPrinter();
    private static InputScanner myInputScanner = new InputScanner();

    private void start() {
        NoteManager myNoteManager = new NoteManager();
        startMainMenu(myNoteManager);
    }

    private void startMainMenu(NoteManager myNoteManager) {
        while (true) {

            // Welcome
            myMessenger.printMenu("Greetings");

            // List out notes
            myNoteManager.printNoteList();

            // Print main menu
            myMessenger.printMenu("Main");

            // Get user input
            int num = myInputScanner.InputScanner("Options", 1);

            if (num == 1) {
                // Select the note
                String noteName = myInputScanner.InputScanner("NoteName", "a");
                // Enter the Note Menu
                startNoteMenu(myNoteManager.searchNoteByName(noteName));


            } else if (num == 2) {
                // Select the note
                String noteName = myInputScanner.InputScanner("NoteName", "a");

                // Create new note
                Note myNote = new Note(noteName);

                // Add new note to the list of notes
                myNoteManager.addNote(myNote);

            } else if (num == 3) {
                // Select the note
                String noteToRemove = myInputScanner.InputScanner("NoteName", "a");

                // Remove note if the name matches
                if (myInputScanner.ConfirmRemoval("NoteName")) {
                    try {
                        myNoteManager.removeNoteByName(noteToRemove);
                    } catch (Exception e) {
                        myMessenger.printMenu("NoteNameMisMatchError");
                    }
                }
            } else {
                // Quit
                myMessenger.printMenu("Farewell");
                break;
            }

        }

    }

    private void startNoteMenu(Note myNote) {

        while (true) {

            // Welcome
            System.out.println("====================");
            System.out.println(myNote.getName());
            System.out.println("====================");

            // List out notes here
            System.out.println("List of words: ");
            myNote.printWordList();

            // Main menu
            System.out.println("--------------------");
            System.out.println("Note menu:");
            System.out.println("1: New word");
            System.out.println("2: Delete word");
            System.out.println("3: Quit");
            System.out.println("--------------------");
            System.out.print("(INPUT) Please select your option: ");

            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();

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

