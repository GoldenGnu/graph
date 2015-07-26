package uk.me.candle.eve.graph;

import uk.me.candle.eve.graph.DisconnectedGraphException;
import uk.me.candle.eve.graph.Node;
import uk.me.candle.eve.graph.Graph;
import uk.me.candle.eve.graph.Edge;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.*;

/**
 *
 * @author Candle
 */
public class TestGraph {

    @Before
    public void before() {
    }

    Graph g;

    @Test
    public void testDistanceBetween() {
        Graph g = new Graph();
        Node a = new Node("a");
        Node b = new Node("b");
        g.addEdge(new Edge(a, b));
        assertEquals(0, g.distanceBetween(a, a));
        assertEquals(1, g.distanceBetween(a, b));
        assertEquals(0, g.distanceBetween(b, b));
    }

    @Test(expected=DisconnectedGraphException.class)
    public void testDistanceBetweenFail() {
        Graph g = new Graph();
        Node a = new Node("a");
        Node b = new Node("b");
        g.addEdge(new Edge(a, b));

        assertEquals(1, g.distanceBetween(b, a));
    }

    @Test
    public void testDistanceBetween2() {
        Graph g = new Graph();
        Node a = new Node("a");
        Node b = new Node("b");
        g.addEdge(new Edge(a, b));
        g.addEdge(new Edge(b, a));

        assertEquals(0, g.distanceBetween(a, a));
        assertEquals(1, g.distanceBetween(a, b));
        assertEquals(1, g.distanceBetween(b, a));
        assertEquals(0, g.distanceBetween(b, b));
    }

    @Test
    public void testDistanceBetween3() {
        Graph gr = new Graph();
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");
        Node g = new Node("g");
        Node h = new Node("h");
        gr.addEdge(new Edge(a, b)); gr.addEdge(new Edge(b, a));
        gr.addEdge(new Edge(a, c)); gr.addEdge(new Edge(c, a));
        gr.addEdge(new Edge(a, d)); gr.addEdge(new Edge(d, a));
        gr.addEdge(new Edge(e, d)); gr.addEdge(new Edge(d, e));
        gr.addEdge(new Edge(d, c)); gr.addEdge(new Edge(c, d));
        gr.addEdge(new Edge(b, c)); gr.addEdge(new Edge(c, b));
        gr.addEdge(new Edge(a, f)); gr.addEdge(new Edge(b, f));
        gr.addEdge(new Edge(f, g)); gr.addEdge(new Edge(g, f));
        gr.addEdge(new Edge(g, h)); gr.addEdge(new Edge(h, g));
        gr.addEdge(new Edge(h, c)); gr.addEdge(new Edge(c, h));

        assertEquals(1, gr.distanceBetween(e, d));
        assertEquals(1, gr.distanceBetween(d, e));
        assertEquals(1, gr.distanceBetween(a, d));
        assertEquals(1, gr.distanceBetween(d, a));
        assertEquals(2, gr.distanceBetween(a, e));
        assertEquals(2, gr.distanceBetween(e, a));
        assertEquals(4, gr.distanceBetween(e, g));
        assertEquals(4, gr.distanceBetween(g, e));
    }
}
