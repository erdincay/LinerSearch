package tests.correctness;

import org.junit.Before;
import org.junit.Test;
import search.model.SelectKth4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * User: Ding
 * Date: 5/30/13
 * Time: 1:12 AM
 */
public class SelectKth4Test {
    private List<Integer> numbers;
    private List<Integer> copy_numbers;
    private final static int SIZE = 5333;
    private final static int MAX = 10000;

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
        SelectKth4 sk = new SelectKth4();
        assertEquals(copy_numbers.get(index),sk.select(index,numbers));
    }

    @Test
    public void testSelectQuater() throws Exception {
        int index = numbers.size() / 4;
        SelectKth4 sk = new SelectKth4();
        assertEquals(copy_numbers.get(index),sk.select(index,numbers));
    }

    @Test
    public void testSelectHalf() throws Exception {
        int index = numbers.size() / 2;
        SelectKth4 sk = new SelectKth4();
        assertEquals(copy_numbers.get(index),sk.select(index,numbers));
    }

    @Test
    public void testSelectRevQuater() throws Exception {
        int index = (numbers.size() * 3) / 4;
        SelectKth4 sk = new SelectKth4();
        assertEquals(copy_numbers.get(index),sk.select(index,numbers));
    }

    @Test
    public void testSelectLast() throws Exception {
        int index = numbers.size() - 1;
        SelectKth4 sk = new SelectKth4();
        assertEquals(copy_numbers.get(index),sk.select(index,numbers));
    }
}
