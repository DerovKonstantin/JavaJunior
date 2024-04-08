package task1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Task1 {

    /*
    Урок 1. Лямбды и Stream API
    1. Напишите программу, которая использует Stream API для обработки списка чисел. 
    Программа должна вывести на экран среднее значение всех четных чисел в списке.
    */

    public static void main(String[] args) throws Exception {

        List<Integer> nums = Arrays.asList(-4,-5,-2,-3,-1,2,0,1,3,4,6,7,5,9);

        // nums = nums.stream()
        //     .filter(num -> num > 0)
        //     .filter(num -> num % 2 == 0)
        //     .toList();
        // int sum_nums = nums.stream()
        //     .reduce(0, (a, b) -> a + b);
        // System.out.println(sum_nums / nums.size());

        //Integer[] counter = {0};
        AtomicInteger counter = new AtomicInteger(0);
        int sum_nums = nums.stream()
            .filter(num -> num > 0)
            .filter(num -> num % 2 == 0)
            .reduce(0, (a, b) -> { 
                // counter[0]++; 
                counter.getAndIncrement();
                return a + b;
            });
        // System.out.println(sum_nums / counter[0]);
        System.out.println(sum_nums / counter.get());

        

    }

}
