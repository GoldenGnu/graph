package uk.me.candle.eve.graph;

/**
 *
 * @author Candle
 */
public class Edge {
    int weight = 1;
    Node start;
    Node end;

    public Edge(Node start, Node end) {
        if (start == end) {
            throw new IllegalArgumentException("The edge cannot be a self loop.");
        }
        this.start = start;
        this.end = end;
    }
    
    public String toDotLine() {
        return "node" + start.hashCode() + " -> " + "node" + end.hashCode();
    }

    public Node getEnd() {
        return end;
    }

    public Node getStart() {
        return start;
    }
}
