/*
Trent Bultsma
3/12/2020
CS 145
Anagrams

This class is created by being fed a Set of Strings that contain
lots of words from the dictionary. This dictionary is used to find
words that are contined within an inputted phrase and then subsequently
find combinations of them that combine to create a perfect anagram with
no letters left over. Every time a phrase is inputted, it is stored and
it's anagrams are calculated. When another phrase is inputted, it checks
if it's the same. If it is, no extra calculations are done to stop redundant
calculations and the previous values kept and used. This allows the user
to input a word and check anagrams of differing max lengths without being
penalized with extra calculations.
 */

import java.util.HashSet;
import java.util.Set;

public class Anagrams {

    // stores the set of words to look through
    private Set<String> dictionary;
    // store results from previous inputted phrase
    // used to not recalculate the words if a phrase is inputted twice
    private String phrase;
    private Set<String> wordsInPhrase;
    private Set<Set<String>> anagrams;

    // sets up the dictionary set
    public Anagrams(Set<String> dictionary) {
        if(dictionary == null) {
            throw new IllegalArgumentException();
        }
        this.dictionary = dictionary;
    }

    // returns the words contained within the phrase
    public Set<String> getWords(String phrase) {
        if(phrase == null) {
            throw new IllegalArgumentException();
        }
        // if the word has already been calculated, don't recalculate it
        if(!phrase.equals(this.phrase)) {
            // calculate the words and anagrams in the phrase
            this.phrase = phrase;
            wordsInPhrase = calculateWords(phrase);
            getAnagrams();
        }
        return wordsInPhrase;
    }

    // actually calculates the words found in the phrase
    private Set<String> calculateWords(String phrase) {
        HashSet<String> wordsFromPhrase = new HashSet<>();
        LetterInventory phraseLI = new LetterInventory(phrase);
        for(String word : dictionary) {
            if(phraseLI.contains(word)) {
                wordsFromPhrase.add(word);
            }
        }
        return wordsFromPhrase;
    }

    // finds all the anagrams of phrase within wordsInPhrase and adds them to anagrams
    private void getAnagrams() {
        anagrams = new HashSet<>();
        LetterInventory phraseLI = new LetterInventory(phrase);
        for(String word : wordsInPhrase) {
            LetterInventory remainingLetters = new LetterInventory(phrase);
            remainingLetters.subtract(word);
            calculateAnagrams(word, remainingLetters, new HashSet<>());
        }
    }

    // recursively calculates the anagrams within phrase and adds them to the anagrams set
    private void calculateAnagrams(String word, LetterInventory remainingLetters, Set<String> anagramsFound) {
        anagramsFound.add(word);
        if(remainingLetters.isEmpty()) {
            // when there are no more letters, add it to anagrams and move on
            anagrams.add(anagramsFound);
        } else {
            for(String newWord : wordsInPhrase) {
                LetterInventory newWordLI = new LetterInventory(newWord);
                // must create a new LetterInventory with the same letters in it as remainingLetters
                // so remainingLetters isn't passed as a reference and doesn't mess up the whole thing
                // by having it's letters removed before they are used in different parts on the
                // entire recursive stack
                String remainingLettersAsString = remainingLetters.toString();
                LetterInventory newRemainingLetters = new LetterInventory(
                        remainingLettersAsString.substring(1, remainingLettersAsString.length()-1));
                if(remainingLetters.contains(newWordLI)) {
                    newRemainingLetters.subtract(newWordLI);
                    calculateAnagrams(newWord, newRemainingLetters, new HashSet<>(anagramsFound));
                }
            }
        }
    }

    // prints out all anagrams of a phrase
    public void print(String phrase) {
        print(phrase, 0);
    }

    // prints out all anagrams of a phrase with a maximum inputted amount of words
    public void print(String phrase, int max) {
        if(phrase == null || max < 0) {
            throw new IllegalArgumentException();
        } else if(phrase.equals("")) {
            return;
        }
        // updates anagrams by calling getWords()
        getWords(phrase);
        for(Set<String> anagram : anagrams) {
            if(max == 0 || anagram.size() <= max) {
                System.out.println(anagram);
            }
        }
    }
}
