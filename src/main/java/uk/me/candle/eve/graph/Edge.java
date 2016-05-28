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
