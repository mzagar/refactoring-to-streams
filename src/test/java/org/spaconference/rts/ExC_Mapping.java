package org.spaconference.rts;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(Theories.class)
public class ExC_Mapping {

    @DataPoint
    public static Function<List<String>, List<Integer>> FOR_LOOP = strings -> {
        List<Integer> result = new ArrayList<>();
        for (String string : strings) {
            result.add(Integer.parseInt(string));
        }
        return result;
    };

    @DataPoint
    public static Function<List<? extends String>, List<? extends Integer>> STREAM = null;


    @Theory
    public void test(Function<List<String>, List<Integer>> f) {
        assertThat(f.apply(asList("2", "3", "5", "7")), equalTo(asList(2, 3, 5, 7)));
    }

}