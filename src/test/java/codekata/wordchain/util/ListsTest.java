package codekata.wordchain.util;

import org.junit.Test;

import java.util.List;

import static codekata.wordchain.util.Lists.filter;
import static codekata.wordchain.util.Lists.foldL;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;

public class ListsTest {

    @Test
    public void shouldFoldLeft() {
        List<Integer> numbers = Lists.of(1, 2, 3, 4, 5);
        Integer sum = foldL(numbers, 0, doSum());
        assertThat(sum, is(15));
    }

    @Test
    public void shouldFilter() {
        List<Integer> numbers = Lists.of(1, 2, 3, 4, 5);
        List<Integer> oddNumbers = filter(numbers, pickOddNumbers());

        assertThat(oddNumbers.size(), is(3));
        assertThat(oddNumbers, hasItem(1));
        assertThat(oddNumbers, hasItem(3));
        assertThat(oddNumbers, hasItem(5));
    }

    @Test
    public void shouldDoFilterAndFold() {
        List<Integer> numbers = Lists.of(1, 2, 3, 4, 5);
        Integer sum = foldL(filter(numbers, pickOddNumbers()), 0, doSum());
        assertThat(sum, is(9));
    }

    private Predicate<Integer> pickOddNumbers() {
        return new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer input) {
                return input % 2 != 0;
            }
        };
    }


    private Function<Tuple2<Integer, Integer>, Integer> doSum() {
        return new Function<Tuple2<Integer, Integer>, Integer>() {
            @Override
            public Integer apply(Tuple2<Integer, Integer> input) {
                Integer sumSoFar = input._1();
                Integer element = input._2();
                return sumSoFar + element;
            }
        };
    }


}