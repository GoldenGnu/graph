/*
 * Copyright 2015-2020, Niklas Kyster Rasmussen, Flaming Candle
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
 * @param <T>
 */
public class Graph<T extends Node> {

    Set<Edge<T>> edges = new HashSet<>();
    Set<T> nodes = new HashSet<>();
    private final Distance distance;

    public Graph(Distance distance) {
        this.distance = distance;
    }

    public void addEdge(Edge<T> e) {
        edges.add(e);
        nodes.add(e.getStart());
        nodes.add(e.getEnd());
        e.getStart().addOutgoingEdge(e);
        e.getEnd().addIncommingEdge(e);
    }

    public void addNode(T n) {
        nodes.add(n);
    }

    public Set<Edge<T>> getEdges() {
        return Collections.unmodifiableSet(edges);
    }

    public Set<T> getNodes() {
        return Collections.unmodifiableSet(nodes);
    }

    public boolean isDisconnected() {
        for (T n : nodes) {
            if (n.getIncommingEdges().isEmpty() && n.getOutgoingEdges().isEmpty()) return true;
        }
        return false;
    }

    public void clear() {
        for (T n : nodes) {
            n.clearEdges();
        }
        nodes.clear();
        edges.clear();
    }

    public int distanceBetween(T a, T b) {
        return distance.distanceBetween(a, b);
    }

    public List<T> routeBetween(T start, T end) {
        return distance.routeBetween(start, end);
    }
}
