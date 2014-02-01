package report.mathematica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * User: Ding
 * Date: 6/1/13
 * Time: 5:57 PM
 */
class ReportRecords {
    private Records rds;
    private String PlotStyle;

    public ReportRecords(Records rds, String pls) {
        this.rds = rds;
        this.PlotStyle = pls;
    }

    String getPlotStyle() {
        return PlotStyle;
    }

    public String getName() {
        return rds.getName();
    }

    public String toString() {
        return rds.toString();
    }
}

public class Mathematica {
    private static final String[] PlotStyleTypes = {"Dotted", "Solid", "Dashed", "DotDashed"};
    private List<ReportRecords> rpRecordsList;

    public Mathematica(List<Records> records) {
        rpRecordsList = new ArrayList<ReportRecords>();
        for (int i = 0; i < records.size(); i++) {
            rpRecordsList.add(new ReportRecords(records.get(i), PlotStyleTypes[i % PlotStyleTypes.length]));
        }
    }

    private String ListLinePlotCompare(ReportRecords r1, ReportRecords r2) {
        List<ReportRecords> list = new ArrayList<ReportRecords>();
        list.add(r1);
        list.add(r2);

        return ListLinePlotAll(list);
    }

    private String ListLinePlotAll(List<ReportRecords> list) {
        StringBuilder strRet = new StringBuilder();
        strRet.append("ListLinePlot[{");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            ReportRecords rds = (ReportRecords) iterator.next();
            strRet.append(rds.getName());
            if (iterator.hasNext()) {
                strRet.append(", ");
            } else {
                strRet.append("}, ");
            }
        }

        strRet.append("PlotStyle -> {");
        iterator = list.iterator();
        while (iterator.hasNext()) {
            ReportRecords rds = (ReportRecords) iterator.next();
            strRet.append(rds.getPlotStyle());
            if (iterator.hasNext()) {
                strRet.append(", ");
            } else {
                strRet.append("}, ");
            }
        }

        strRet.append("PlotLegends -> Placed[{");
        iterator = list.iterator();
        while (iterator.hasNext()) {
            ReportRecords rds = (ReportRecords) iterator.next();
            strRet.append("\"");
            strRet.append(rds.getName());
            strRet.append("\"");
            if (iterator.hasNext()) {
                strRet.append(", ");
            } else {
                strRet.append("}, Bottom], ");
            }
        }

        strRet.append("AxesLabel -> {\"input size\", \"exec-time : milliseconds\"}, ");
        strRet.append("ImageSize -> Large]");

        return strRet.toString();
    }

    public void write(String filename, boolean timeTag) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(filename, true));

        if (timeTag) {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date date = new Date();
            out.newLine();
            out.newLine();
            out.write(dateFormat.format(date));
        }

        for (ReportRecords rds : rpRecordsList) {
            out.newLine();
            out.write(rds.getName() + " = " + rds.toString());
        }

        out.newLine();
        out.write(ListLinePlotAll(rpRecordsList));

        out.close();
    }

    public void compare(String filename, String r1, String r2) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(filename, true));

        out.newLine();
        out.write(ListLinePlotCompare(getRecordByName(r1), getRecordByName(r2)));

        out.close();
    }

    private ReportRecords getRecordByName(String name) {
        for (ReportRecords rds : rpRecordsList) {
            if (rds.getName().equals(name)) {
                return rds;
            }
        }

        return null;
    }
}
