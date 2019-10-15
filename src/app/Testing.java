package app;

import java.util.LinkedList;

/**
 * Testing
 */
public class Testing {

    public static void main(String[] args) {
        LinkedList<Integer> numbers = new LinkedList<Integer>();

        numbers.add(1);
        numbers.add(10);
        numbers.add(0);
        numbers.add(2);
        numbers.add(3);
        numbers.add(11);
        numbers.add(7);
        numbers.add(8);

        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < numbers.size(); i++) {
            int cost = numbers.get(i);
            if (cost < min) {
                min = cost;
                index = i;
            }
        }

        System.out.println(numbers.get(index));

    }
}