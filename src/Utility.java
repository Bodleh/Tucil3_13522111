import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public final class Utility {
    public static HashSet<String> loadWordsFromFile(String filePath) throws FileNotFoundException {
        HashSet<String> words = new HashSet<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim();
                if (!word.isEmpty()) {
                    words.add(word);
                }
            }
        }
        return words;
    }

    // utility function to get all valid one-letter modifications of a word
    public static List<String> getNeighbors(String word, Set<String> dictionary) {
        List<String> neighbors = new ArrayList<>();
        char[] tempChars = word.toCharArray();

        for (int i = 0; i < tempChars.length; i++) {
            char oldChar = tempChars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != oldChar) {
                    tempChars[i] = c;
                    String newWord = new String(tempChars);
                    if (dictionary.contains(newWord)) {
                        neighbors.add(newWord);
                    }
                }
            }
            tempChars[i] = oldChar;
        }
        return neighbors;
    }

    // heuristic function for Greedy Best First Search and A* algorithm
    public static int getHeuristic(String current, String goal) {
        int differences = 0;
        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) != goal.charAt(i)) {
                differences++;
            }
        }
        return differences;
    }
}