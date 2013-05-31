package tests.correctness;

import org.junit.Before;
import org.junit.Test;
import search.model.SelectKth2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * User: Ding
 * Date: 5/29/13
 * Time: 9:03 PM
 */
public class SelectKth2Test {
    private List<Integer> numbers;
    private List<Integer> copy_numbers;
    private final static int SIZE = 500;
    private final static int MAX = 1000;

    @Before
    public void setUp() throws Exception {
        numbers = new ArrayList<Integer>();
        Random generator = new Random();
        for (int i = 0; i < SIZE; i++) {
            numbers.add(generator.nextInt(MAX));
        }

        copy_numbers = new ArrayList<Integer>(numbers);
        Collections.sort(copy_numbers);
    }

    @Test
    public void testSelect1st() throws Exception {
        int index = 0;
        SelectKth2 sk = new SelectKth2();
        assertEquals(copy_numbers.get(index),sk.select(index,numbers));
    }

    @Test
    public void testSelectQuater() throws Exception {
        int index = numbers.size() / 4;
        SelectKth2 sk = new SelectKth2();
        assertEquals(copy_numbers.get(index),sk.select(index,numbers));
    }

    @Test
    public void testSelectHalf() throws Exception {
        int index = numbers.size() / 2;
        SelectKth2 sk = new SelectKth2();
        assertEquals(copy_numbers.get(index),sk.select(index,numbers));
    }

    @Test
    public void testSelectRevQuater() throws Exception {
        int index = (numbers.size() * 3) / 4;
        SelectKth2 sk = new SelectKth2();
        assertEquals(copy_numbers.get(index),sk.select(index,numbers));
    }

    @Test
    public void testSelectLast() throws Exception {
        int index = numbers.size() - 1;
        SelectKth2 sk = new SelectKth2();
        assertEquals(copy_numbers.get(index),sk.select(index,numbers));
    }
}
