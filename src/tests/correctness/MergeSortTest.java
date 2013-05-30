package tests.correctness;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import search.model.MergeSort;

/**
  * User: Ding
 * Date: 5/29/13
 * Time: 5:03 PM
  */
public class MergeSortTest {
    private List<Integer> numbers;
    private final static int SIZE = 20;
    private final static int MAX = 1000;

    @Before
    public void setUp() throws Exception {
        numbers = new ArrayList<Integer>();
        Random generator = new Random();
        for (int i = 0; i < SIZE; i++) {
            numbers.add(generator.nextInt(MAX));
        }
    }

    @Test
    public void testMergeSort() {
        long startTime = System.currentTimeMillis();
        MergeSort sorter = new MergeSort();
        sorter.sort(numbers);
        long stopTime = System.currentTimeMillis();

        long elapsedTime = stopTime - startTime;
        System.out.println("MergeSort " + elapsedTime);

        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) > numbers.get(i + 1)) {
                fail("Should not happen");
            }
        }
    }

    @Test
    public void itWorksRepeatably() {
        for (int i = 0; i < 50; i++) {
            numbers = new ArrayList<Integer>();
            Random generator = new Random();
            for (int k = 0; k < SIZE; k++) {
                numbers.add(generator.nextInt(MAX));
            }

            MergeSort sorter = new MergeSort();
            sorter.sort(numbers);
            for (int j = 0; j < numbers.size() - 1; j++) {
                if (numbers.get(j) > numbers.get(j + 1)) {
                    fail("Should not happen");
                }
            }
        }
    }

    @Test
    public void testStandardSort() {
        List<Integer> copy_numbers = new ArrayList<Integer>(numbers);
        long startTime = System.currentTimeMillis();
        Collections.sort(copy_numbers);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Standard Java sort " + elapsedTime);

        MergeSort sorter = new MergeSort();
        sorter.sort(numbers);

        for (int i = 0; i < numbers.size(); i++) {
            if (!numbers.get(i).equals(copy_numbers.get(i))) {
                fail("different from standard java sort");
            }
        }
    }
}
