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

/**
 *
 * @author Candle
 */
public class DisconnectedGraphException extends RuntimeException {
    private static final long serialVersionUID = 1l;

    public DisconnectedGraphException(Throwable cause) {
        super(cause);
    }

    public DisconnectedGraphException(String message, Throwable cause) {
        super(message, cause);
    }

    public DisconnectedGraphException(String message) {
        super(message);
    }

    public DisconnectedGraphException() {
    }
}
