package tests.performance;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * User: Ding
 * Date: 5/31/13
 * Time: 1:05 PM
 */
public class Records extends TreeMap<Integer,Record> {
    private String name;
    //int[] lengthList = {10, 50, 100, 250, 500, 1000, 2500, 5000, 10000, 25000, 50000};

    public Records(String name) {
        this.name = name;
    }

    public static int calcRepeats(int size) {
        int ret;

        if (size <= 10) {
            ret = 200000;
        } else if (size <= 50) {
            ret = 40000;
        } else if (size <= 100) {
            ret = 20000;
        } else if (size <= 250) {
            ret = 8000;
        } else if (size <= 500) {
            ret = 4000;
        } else if (size <= 1000) {
            ret = 2000;
        } else if (size <= 2500) {
            ret = 800;
        } else if (size <= 5000) {
            ret = 400;
        }
        else if (size <= 10000) {
            ret = 200;
        }
        else if (size <= 25000) {
            ret = 80;
        }
        else if (size <= 50000) {
            ret = 40;
        }
        else if (size <= 100000) {
            ret = 20;
        }
        else if (size <= 250000) {
            ret = 8;
        }
        else {
            ret = 1;
        }

        return ret;
    }

    private void writeRecords() throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();

        BufferedWriter out = new BufferedWriter(new FileWriter(name, true));
        out.newLine();
        out.write(dateFormat.format(date) + " : ");
        out.write(this.toString());
        out.close();
    }

    public void AddRecord(Integer key, Record value) throws IOException {
        put(key, value);
        writeRecords();
    }

    @Override
    public String toString() {
        StringBuilder sRet = new StringBuilder();
        sRet.append("{ ");
        for (Map.Entry entry : this.entrySet()) {
            sRet.append("{");
            sRet.append(entry.getKey());
            sRet.append(", ");
            sRet.append(entry.getValue());
            sRet.append("}, ");
        }
        sRet.append("}");

        return sRet.toString();
    }
}