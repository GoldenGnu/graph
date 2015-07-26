package uk.me.candle.eve.graph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Candle
 */
public class Node {
	final String name;
 	final int x;
	final int y;
    final Set<Edge> incommingEdges = new HashSet<Edge>();
    final Set<Edge> outgoingEdges = new HashSet<Edge>();

	public Node(int x, int y) {
		this("", 0, 0);
	}

	public Node(String name) {
		this(name, 0, 0);
	}

	public Node(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}

    public void addIncommingEdge(Edge e) {
        incommingEdges.add(e);
    }

    public void addOutgoingEdge(Edge e) {
        outgoingEdges.add(e);
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

	public String getName() {
		return name;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
		hash = 29 * hash + this.x;
		hash = 29 * hash + this.y;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Node other = (Node) obj;
		if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
			return false;
		}
		if (this.x != other.x) {
			return false;
		}
		if (this.y != other.y) {
			return false;
		}
		return true;
	}
}
