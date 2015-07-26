/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.me.candle.eve.graph;

import java.util.List;

/**
 *
 * @author Niklas
 */
public interface Distance {
	public int distanceBetween(Node a, Node b);
	public List<Node> routeBetween(Node start, Node end);
}
