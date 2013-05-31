package tests.performance;

import org.junit.Before;
import org.junit.Test;
import search.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * User: Ding
 * Date: 5/31/13
 * Time: 11:42 AM
 */
public class SelectKthPerformanceTest {
    private List<List<Integer>> numberLists;

    @Before
    public void setUp() throws Exception {
        int[] lengthList = {10, 50, 100, 250, 500, 1000, 2500, 5000, 10000, 25000, 50000, 100000, 250000, 500000};

        Random generator = new Random();
        numberLists = new ArrayList<List<Integer>>();
        for (int length : lengthList){
            List<Integer> numbers = new ArrayList<Integer>();
            for (int j=0; j<length;j++) {
                numbers.add(generator.nextInt(length * 4));
            }
            numberLists.add(numbers);
        }
    }

    @Test
    public void testSelectKth1() throws Exception {
        SelectKth1 sk = new SelectKth1();
        testSelect(sk,new Records("SelectKth1.rd"));
    }

    @Test
    public void testSelectKth2() throws Exception {
        SelectKth2 sk = new SelectKth2();
        testSelect(sk,new Records("SelectKth2.rd"));
    }

    @Test
    public void testSelectKth3() throws Exception {
        SelectKth3 sk = new SelectKth3();
        testSelect(sk,new Records("SelectKth3.rd"));
    }

    @Test
    public void testSelectKth4() throws Exception {
        SelectKth4 sk = new SelectKth4();
        testSelect(sk,new Records("SelectKth4.rd"));
    }

    private void testSelect(SelectKthI sk, Records rds) throws Exception {
        for (List<Integer> numbers : numberLists) {
            int repeats = Records.calcRepeats(numbers.size());
            RecordTime rt = new RecordTime();
            rt.start();
            for (int i=0; i<repeats; i++) {
                sk.select(0,numbers);
                sk.select(numbers.size() / 4, numbers);
                sk.select(numbers.size() / 2, numbers);
                sk.select(numbers.size() * 3 / 4, numbers);
                sk.select(numbers.size() - 1, numbers);
            }
            rt.end();
            rds.AddRecord(numbers.size(),new Record(rt.calc(),repeats * 5));
        }
    }
}
