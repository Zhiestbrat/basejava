package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author p.kondakov
 */
public class HomeWork {
    public static void main(String[] args) {
        /*System.out.println(minValue(new int[]{1, 2, 3, 3, 2, 3}));
        System.out.println(minValue(new int[]{9,8}));*/
        System.out.println(oddOrEven(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    public static int minValue(int[] values) {
        return Arrays.stream(values)
                .sorted()
                .distinct()
                .reduce(0, (x, y) -> x * 10 + y);
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream()
                .reduce(0, Integer::sum);
        return integers.stream()
                .filter(x -> (sum % 2 == 0) == (x % 2 == 0))
                .collect(Collectors.toList());
       /* int sum = 0;
        for (int x : integers) {
            sum += x;
        }
        List<Integer> result = new ArrayList<>();
        for (int x : integers) {
            if ((sum % 2 == 0) == (x % 2 == 0)) {
                result.add(x);
            }
        }
        return result;*/
    }
}
