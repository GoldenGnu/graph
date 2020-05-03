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
package uk.me.candle.eve.graph.distances;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import uk.me.candle.eve.graph.DisconnectedGraphException;
import uk.me.candle.eve.graph.Distance;
import uk.me.candle.eve.graph.Edge;
import uk.me.candle.eve.graph.Node;


public class Jumps<T extends Node> implements Distance<T> {

    @Override
    public int distanceBetween(T a, T b) {
        if (a == b) return 0;

        Queue<T> bfsQueue = new LinkedList<>();
        bfsQueue.add(a);

        Map<T, Integer> distances = new HashMap<>();
        distances.put(a, 0);
        while (!bfsQueue.isEmpty()) {
            T current = bfsQueue.remove();
            for (Edge<T> e : current.getOutgoingEdges()) {
                if (!bfsQueue.contains(e.getEnd()) && !distances.containsKey(e.getEnd())) {
                    bfsQueue.add(e.getEnd());
                    distances.put(e.getEnd(), distances.get(current) + 1);
                }
                if (e.getEnd() == b) {
                    return distances.get(current) + 1;
                }
            }
        }
        throw new DisconnectedGraphException("There is no route between " + a.getName() + " and " + b.getName());
    }

    @Override
    public List<T> routeBetween(T start, T end) {
        if (start == end) return Collections.emptyList();
        Queue<T> bfsQueue = new LinkedList<>();
        bfsQueue.add(start);

        // endNode => route to it.
        Map<T, List<T>> shortestRoutes = new HashMap<>();
        shortestRoutes.put(start, Collections.<T>emptyList());

        while (bfsQueue.size() > 0) {
            T current = bfsQueue.remove();
            if (current == end) {
                List<T> tempRoute = new ArrayList<>(shortestRoutes.get(current));
                tempRoute.add(current);
                return tempRoute;
            }

            for (Edge<T> e : current.getOutgoingEdges()) {
                if (!shortestRoutes.containsKey(e.getEnd())) {
                    bfsQueue.add(e.getEnd());
                    List<T> tempRoute = new ArrayList<>(shortestRoutes.get(current));
                    tempRoute.add(current);
                    shortestRoutes.put(e.getEnd(), tempRoute);
                }
            }
        }
        throw new DisconnectedGraphException("There is no route between nodes: " + start.getName() + " and " + end.getName());
    }
}
