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

import org.junit.Before;

/**
 *
 * @author Candle
 */
public class TestGraph {

    @Before
    public void before() {
    }

    Graph g;

	/*
    @Test
    public void testDistanceBetween() {
        Graph g = new Graph();
        Node a = new Node(1, 0);
        Node b = new Node(0, 2);
        g.addEdge(new Edge(1, 3));
        //assertEquals(0, g.getRoute(a, a).size());
        assertEquals(1, g.getRoute(a, b).size());
        //assertEquals(0, g.getRoute(b, b).size());
    }

    @Test(expected=DisconnectedGraphException.class)
    public void testDistanceBetweenFail() {
        Graph g = new Graph();
        Node a = new Node("a");
        Node b = new Node("b");
        g.addEdge(new Edge(a, b));

        assertEquals(1, g.getRoute(b, a));
    }

    @Test
    public void testDistanceBetween2() {
        Graph g = new Graph();
        Node a = new Node("a");
        Node b = new Node("b");
        g.addEdge(new Edge(a, b));
        g.addEdge(new Edge(b, a));

        assertEquals(0, g.getRoute(a, a).size());
        assertEquals(1, g.getRoute(a, b).size());
        assertEquals(1, g.getRoute(b, a).size());
        assertEquals(0, g.getRoute(b, b).size());
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

        assertEquals(1, gr.getRoute(e, d).size());
        assertEquals(1, gr.getRoute(d, e).size());
        assertEquals(1, gr.getRoute(a, d).size());
        assertEquals(1, gr.getRoute(d, a).size());
        assertEquals(2, gr.getRoute(a, e).size());
        assertEquals(2, gr.getRoute(e, a).size());
        assertEquals(4, gr.getRoute(e, g).size());
        assertEquals(4, gr.getRoute(g, e).size());
    }
	*/

}
