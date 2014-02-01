package tests.performance;

import report.mathematica.Record;
import report.mathematica.RecordTime;
import report.mathematica.Records;
import org.junit.BeforeClass;
import org.junit.Test;
import search.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * User: Ding
 * Date: 5/31/13
 * Time: 11:42 AM
 * select with k = 1, n/4, n/2, 3n/4, and n respectively but record their executed time respectively
 */
public class SelectKthPerformanceTestSeparate {
    private static List<List<Integer>> OriLists;
    private static int digit = 7;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        //int[] lengthList = {10, 50, 100, 250, 500, 1000,2500,5000,10000};
        List<Integer> lengthList = new ArrayList<Integer>();
        lengthList.add(10);
        lengthList.add(50);
        for (int i = 2; i < digit; i++) {
            lengthList.add(lengthList.get(lengthList.size() - 1) * 2);
            lengthList.add(lengthList.get(lengthList.size() - 1) * 5 / 2);
            lengthList.add(lengthList.get(lengthList.size() - 1) * 2);
        }

        Random generator = new Random();
        OriLists = new ArrayList<List<Integer>>();
        for (int length : lengthList) {
            List<Integer> numbers = new ArrayList<Integer>();
            for (int j = 0; j < length; j++) {
                numbers.add(generator.nextInt(length * 5));
            }
            OriLists.add(numbers);
        }

        System.out.println("@BeforeClass");
    }

    @Test
    public void testSelectKth1() throws Exception {
        SelectKth1 sk = new SelectKth1();
        //Records rds = new Records("SelectKth1.rd");
        testSelect(sk, "SelectKth1.rd", OriLists);
        System.out.println("testSelectKth1 over");
    }

    @Test
    public void testSelectKth2() throws Exception {
        SelectKth2 sk = new SelectKth2();
        //Records rds = new Records("SelectKth2.rd");
        testSelect(sk, "SelectKth2.rd", OriLists);
        System.out.println("testSelectKth2 over");
    }

    @Test
    public void testSelectKth3() throws Exception {
        SelectKth3 sk = new SelectKth3();
        //Records rds = new Records("SelectKth3.rd");
        testSelect(sk, "SelectKth3.rd", OriLists);
        System.out.println("testSelectKth3 over");
    }

    @Test
    public void testSelectKth4() throws Exception {
        SelectKth4 sk = new SelectKth4();
        //Records rds = new Records("SelectKth4.rd");
        testSelect(sk, "SelectKth4.rd", OriLists);
        System.out.println("testSelectKth4 over");
    }

    private void testSelect(SelectKthI sk, String recordName, List<List<Integer>> numberLists) throws Exception {
        List<Records> rdsList = new ArrayList<Records>();
        List<RecordTime> rtList = new ArrayList<RecordTime>();
        for (int i = 1; i <= 5; i++) {
            rdsList.add(new Records(recordName + i));
            rtList.add(new RecordTime());
        }

        for (List<Integer> itera : numberLists) {
            int repeats = Records.calcRepeats(itera.size());
            for (int i = 0; i < repeats; i++) {
                int count = 0;
                {
                    List<Integer> numbers = new ArrayList<Integer>(itera);
                    rtList.get(count).start();
                    sk.select(0, numbers);
                    rtList.get(count).end();
                    count++;
                }
                {
                    List<Integer> numbers = new ArrayList<Integer>(itera);
                    rtList.get(count).start();
                    sk.select(numbers.size() / 4, numbers);
                    rtList.get(count).end();
                    count++;
                }
                {
                    List<Integer> numbers = new ArrayList<Integer>(itera);
                    rtList.get(count).start();
                    sk.select(numbers.size() / 2, numbers);
                    rtList.get(count).end();
                    count++;
                }
                {
                    List<Integer> numbers = new ArrayList<Integer>(itera);
                    rtList.get(count).start();
                    sk.select(numbers.size() * 3 / 4, numbers);
                    rtList.get(count).end();
                    count++;
                }
                {
                    List<Integer> numbers = new ArrayList<Integer>(itera);
                    rtList.get(count).start();
                    sk.select(numbers.size() - 1, numbers);
                    rtList.get(count).end();
                    count++;
                }
            }

            for (int i = 0; i < rdsList.size(); i++) {
                rdsList.get(i).AddRecord(itera.size(), new Record(rtList.get(i).calc(), repeats));
            }
        }
    }
}
