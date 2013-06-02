package tests.performance;

import report.mathematica.Mathematica;
import report.mathematica.Record;
import report.mathematica.RecordTime;
import report.mathematica.Records;
import org.junit.AfterClass;
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
 * select with k = 1, n/4, n/2, 3n/4, and n respectively but record their average executed time
 */
public class SelectKthPerformanceTest {
    // for report file format
    private static List<Records> recordsList;

    //Test data
    private static List<List<Integer>> OriLists;
    private static int  digit = 7; // max digits of the automatically generated number array

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        recordsList = new ArrayList<Records>();

        List<Integer> lengthList = new ArrayList<Integer>();
        lengthList.add(10);
        lengthList.add(50);
        for (int i=2;i<digit;i++) {
            lengthList.add(lengthList.get(lengthList.size()-1) * 2);
            lengthList.add(lengthList.get(lengthList.size()-1) * 5 / 2);
            lengthList.add(lengthList.get(lengthList.size()-1) * 2);
        }

        Random generator = new Random();
        OriLists = new ArrayList<List<Integer>>();
        for (int length : lengthList){
            List<Integer> numbers = new ArrayList<Integer>();
            for (int j=0; j<length;j++) {
                numbers.add(generator.nextInt(length * 5));
            }
            OriLists.add(numbers);
        }

        System.out.println("@BeforeClass");
    }

    @AfterClass
    public static void cleanUpAfterClass() throws Exception {
        List<Records> listL = new ArrayList<Records>();
        List<Records> listR = new ArrayList<Records>();
        for (Records rds : recordsList) {
            List<Records> list = rds.Split(2);
            listL.add(list.get(0));
            listR.add(list.get(1));
        }

        Mathematica rpt = new Mathematica(recordsList);
        Mathematica rptL = new Mathematica(listL);
        Mathematica rptR = new Mathematica(listR);

        rpt.write("analysis.txt",true);
        rpt.compare("analysis.txt","sk1","sk4");
        rpt.compare("analysis.txt","sk2","sk3");

        rptL.write("analysis.txt",false);
        rptL.compare("analysis.txt","sk1","sk4");
        rptL.compare("analysis.txt","sk2","sk3");

        rptR.write("analysis.txt",false);
        rpt.compare("analysis.txt","sk1","sk4");
        rpt.compare("analysis.txt","sk2","sk3");

        System.out.println("@AfterClass");
    }

    @Test
    public void testSelectKth1() throws Exception {
        SelectKth1 sk = new SelectKth1();
        Records rds = new Records("sk1");
        testSelect(sk, rds, OriLists);
        recordsList.add(rds);
        System.out.println("testSelectKth1 over");
    }

    @Test
    public void testSelectKth2() throws Exception {
        SelectKth2 sk = new SelectKth2();
        Records rds = new Records("sk2");
        testSelect(sk, rds, OriLists);
        recordsList.add(rds);
        System.out.println("testSelectKth2 over");
    }

    @Test
    public void testSelectKth3() throws Exception {
        SelectKth3 sk = new SelectKth3();
        Records rds = new Records("sk3");
        testSelect(sk, rds, OriLists);
        recordsList.add(rds);
        System.out.println("testSelectKth3 over");
    }

    @Test
    public void testSelectKth4() throws Exception {
        SelectKth4 sk = new SelectKth4();
        Records rds = new Records("sk4");
        testSelect(sk, rds, OriLists);
        recordsList.add(rds);
        System.out.println("testSelectKth4 over");
    }

    //@Test
    public void testSelectKth5() throws Exception {
        SelectKth2 sk = new SelectKth2();
        Records rds = new Records("sk5");
        testSelect(sk, rds, OriLists);
        recordsList.add(rds);
        System.out.println("testSelectKth5 over");
    }

    //@Test
    public void testSelectKth6() throws Exception {
        SelectKth3 sk = new SelectKth3();
        Records rds = new Records("sk6");
        testSelect(sk, rds, OriLists);
        recordsList.add(rds);
        System.out.println("testSelectKth6 over");
    }

    private Records testSelect(SelectKthI sk, Records rds,List<List<Integer>> numberLists) throws Exception {
        for (List<Integer> itera : numberLists) {
            int repeats = Records.calcRepeats(itera.size()) * 2 * 10;
            RecordTime rt = new RecordTime();
            for (int i=0; i<repeats; i++) {
                {
                    List<Integer> numbers = new ArrayList<Integer>(itera);
                    rt.start();
                    sk.select(0,numbers);
                    rt.end();
                }
                {
                    List<Integer> numbers = new ArrayList<Integer>(itera);
                    rt.start();
                    sk.select(numbers.size() / 4, numbers);
                    rt.end();
                }
                {
                    List<Integer> numbers = new ArrayList<Integer>(itera);
                    rt.start();
                    sk.select(numbers.size() / 2, numbers);
                    rt.end();
                }
                {
                    List<Integer> numbers = new ArrayList<Integer>(itera);
                    rt.start();
                    sk.select(numbers.size() * 3 / 4, numbers);
                    rt.end();
                }
                {
                    List<Integer> numbers = new ArrayList<Integer>(itera);
                    rt.start();
                    sk.select(numbers.size() - 1, numbers);
                    rt.end();
                }
            }
            rds.AddRecord(itera.size(), new Record(rt.calc(), repeats * 5));
        }

        return rds;
    }
}
