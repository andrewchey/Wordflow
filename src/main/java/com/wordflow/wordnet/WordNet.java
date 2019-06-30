package com.wordflow.wordnet;

import com.wordflow.screen.ScreenPrinter;
import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.*;
import net.sf.extjwnl.dictionary.Dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WordNet {

    private static ScreenPrinter screen = new ScreenPrinter();

    public static class Item {

        String word;
        ArrayList<String> definitions;
        ArrayList<String> synonyms;

        public Item(String word, ArrayList<String> definitions, ArrayList<String> synonyms) {
            this.word = word;
            this.definitions = definitions;
            this.synonyms = synonyms;
        }

        public String getWord() {
            return word;
        }

        public String getDefinitionString() {
            String definitionString = "";
            for (int i = 0; i < definitions.size(); i++) {
                definitionString += " " + (i + 1) + "." + definitions.get(i);
            }
            return definitionString;
        }

        public ArrayList<String> getDefinitions() {
            return definitions;
        }

        public ArrayList<String> getSynonyms() {
            return synonyms;
        }

    }


    public static class FlowItem {
        Item item;
        FlowItem nextItem;
        FlowItem prevItem;

        public FlowItem(Item item) {
            this.item = item;
        }
    }

    public static class FlowList {

        FlowItem headerItem;

        public void add(FlowItem newItem) {

            // check if header exists
            if (headerItem == null) {
                headerItem = newItem;
            } else {
                // cursor setup (currentItem)
                FlowItem currentItem = headerItem;
                // search for last item
                while (currentItem.nextItem != null) {
                    currentItem = currentItem.nextItem;
                }
                // set new item and its prev item
                currentItem.nextItem = newItem;
                newItem.prevItem = currentItem;
            }
        }

        public void remove(FlowItem removeItem) {
            // check if header exists
            if (headerItem == removeItem) {
                headerItem = null;
            } else {
                // cursor setup (currentItem)
                FlowItem currentItem = headerItem;
                // search for remove item
                while (currentItem != removeItem) {
                    currentItem = currentItem.nextItem;
                }
                if (currentItem.nextItem == null) {
                    // 1 <> 2
                    // 1
                    currentItem.prevItem.nextItem = null;
                } else {
                    // 1 <> 2 <> 3
                    //    1 <> 3
                    currentItem.prevItem.nextItem = currentItem.nextItem;
                    currentItem.nextItem.prevItem = currentItem.prevItem;
                }
            }
        }

        public Item getItem(int i) {
            if (i < size()) {
                FlowItem currentItem = headerItem;
                for (int j = 0; j < i; j++) {
                    currentItem = currentItem.nextItem;
                }
                return currentItem.item;
            } else {
                return null;
            }
        }

        public int size() {
            int length = 0;
            FlowItem currentItem = headerItem;
            while (currentItem != null) {
                length ++;
                currentItem = currentItem.nextItem;
            }
            return length;
        }
    }

    public static Item search(Dictionary d, String keyword) throws JWNLException {

        // Initialize
        ArrayList<String> resultArray = new ArrayList<String>();
        ArrayList<String> definitions = new ArrayList<String>();

        // Get senses (Synsets) by looking up all POS
        IndexWordSet indexWordSet = d.lookupAllIndexWords(keyword);

        // Print word
        screen.print("line");
        System.out.println(indexWordSet.getLemma());
        screen.print("line");

        // Get senses from each IndexWord and save to synsetArrayList
        ArrayList<Synset> synsetArrayList = new ArrayList<Synset>();
        for (int i = 0; i < indexWordSet.getIndexWordArray().length; i++) {

            IndexWord indexWord = indexWordSet.getIndexWordArray()[i];

            // Sort according to Use Count
            indexWord.sortSenses();

            // Cap upto three Senses per POS
            Integer cap;
            if (indexWord.getSenses().size() > 3) {cap = 3;}
            else {cap = indexWord.getSenses().size();}

            // Save all Senses to synsetArrayList
            for (int j = 0; j < cap; j++) {
                Synset synset = indexWordSet.getIndexWordArray()[i].getSenses().get(j);
                synsetArrayList.add(synset);
            }
        }

        Integer count = 0;
        for (int i = 0; i < synsetArrayList.size(); i++) {
            Synset synset = synsetArrayList.get(i);
            List<Word> wordList = synset.getWords();

            String definition = synset.getPOS().toString() + " " + synset.getGloss();
            definitions.add(definition);

            System.out.println("#" + (i + 1) + " Sense of " + keyword + " : " + definition);

            for (int j = 0; j < wordList.size(); j++) {

                // exclude itself
                if (keyword.equals(wordList.get(j).getLemma())) {continue;}

                count ++;

                String result = wordList.get(j).getLemma();
                System.out.println("    " + count + " - " + result);

                resultArray.add(result);

            }

        }

        // finish
        screen.print("line");

        Item resultItem = new Item(keyword, definitions, resultArray);

        return resultItem;

    }

}
