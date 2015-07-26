/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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



/**
 *
 * @author Niklas
 */
public class Jumps implements Distance {
	@Override
	public int distanceBetween(Node a, Node b) {
        if (a == b) return 0;

		Queue<Node> bfsQueue = new LinkedList<Node>();
		bfsQueue.add(a);

		Map<Node, Integer> distances = new HashMap<Node, Integer>();
		distances.put(a, Integer.valueOf(0));
		while (!bfsQueue.isEmpty()) {
			Node current = bfsQueue.remove();
			System.out.println(current.getName());
			for (Edge e : current.getOutgoingEdges()) {
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
	public List<Node> routeBetween(Node start, Node end) {
        if (start == end) return Collections.emptyList();
		Queue<Node> bfsQueue = new LinkedList<Node>();
		bfsQueue.add(start);

		// endNode => route to it.
		Map<Node, List<Node>> shortestRoutes = new HashMap<Node, List<Node>>();
		shortestRoutes.put(start, Collections.<Node>emptyList());

		while (bfsQueue.size() > 0) {
			Node current = bfsQueue.remove();
			if (current == end) {
				List<Node> tempRoute = new ArrayList<Node>(shortestRoutes.get(current));
				tempRoute.add(current);
				return tempRoute;
			}

			for (Edge e : current.getOutgoingEdges()) {
				if (!shortestRoutes.containsKey(e.getEnd())) {
					bfsQueue.add(e.getEnd());
					List<Node> tempRoute = new ArrayList<Node>(shortestRoutes.get(current));
					tempRoute.add(current);
					shortestRoutes.put(e.getEnd(), tempRoute);
				}
			}
		}
		throw new DisconnectedGraphException("There is no route between nodes: " + start.getName() + " and " + end.getName());
	}
}
