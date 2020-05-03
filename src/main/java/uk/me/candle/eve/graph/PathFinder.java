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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PathFinder<T extends Node> {
    public static boolean canCutCorners = true;
    
    private T end;
    private Map<Integer, Map<Integer, Value<T>>> values;
    
    public static <T extends Node> List<T> generate(T start, T finish) {
        PathFinder<T> pathFinder = new PathFinder<>();
        return pathFinder.generateInner(start, finish);
    }
    
    private List<T> generateInner(T start, T finish) {
        if (start.equals(end)) {
            return new ArrayList<>();
        }
        List<T> openNodes = new ArrayList<>();
        List<T> closedNodes = new ArrayList<>();
        values = new HashMap<>();
        end = finish;
        openNodes.add(start);
        setScoreG(start.x, start.y, 0);
        setScoreH(start.x, start.y, calculateHeuristic(start));
        setScoreF(start.x, start.y, getScoreH(start.x, start.y));
        
        while(openNodes.size() > 0) {
            T current = getLowestNodeIn(openNodes);
            if(current == null) break;
            if(current.equals(end)) return reconstructPath(current);
            
            openNodes.remove(current);
            closedNodes.add(current);
            
            List<T> neighbors = getNeighborNodes(current);
            for(T n : neighbors) {
                
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
        return new ArrayList<>();
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

    private T getFrom(int x, int y) {
        return getValue(x, y).getFrom();
    }

    private void setFrom(int x, int y, T node) {
        getValue(x, y).setFrom(node);
    }

    private Value<T> getValue(int x, int y) {
        Map<Integer, Value<T>> map = values.get(x);
        if (map == null) {
            map = new HashMap<Integer, Value<T>>();
            values.put(x, map);
        }
        Value<T> value = map.get(y);
        if (value == null) {
            value = new Value();
            map.put(y, value);
        }
        return value;
    }

    private List<T> reconstructPath(T from) {
        List<T> path = new ArrayList<>();
        while (from != null) {
            path.add(0, from);
            from = getFrom(from.x, from.y);
        }
        return path;
    }

    private List<T> getNeighborNodes(T n) {
        List<T> found = new ArrayList<>();
        for (Edge<T> edge : n.getOutgoingEdges()) {
            found.add(edge.getEnd());
        }
        return found;
    }

    private T getLowestNodeIn(List<T> nodes) {
        int lowest = -1;
        T found = null;
        for(T n : nodes) {
            int dist = getFrom(n.x, n.y) == null ? -1 : getScoreG(getFrom(n.x, n.y).x, getFrom(n.x, n.y).y) + distanceBetween(n, getFrom(n.x, n.y)) + calculateHeuristic(n);
            if(dist <= lowest || lowest == -1) {
                lowest = dist;
                found = n;
            }
        }
        return found;
    }

    private int distanceBetween(T n1, T n2) {
        int dx = Math.abs(n1.getX() - n2.getX());
        int dy = Math.abs(n1.getY() - n2.getY());
        double diff = (double)Math.abs(dx - dy);

        if (diff == 0){ //Move diagonal (cost 1 more);
            return 2+1;
        }
        return 2;
    }

    private int calculateHeuristic(T start) {
        int dx = Math.abs(start.getX() - end.getX());
        int dy = Math.abs(start.getY() - end.getY());
        return 2 * Math.max(dx, dy);
        //return 10 * (Math.abs(start.x - end.x) + Math.abs(start.y - end.y));
    }

    private static class Value<T extends Node> {
        int gScore = 0;
        int hScore = 0;
        int fScore = 0;
        T from = null;

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

        public T getFrom() {
            return from;
        }

        public void setFrom(T from) {
            this.from = from;
        }
    }
}
