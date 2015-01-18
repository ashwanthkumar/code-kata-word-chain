package codekata.wordchain.util;

import java.util.HashSet;
import java.util.Set;

public class Sets {
    public static <T> Set<T> copy(Set<T> input) {
        HashSet<T> copy = new HashSet<T>();
        copy.addAll(input);
        return copy;
    }
}
