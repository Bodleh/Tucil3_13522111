import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String filePath, startWord, endWord;
        filePath = "";
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Input dictionary's file name and it's extension: ");
            filePath = input.nextLine();
            int pilihan;
            HashSet<String> dict = Utility.loadWordsFromFile(filePath);
            if (dict.isEmpty()) {
                System.exit(0);
            }
            Collections.unmodifiableSet(dict);
            while (true) {
                System.out.print("Input start word: ");
                startWord = input.nextLine();
                System.out.print("Input end word: ");
                endWord = input.nextLine();
                startWord = startWord.toLowerCase();
                endWord = endWord.toLowerCase();
                if (startWord.length() == endWord.length()) {
                    break;
                }
                System.out.println("THe length of the startWord and endWord must be the same\n");
            }
            System.out.println("\n1. Uniform Cost Search");
            System.out.println("2. Greedy Best First Search");
            System.out.println("3. A*");
            System.out.println("4. All algorithm");
            while (true) {
                System.out.print("Input the number: ");
                pilihan = input.nextInt();
                if (pilihan >= 1 && pilihan <= 4) {
                    break;
                }
                System.out.println("Input valid from 1 until 4");
            }
            long startTime, endTime;
            Runtime run = Runtime.getRuntime();
            long startMemory, endMemory;

            if (pilihan == 1) {
                startTime = System.nanoTime();
                startMemory = run.totalMemory() - run.freeMemory();
                UCS UCSsolver = new UCS(dict, startWord, endWord);
                List<String> result = UCSsolver.findLadder();
                endMemory = run.totalMemory() - run.freeMemory();
                endTime = System.nanoTime();
                System.out.println("Memory used by UCS: " + (endMemory - startMemory)/1024 + " KB");
                System.out.println("Shortest path using UCS: " + result);
                System.out.println(((endTime - startTime)/1000000) + " ms");
            } else if (pilihan == 2) {
                startTime = System.nanoTime();
                startMemory = run.totalMemory() - run.freeMemory();
                GreedyBFS GBFSsolver = new GreedyBFS(dict, startWord, endWord);
                List<String> result = GBFSsolver.findLadder();
                endMemory = run.totalMemory() - run.freeMemory();
                endTime = System.nanoTime();
                System.out.println("Memory used by GBFS: " + (endMemory - startMemory)/1024 + " KB");
                System.out.println("Shortest path using Greedy BFS: " + result);
                System.out.println(((endTime - startTime)/1000000) + " ms");
            } else if (pilihan == 3) {
                startTime = System.nanoTime();
                startMemory = run.totalMemory() - run.freeMemory();
                AStar aStarSolver = new AStar(dict, startWord, endWord);
                List<String> result = aStarSolver.findLadder();
                endMemory = run.totalMemory() - run.freeMemory();
                endTime = System.nanoTime();
                System.out.println("Memory used by A*: " + (endMemory - startMemory)/1024 + " KB");
                System.out.println("Shortest path using A*: " + result);
                System.out.println(((endTime - startTime)/1000000) + " ms");
            } else if (pilihan == 4) {
                startTime = System.nanoTime();
                startMemory = run.totalMemory() - run.freeMemory();
                UCS UCSsolver = new UCS(dict, startWord, endWord);
                List<String> result = UCSsolver.findLadder();
                endMemory = run.totalMemory() - run.freeMemory();
                endTime = System.nanoTime();
                System.out.println("Memory used by UCS: " + (endMemory - startMemory)/1024 + " KB");
                System.out.println("Shortest path using UCS: " + result);
                System.out.println(((endTime - startTime)/1000000) + " ms");

                startTime = System.nanoTime();
                startMemory = run.totalMemory() - run.freeMemory();
                GreedyBFS GBFSsolver = new GreedyBFS(dict, startWord, endWord);
                List<String> result2 = GBFSsolver.findLadder();
                endMemory = run.totalMemory() - run.freeMemory();
                endTime = System.nanoTime();
                System.out.println("Memory used by GBFS: " + (endMemory - startMemory)/1024 + " KB");
                System.out.println("Shortest path using Greedy BFS: " + result2);
                System.out.println(((endTime - startTime)/1000000) + " ms");

                startTime = System.nanoTime();
                startMemory = run.totalMemory() - run.freeMemory();
                AStar aStarSolver = new AStar(dict, startWord, endWord);
                List<String> result3 = aStarSolver.findLadder();
                endMemory = run.totalMemory() - run.freeMemory();
                endTime = System.nanoTime();
                System.out.println("Memory used by A*: " + (endMemory - startMemory)/1024 + " KB");
                System.out.println("Shortest path using A*: " + result3);
                System.out.println(((endTime - startTime)/1000000) + " ms");
            }            
            input.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }
    }
}