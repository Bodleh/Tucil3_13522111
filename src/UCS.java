import java.util.*;

public class UCS {
    private HashSet<String> dictionary;
    private String startWord;
    private String endWord;

    // node class to hold each step of the word ladder
    private static class Node {
        String word;
        Node parent;
        int cost;

        Node(String word, Node parent, int cost) {
            this.word = word;
            this.parent = parent;
            this.cost = cost;
        }
    }

    public UCS(HashSet<String>wordSet, String startWord, String endWWord) {
        this.startWord = startWord;
        this.endWord = endWWord;
        this.dictionary = new HashSet<>(wordSet);
    }

    public List<String> findLadder() {
        if (!dictionary.contains(startWord) || !dictionary.contains(endWord)) {
            return Collections.emptyList();
        }

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        Set<String> visited = new HashSet<>();
        queue.add(new Node(startWord, null, 0));

        int nodesVisited = 0;
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            nodesVisited++;

            if (current.word.equals(endWord)) {
                System.out.println("\nNodes visited: " + nodesVisited);
                return buildPath(current);
            }
            
            for (String neighbor : Utility.getNeighbors(current.word, dictionary)) {
                int newCost = current.cost + 1;
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(new Node(neighbor, current, newCost));
                }
            }
        }
        System.out.println("\nNodes visited: " + nodesVisited);
        return Collections.emptyList();
    }

    // reconstruct path from endWWord to startWord
    private List<String> buildPath(Node endNode) {
        LinkedList<String> path = new LinkedList<>();
        for (Node node = endNode; node != null; node = node.parent) {
            path.addFirst(node.word);
        }
        return path;
    }
}