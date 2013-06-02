package report.mathematica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User: Ding
 * Date: 5/31/13
 * Time: 1:05 PM
 */
public class Records extends TreeMap<Integer,Record> {
    private String name;

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
        else if (size <= 500000) {
            ret = 4;
        }
        else if (size <= 1000000) {
            ret = 2;
        }
        else {
            ret = 1;
        }

        return ret;
    }

    private void writeRecords() throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();

        BufferedWriter out = new BufferedWriter(new FileWriter(name + ".rd", true));
        out.newLine();
        out.write(dateFormat.format(date) + " : ");
        out.write(this.toString());
        out.close();
    }

    public void AddRecord(Integer key, Record value) throws IOException {
        put(key, value);
        writeRecords();
    }

    public String getName() {
        return name;
    }

    public List<Records> Split(int div) {
        int subNum = this.size() / div;
        List<Records> list = new ArrayList<Records>();

        int count = 0;
        Records rds = new Records(getName());
        for (Map.Entry entry : this.entrySet()) {
            if (count != 0 && count % subNum == 0 && this.size() - count >= subNum) {
                list.add(rds);
                rds = new Records(getName());
            }

            rds.put((Integer)entry.getKey(), (Record)entry.getValue());

            count++;
        }
        list.add(rds);

        return list;
    }

    @Override
    public String toString() {
        StringBuilder sRet = new StringBuilder();
        sRet.append("{ ");
        Iterator iterator = this.entrySet().iterator();
        while (iterator.hasNext()) {
            sRet.append("{");
            Map.Entry entry = (Map.Entry) iterator.next();
            sRet.append(entry.getKey());
            sRet.append(", ");
            sRet.append(entry.getValue());
            if (iterator.hasNext()) {
                sRet.append("}, ");
            }
            else {
                sRet.append("}");
            }
        }
        sRet.append("};");

        return sRet.toString();
    }
}
