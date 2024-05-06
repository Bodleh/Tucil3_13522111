import java.util.*;

public class Tes {
    public void greedyBestFirstSearch(String startWord, String endWord, Set<String> dictionary) {
        long startTime = System.nanoTime();
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(n -> n.heuristic));
        Set<String> visited = new HashSet<>();
        frontier.offer(new Node(startWord, null, 0, hammingDistance(startWord, endWord)));
    
        int nodesVisited = 0;
    
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            nodesVisited++;
    
            if (current.word.equals(endWord)) {
                long endTime = System.nanoTime();
                System.out.println("Path: " + buildPath(current));
                System.out.println("Nodes Visited: " + nodesVisited);
                System.out.println("Execution Time: " + (endTime - startTime) / 1000000 + " ms");
                return;
            }
    
            if (visited.add(current.word)) {
                for (String neighbor : getNeighbors(current.word, dictionary)) {
                    if (!visited.contains(neighbor)) {
                        int heuristic = hammingDistance(neighbor, endWord);
                        frontier.offer(new Node(neighbor, current.word, 0, heuristic));
                    }
                }
            }
        }
    
        System.out.println("No path found");
    }
    class Node {
        String word;
        String parent;
        int cost;
        int heuristic;
    
        public Node(String word, String parent, int cost, int heuristic) {
            this.word = word;
            this.parent = parent;
            this.cost = cost;
            this.heuristic = heuristic;
        }
    
        public Node(String word, String parent, int cost) {
            this(word, parent, cost, 0);
        }
    }
    
    private List<String> buildPath(Node endNode) {
        LinkedList<String> path = new LinkedList<>();
        for (Node node = endNode; node != null; node.word = node.parent) {
            path.addFirst(node.word);
        }
        return path;
    }

    private List<String> getNeighbors(String word, Set<String> dictionary) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char oldChar = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == oldChar) continue;
                chars[i] = c;
                String newWord = new String(chars);
                if (dictionary.contains(newWord)) {
                    neighbors.add(newWord);
                }
            }
            chars[i] = oldChar;
        }
        return neighbors;
    }

    // Heuristic function for A* and Greedy Best First Search: Hamming Distance
    private int hammingDistance(String current, String endWord) {
        int distance = 0;
        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) != endWord.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    
}
