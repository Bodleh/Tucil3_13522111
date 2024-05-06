import java.util.*;

public class AStar {
    private HashSet<String> dictionary;
    private String startWord;
    private String endWord;

    private static class Node {
        String word;
        Node parent;
        int cost;
        int heuristic;
    
        public Node(String word, Node parent, int cost, int heuristic) {
            this.word = word;
            this.parent = parent;
            this.cost = cost;
            this.heuristic = heuristic;
        }
    }

    public AStar(HashSet<String>wordSet, String startWord, String end) {
        this.startWord = startWord;
        this.endWord = end;
        this.dictionary = new HashSet<>(wordSet);
    }

    private List<String> buildPath(Node endNode) {
        LinkedList<String> path = new LinkedList<>();
        for (Node at = endNode; at != null; at = at.parent) {
            path.addFirst(at.word);
        }
        return path;
    }

    public List<String> findLadder() {
        if (!dictionary.contains(startWord) || !dictionary.contains(endWord)) {
            return Collections.emptyList();
        }

        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost + n.heuristic));
        Map<String, Integer> visitedWithCost = new HashMap<>();

        frontier.offer(new Node(startWord, null, 0, Utility.getHeuristic(startWord, endWord)));
        visitedWithCost.put(startWord, 0);
        int nodesVisitedWithCost = 0;
    
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            if (current.cost > visitedWithCost.get(current.word)) {
                continue; 
            }
            nodesVisitedWithCost++;
    
            if (current.word.equals(endWord)) {
                System.out.println("\nNodes Visited: " + nodesVisitedWithCost);
                return buildPath(current);
            }
    
            for (String neighbor : Utility.getNeighbors(current.word, dictionary)) {
                int newCost = visitedWithCost.get(current.word) + 1;
                if (!visitedWithCost.containsKey(neighbor)) {
                    visitedWithCost.put(neighbor, newCost);
                    int heuristic = Utility.getHeuristic(neighbor, endWord);
                    frontier.offer(new Node(neighbor, current, newCost, heuristic));
                }
            }
        }
        System.out.println("\nNodes visited: " + nodesVisitedWithCost);
        return Collections.emptyList();
    }    
}