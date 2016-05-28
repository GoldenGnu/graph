/*
 * Copyright 2015-2016, Niklas Kyster Rasmussen, Flaming Candle
 *
 * This file is part of Graph
 *
 * Graph is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * Graph is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graph; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 */
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
