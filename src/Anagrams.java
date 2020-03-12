import java.util.HashSet;
import java.util.Set;

public class Anagrams {

    // stores the set of words to look through
    private Set<String> dictionary;
    // store results from previous inputted phrase
    // used to not recalculate the words if a phrase is inputted twice
    private Set<String> wordsInPhrase;
    private String phrase;

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
        if(phrase.equals(this.phrase)) {
            return wordsInPhrase;
        } else {
            this.phrase = phrase;
            wordsInPhrase = calculateWords(phrase);
            return wordsInPhrase;
        }
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
    }
}
