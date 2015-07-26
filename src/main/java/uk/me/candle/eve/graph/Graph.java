package uk.me.candle.eve.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Candle
 */
public class Graph {
    Set<Edge> edges = new HashSet<Edge>();
    Set<Node> nodes = new HashSet<Node>();


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

    public String toDot() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {");
        sb.append("\n");
        for (Node n : nodes) {
            sb.append("  ");
            sb.append(n.toDotLine());
            sb.append("\n");
        }
        for (Edge e : edges) {
            sb.append("  ");
            sb.append(e.toDotLine());
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();
    }

    public int distanceBetween(Node a, Node b) {
        if (a == b) return 0;
        Map<Node, Integer> distances = new HashMap<Node, Integer>();
        distances.put(a, Integer.valueOf(0));
        Queue<Node> bfs = new LinkedList<Node>();
        bfs.add(a);
        while (!bfs.isEmpty()) {
            Node current = bfs.remove();
            for (Edge e : current.getOutgoingEdges()) {
                if (!bfs.contains(e.getEnd()) && !distances.containsKey(e.getEnd())) {
                    bfs.add(e.getEnd());
                    distances.put(e.getEnd(), distances.get(current) + 1);
                }
                if (e.getEnd() == b) {
                    return distances.get(current) + 1;
                }
            }
        }
        throw new DisconnectedGraphException("There is no route between " + a.getName() + " and " + b.getName());
    }

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
