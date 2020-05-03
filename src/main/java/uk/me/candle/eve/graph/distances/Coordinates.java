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

import java.util.Collections;
import java.util.List;
import uk.me.candle.eve.graph.Distance;
import uk.me.candle.eve.graph.Node;


public class Coordinates<T extends Node> implements Distance<T> {

    @Override
    public int distanceBetween(T a, T b) {
        double xx = (int) Math.pow(a.getX() - b.getX(), 2);
        double yy = Math.pow(a.getY() - b.getY(), 2);
        return (int) Math.sqrt(xx + yy);
    }

    @Override
    public List<T> routeBetween(T start, T end) {
        return Collections.emptyList();
    }
    
}
