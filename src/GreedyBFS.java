import java.util.*;

public class GreedyBFS {
    private HashSet<String> dictionary;
    private String startWord;
    private String endWord;
    
    private static class Node {
        String word;
        Node parent;
        int heuristic;

        Node(String word, Node parent, int heuristic) {
            this.word = word;
            this.parent = parent;
            this.heuristic = heuristic;
        }
    }

    public GreedyBFS(HashSet<String>wordSet, String startWord, String endWord) {
        this.dictionary = new HashSet<>(wordSet);
        this.startWord = startWord;
        this.endWord = endWord;
    }
    
    private List<String> buildPath(Node endNode) {
        LinkedList<String> path = new LinkedList<>();
        for (Node node = endNode; node != null; node = node.parent) {
            path.addFirst(node.word);
        }
        return path;
    }

    public List<String> findLadder() {
        if (!dictionary.contains(startWord) || !dictionary.contains(endWord)) {
            return Collections.emptyList();
        }
        
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(node -> node.heuristic));
        Set<String> visited = new HashSet<>();
        frontier.offer(new Node(startWord, null, Utility.getHeuristic(startWord, endWord)));

        int nodesVisited = 0;

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            nodesVisited++;

            if (current.word.equals(endWord)) {
                System.out.println("\nNodes visited: " + nodesVisited);
                return buildPath(current);
            }
            if (visited.add(current.word)) {
                for (String neighbor : Utility.getNeighbors(current.word, dictionary)) {
                    if (!visited.contains(neighbor)) {
                        int heuristic = Utility.getHeuristic(neighbor, endWord);
                        frontier.offer(new Node(neighbor, current, heuristic));
                    }
                }
            }
        }
        System.out.println("\nNodes visited: " + nodesVisited);
        return Collections.emptyList();
    }
}