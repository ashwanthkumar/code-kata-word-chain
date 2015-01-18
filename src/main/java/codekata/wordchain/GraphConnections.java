package codekata.wordchain;

import codekata.wordchain.util.Predicate;
import codekata.wordchain.util.Sets;
import codekata.wordchain.util.Tuple2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static codekata.wordchain.util.Lists.any;
import static codekata.wordchain.util.Lists.of;
import static codekata.wordchain.util.Tuple2.tuple2;

/**
 * Represents a connection between any 2 nodes.
 */
public class GraphConnections<T> {
    private Set<Tuple2<T, T>> connections = new HashSet<Tuple2<T, T>>();

    public GraphConnections<T> addConnection(T left, T right) {
        connections.add(tuple2(left, right));
        return this;
    }

    /**
     * Checks for neighbours
     */
    public boolean isConnected(T left, T right) {
        return connections.contains(tuple2(left, right));
    }

    /**
     * Checks recursively if they're connected
     */
    public boolean isDeeplyConnected(T left, T right) {
        return isDeeplyConnected(left, right, Sets.copy(connections));
    }

    /**
     * Find a path from left to right
     */
    public List<T> traverse(T left, T right) {
        return traverse(left, right, Sets.copy(connections));
    }

    public List<T> candidates(T node) {
        return candidates(node, connections);
    }

    private List<T> traverse(T left, T right, Set<Tuple2<T, T>> connections) {
        if (isConnected(left, right)) return of(left, right);
        else {
            List<T> candidates = candidates(left, connections);

            List<T> sofar = of(left);
            for (T next : candidates) {
                connections.remove(tuple2(left, next));
                List<T> traversed = traverse(next, right, connections);
                if (!traversed.isEmpty()) {
                    sofar.addAll(traversed);
                    return sofar;
                }
            }
        }
        return new ArrayList<T>();
    }

    private boolean isDeeplyConnected(final T left, final T right, final Set<Tuple2<T, T>> connections) {
        if (isConnected(left, right)) {
            return true;
        } else {
            List<T> candidates = candidates(left, connections);
            return any(candidates, new Predicate<T>() {
                @Override
                public Boolean apply(T next) {
                    connections.remove(tuple2(left, next));
                    return isDeeplyConnected(next, right, connections);
                }
            });
        }
    }

    private List<T> candidates(T node, Set<Tuple2<T, T>> connections) {
        List<T> candidates = new ArrayList<T>();
        for (Tuple2<T, T> connection : connections) {
            if (connection._1().equals(node)) {
                candidates.add(connection._2());
            }
        }

        return candidates;
    }

}
