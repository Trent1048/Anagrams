import java.util.Set;

public class Anagrams {

    public Anagrams(Set<String> dictionary) {
        if(dictionary == null) {
            throw new IllegalArgumentException();
        }
    }

    public Set<String> getWords(String phrase) {
        if(phrase == null) {
            throw new IllegalArgumentException();
        }
        return null;
    }

    public void print(String phrase) {
        print(phrase, 0);
    }

    public void print(String phrase, int max) {
        if(phrase == null || max < 0) {
            throw new IllegalArgumentException();
        } else if(phrase.equals("")) {
            return;
        }
    }
}
