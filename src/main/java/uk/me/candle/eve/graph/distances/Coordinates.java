/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.me.candle.eve.graph.distances;

import java.util.Collections;
import java.util.List;
import uk.me.candle.eve.graph.Distance;
import uk.me.candle.eve.graph.Node;

/**
 *
 * @author Niklas
 */
public class Coordinates implements Distance {

	@Override
	public int distanceBetween(Node a, Node b) {
		double xx = (int) Math.pow(a.getX() - b.getX(), 2);
		double yy = Math.pow(a.getY() - b.getY(), 2);
		return (int) Math.sqrt(xx + yy);
	}

	@Override
	public List<Node> routeBetween(Node start, Node end) {
		return Collections.emptyList();
	}
	
}
