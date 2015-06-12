package org.spaconference.rts.solutions;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.spaconference.rts.runner.ExampleRunner;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

import static java.lang.Math.floor;
import static java.lang.Math.sqrt;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.spaconference.rts.runner.ExampleRunner.Way;


@RunWith(ExampleRunner.class)
public class ExE_Grouping2 {
    @Way
    public static Map<Integer, List<Integer>> oldWay(int max) {
        SortedMap<Integer, List<Integer>> multiples = new TreeMap<>();

        for (int i = 2; i < max; i++) {
            int divisor = smallestDivisor(i);

            if (multiples.containsKey(divisor)) {
                multiples.get(divisor).add(i);
            } else {
                List<Integer> group = new ArrayList<>();
                group.add(i);
                multiples.put(divisor, group);
            }
        }

        return multiples;
    }

    @Way
    public static Map<Integer, List<Integer>> newWay(int max) {
        return Collections.emptyMap();
    }

    @Test
    public void test_to_10(IntFunction<Map<Integer, List<Integer>>> multiples) {
        assertThat(multiples.apply(10), equalTo(ImmutableMap.of(
                2, asList(2, 4, 6, 8),
                3, asList(3, 9),
                5, asList(5),
                7, asList(7))));
    }

    @Test
    public void test_to_50(IntFunction<Map<Integer, List<Integer>>> f) {
        assertThat(f.apply(50), equalTo(ImmutableMap.builder()
                .put(2, asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48))
                .put(3, asList(3, 9, 15, 21, 27, 33, 39, 45))
                .put(5, asList(5, 25, 35))
                .put(7, asList(7, 49))
                .put(11, asList(11))
                .put(13, asList(13))
                .put(17, asList(17))
                .put(19, asList(19))
                .put(23, asList(23))
                .put(29, asList(29))
                .put(31, asList(31))
                .put(37, asList(37))
                .put(41, asList(41))
                .put(43, asList(43))
                .put(47, asList(47))
                .build()));
    }

    private static List<Integer> rangeAsList(final int from, final int to) {
        return IntStream.range(from, to).boxed().collect(toList());
    }

    static int smallestDivisor(int n) {
        assert n >= 2;

        int sqrt_n = (int) floor(sqrt(n));
        int k = 2;
        while (k <= sqrt_n) {
            if (n % k == 0) {
                return k;
            } else {
                k = k + 1;
            }
        }
        return n;
    }
}
