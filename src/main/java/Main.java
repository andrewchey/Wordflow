import com.sun.org.apache.xalan.internal.xsltc.compiler.FlowList;
import com.wordflow.note.Note;
import com.wordflow.notemanager.NoteManager;
import com.wordflow.screen.ScreenPrinter;
import com.wordflow.screen.ScreenScanner;
import com.wordflow.word.Word;
import com.wordflow.wordnet.WordNet;
import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.dictionary.Dictionary;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws JWNLException {

        Main myWordFlow = new Main();
        myWordFlow.start();

    }

    private static ScreenPrinter screen = new ScreenPrinter();
    private static ScreenScanner scanner = new ScreenScanner();

    private void start() throws JWNLException {
        NoteManager noteManager = new NoteManager();
        Dictionary dictionary = Dictionary.getDefaultResourceInstance();

        startMainMenu(noteManager, dictionary);
    }

    private void startMainMenu(NoteManager myNoteManager, Dictionary dictionary) throws JWNLException {
        while (true) {

            // Welcome
            screen.print("Greetings");

            // Print note list
            myNoteManager.printNoteList();

            // Print main menu
            screen.print("MainMenu");

            // Get user input
            int num = scanner.ScanInt("MenuOptions");

            // Menu Options:
            if (num == 1) {

                // Select the note
                String noteName = scanner.Scan("NoteName");
                // Check whether user input has any matching note name
                if (myNoteManager.existNote(noteName)) {
                    startNoteMenu(myNoteManager.searchNoteByName(noteName), dictionary);
                } else {
                    screen.print("NoteNameMisMatchError");
                }

            } else if (num == 2) {
                // Get note name from user input
                String noteName = scanner.Scan("NoteName");

                // Create new note
                Note myNote = new Note(noteName);

                // Add new note to the list of notes
                myNoteManager.addNote(myNote);

            } else if (num == 3) {
                // Get note name from user input
                String noteToRemove = scanner.Scan("NoteName");

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

    private void startNoteMenu(Note note, Dictionary dictionary) throws JWNLException {

        while (true) {

            // Welcome
            screen.printCustomMenu(note.getName());

            // Print word list
            note.printWordList();

            // Print note menu
            screen.print("NoteMenu");

            // Get user input
            int num = scanner.ScanInt("MenuOptions");

            if (num == 1) {

                // Initialize flowlist
                WordNet.FlowList flowList = new WordNet.FlowList();

                // Get search keyword
                String inputKeyword = scanner.Scan("Keyword");

                // Enter FlowMenu
                startFlowMenu(dictionary, note, flowList, inputKeyword);

            } else if (num == 2) {

                // Get word name from user input
                String wordName = scanner.Scan("WordName");

                // Get word definition from user input
                String wordDefinition = scanner.Scan("WordDefinition");

                // Create new word
                Word myWord = new Word(wordName, wordDefinition);

                // Add new word to the list of notes
                note.addWord(myWord);

            } else if (num == 3) {

                // Get word name from user input
                String wordName = scanner.Scan("WordName");

                // Set word if the word exists
                if (note.existWord(wordName)) {

                    // Get new word name from user input
                    String newWordName = scanner.Scan("WordName");

                    // Get new word definition from user input
                    String newWordDefinition = scanner.Scan("WordDefinition");

                    // Replace the word with new word
                    note.searchWordByName(wordName).setName(newWordName);
                    note.searchWordByName(wordName).setDef(newWordDefinition);

                } else {
                    screen.print("WordNameMisMatchError");
                }

            } else if (num == 4) {

                // Get word name from user input
                String wordToRemove = scanner.Scan("WordName");

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

    private void startFlowMenu(Dictionary dictionary, Note note, WordNet.FlowList flowList, String inputKeyword) throws JWNLException {

        while (true) {

            // Flow
            WordNet.Item item = Flow(dictionary, note, flowList, inputKeyword);

            // Ask to Flow again
            int flowNum = scanner.ScanFlow(item);

            // Search for corresponding word and then Flow again
            if (flowNum > 0) {
                inputKeyword = item.getSynonyms().get(flowNum - 1);
            } else {
                break;
            }

        }

    }

    private WordNet.Item Flow(Dictionary dictionary, Note note, WordNet.FlowList flowList, String input) throws JWNLException {

        // Search
        WordNet.Item item = WordNet.search(dictionary, input);

        // Add to FlowList
        WordNet.FlowItem flowItem = new WordNet.FlowItem(item);
        flowList.add(flowItem);

        // Ask to add to note, and if true, add to note
        boolean isAdd = scanner.Confirm("ConfirmAddToNote");
        if (isAdd) {
            String wordName = item.getWord();
            String wordDefinition = item.getDefinitionString();
            Word word = new Word(wordName, wordDefinition);
            note.addWord(word);
        }

        return item;

    }

}

