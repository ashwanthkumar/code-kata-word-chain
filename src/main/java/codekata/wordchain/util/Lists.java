package codekata.wordchain.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static codekata.wordchain.util.Tuple2.tuple2;

public class Lists {
    public static <T> List<T> of(T... elements) {
        ArrayList<T> list = new ArrayList<T>();
        Collections.addAll(list, elements);
        return list;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> condition) {
        ArrayList<T> filteredList = new ArrayList<T>();
        for (T item : list) {
            if (condition.apply(item)) filteredList.add(item);
        }
        return filteredList;
    }

    public static <T, Z> Z foldL(List<T> list, Z initialValue, Function<Tuple2<Z, T>, Z> foldFunction) {
        Z foldedValue = initialValue;
        for (T item : list) {
            foldedValue = foldFunction.apply(tuple2(foldedValue, item));
        }

        return foldedValue;
    }

    public static <T> boolean any(List<T> input, Predicate<T> condition) {
        for (T data : input) {
            if (condition.apply(data)) return true;
        }

        return false;
    }
}
