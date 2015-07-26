package uk.me.candle.eve.graph;

/**
 *
 * @author Candle
 */
public class Edge {
    int weight;
    Node start;
    Node end;

	public Edge(Node start, Node end) {
		this(start, end, 1);
	}

    public Edge(Node start, Node end, int weight) {
        if (start == end) {
            throw new IllegalArgumentException("The edge cannot be a self loop.");
        }
        this.start = start;
        this.end = end;
		this.weight = weight;
    }
    
    public String toDotLine() {
        return "node" + start.hashCode() + " -> " + "node" + end.hashCode();
    }

	public int getWeight() {
		return weight;
	}

    public Node getEnd() {
        return end;
    }

    public Node getStart() {
        return start;
    }
}
