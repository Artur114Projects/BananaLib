package com.artur114.bananalib.mc;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.function.Function;
import java.util.function.Predicate;

public class BananaGraphs {
    public static <T> void bfs(T startElement, Function<T, Iterable<T>> getNeighbors, Predicate<T> needProcess, Predicate<T> perform) {
        ArrayDeque<T> queue = new ArrayDeque<>(20);
        HashSet<T> checked = new HashSet<>(20);

        queue.addLast(startElement);
        checked.add(startElement);

        while (!queue.isEmpty()) {
            T obj = queue.poll();

            for (T neighbor : getNeighbors.apply(obj)) {
                if (checked.contains(neighbor)) continue;
                if (!needProcess.test(neighbor)) continue;
                if (perform.test(neighbor)) return;
                queue.addLast(neighbor);
                checked.add(neighbor);
            }
        }
    }

    public static <T> void bfs(T startElement, Function<T, Iterable<T>> getNeighbors, Predicate<T> perform) {
        bfs(startElement, getNeighbors, (obj) -> true, perform);
    }

    public static <T> void dfs(T startElement, Function<T, Iterable<T>> getNeighbors, Predicate<T> needProcess, Predicate<T> perform) {
        ArrayDeque<T> stack = new ArrayDeque<>(20);
        HashSet<T> checked = new HashSet<>(20);

        stack.addLast(startElement);
        checked.add(startElement);

        while (!stack.isEmpty()) {
            T obj = stack.pollLast();

            for (T neighbor : getNeighbors.apply(obj)) {
                if (checked.contains(neighbor)) continue;
                if (!needProcess.test(neighbor)) continue;
                if (perform.test(neighbor)) return;
                stack.addLast(neighbor);
                checked.add(neighbor);
            }
        }
    }

    public static <T> void dfs(T startElement, Function<T, Iterable<T>> getNeighbors, Predicate<T> perform) {
        dfs(startElement, getNeighbors, (obj) -> true, perform);
    }
}
