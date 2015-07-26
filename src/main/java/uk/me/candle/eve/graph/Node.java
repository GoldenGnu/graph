package uk.me.candle.eve.graph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Candle
 */
public class Node {
    String name;
    Set<Edge> incommingEdges = new HashSet<Edge>();
    Set<Edge> outgoingEdges = new HashSet<Edge>();

    public Node(String name) {
        this.name = name;
    }

    public void addIncommingEdge(Edge e) {
        incommingEdges.add(e);
    }

    public void addOutgoingEdge(Edge e) {
        outgoingEdges.add(e);
    }

    public String getName() {
        return name;
    }

    public String toDotLine() {
        return "node" + hashCode() + " [label=\"" + getName() + "\"];";
    }

    public Set<Edge> getIncommingEdges() {
        return Collections.unmodifiableSet(incommingEdges);
    }

    public Set<Edge> getOutgoingEdges() {
        return Collections.unmodifiableSet(outgoingEdges);
    }

    void clearEdges() {
        incommingEdges.clear();
        outgoingEdges.clear();
    }
}
