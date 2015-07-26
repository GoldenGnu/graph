package uk.me.candle.eve.graph;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Candle
 */
public class Graph {

	Set<Edge> edges = new HashSet<Edge>();
	Set<Node> nodes = new HashSet<Node>();
	private final Distance distance;

	public Graph(Distance distance) {
		this.distance = distance;
	}

	public void addEdge(Edge e) {
		edges.add(e);
		nodes.add(e.getStart());
		nodes.add(e.getEnd());
		e.getStart().addOutgoingEdge(e);
		e.getEnd().addIncommingEdge(e);
	}

	public void addNode(Node n) {
		nodes.add(n);
	}

	public Set<Edge> getEdges() {
		return Collections.unmodifiableSet(edges);
	}

	public Set<Node> getNodes() {
		return Collections.unmodifiableSet(nodes);
	}

	public boolean isDisconnected() {
		for (Node n : nodes) {
            if (n.getIncommingEdges().size() == 0 && n.getOutgoingEdges().size() == 0) return true;
		}
		return false;
	}

	public void clear() {
		for (Node n : nodes) {
			n.clearEdges();
		}
		nodes.clear();
		edges.clear();
	}

	public int distanceBetween(Node a, Node b) {
		return distance.distanceBetween(a, b);
	}

	public List<Node> routeBetween(Node start, Node end) {
        return distance.routeBetween(start, end);
	}
}
