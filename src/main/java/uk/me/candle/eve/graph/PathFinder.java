/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.me.candle.eve.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Niklas
 */
public class PathFinder {
	public static boolean canCutCorners = true;
	
	private Node end;
	private Map<Integer, Map<Integer, Value>> values;
	
	public static List<Node> generate(Node start, Node finish) {
		PathFinder pathFinder = new PathFinder();
		return pathFinder.generateInner(start, finish);
	}
	
	private List<Node> generateInner(Node start, Node finish) {
		if (start.equals(end)) {
			return new ArrayList<Node>();
		}
		List<Node> openNodes = new ArrayList<Node>();
		List<Node> closedNodes = new ArrayList<Node>();
		values = new HashMap<Integer, Map<Integer, Value>>();
		end = finish;
		openNodes.add(start);
		setScoreG(start.x, start.y, 0);
		setScoreH(start.x, start.y, calculateHeuristic(start));
		setScoreF(start.x, start.y, getScoreH(start.x, start.y));
		
		while(openNodes.size() > 0) {
			Node current = getLowestNodeIn(openNodes);
			if(current == null) break;
			if(current.equals(end)) return reconstructPath(current);
			
			openNodes.remove(current);
			closedNodes.add(current);
			
			List<Node> neighbors = getNeighborNodes(current);
			for(Node n : neighbors) {
				
				if(closedNodes.contains(n)) continue;
				
				int tempGscore = getScoreG(current.x, current.y) + distanceBetween(n, current);
				
				boolean proceed = false;
				if(!openNodes.contains(n)) {
					openNodes.add(n);
					proceed = true;
				}
				else if(tempGscore < getScoreG(n.x, n.y)) {
					proceed = true;
				}
				
				if(proceed) {
					setFrom(n.x, n.y, current);
			        setScoreG(n.x, n.y, tempGscore);
			        setScoreH(n.x, n.y, calculateHeuristic(n));
			        setScoreF(n.x, n.y, getScoreG(n.x, n.y) + getScoreH(n.x, n.y));
				}
			}
		}
		return new ArrayList<Node>();
	}

	private int getScoreG(int x, int y) {
		return getValue(x, y).getScoreG();
	}

	private void setScoreG(int x, int y, int value) {
		getValue(x, y).setScoreG(value);
	}
	private int getScoreH(int x, int y) {
		return getValue(x, y).getScoreH();
	}

	private void setScoreH(int x, int y, int value) {
		getValue(x, y).setScoreH(value);
	}

	private int getScoreF(int x, int y) {
		return getValue(x, y).getScoreF();
	}

	private void setScoreF(int x, int y, int value) {
		getValue(x, y).setScoreF(value);
	}

	private Node getFrom(int x, int y) {
		return getValue(x, y).getFrom();
	}

	private void setFrom(int x, int y, Node node) {
		getValue(x, y).setFrom(node);
	}

	private Value getValue(int x, int y) {
		Map<Integer, Value> map = values.get(x);
		if (map == null) {
			map = new HashMap<Integer, Value>();
			values.put(x, map);
		}
		Value value = map.get(y);
		if (value == null) {
			value = new Value();
			map.put(y, value);
		}
		return value;
	}

	private List<Node> reconstructPath(Node from) {
		List<Node> path = new ArrayList<Node>();
		while (from != null) {
			path.add(0, from);
			from = getFrom(from.x, from.y);
		}
		return path;
	}

	private List<Node> getNeighborNodes(Node n) {
		List<Node> found = new ArrayList<Node>();
		for (Edge edge : n.getOutgoingEdges()) {
			found.add(edge.getEnd());
		}
		return found;
	}

	private Node getLowestNodeIn(List<Node> nodes) {
		int lowest = -1;
		Node found = null;
		for(Node n : nodes) {
			int dist = getFrom(n.x, n.y) == null ? -1 : getScoreG(getFrom(n.x, n.y).x, getFrom(n.x, n.y).y) + distanceBetween(n, getFrom(n.x, n.y)) + calculateHeuristic(n);
			if(dist <= lowest || lowest == -1) {
				lowest = dist;
				found = n;
			}
		}
		return found;
	}

	private int distanceBetween(Node n1, Node n2) {
		int dx = Math.abs(n1.getX() - n2.getX());
		int dy = Math.abs(n1.getY() - n2.getY());
		double diff = (double)Math.abs(dx - dy);

		if (diff == 0){ //Move diagonal (cost 1 more);
			return 2+1;
		}
		return 2;
	}

	private int calculateHeuristic(Node start) {
		int dx = Math.abs(start.getX() - end.getX());
		int dy = Math.abs(start.getY() - end.getY());
		return 2 * Math.max(dx, dy);
		//return 10 * (Math.abs(start.x - end.x) + Math.abs(start.y - end.y));
	}

	private static class Value {
		int gScore = 0;
		int hScore = 0;
		int fScore = 0;
		Node from = null;

		public Value() {
		}

		public int getScoreG() {
			return gScore;
		}

		public void setScoreG(int gScore) {
			this.gScore = gScore;
		}

		public int getScoreH() {
			return hScore;
		}

		public void setScoreH(int hScore) {
			this.hScore = hScore;
		}

		public int getScoreF() {
			return fScore;
		}

		public void setScoreF(int fScore) {
			this.fScore = fScore;
		}

		public Node getFrom() {
			return from;
		}

		public void setFrom(Node from) {
			this.from = from;
		}
	}
}
