package Report.Mathematica;

/**
  * User: Ding
 * Date: 5/12/13
 * Time: 12:41 AM
 */
public class RecordTime {
    private long startTime;
    private long retTime;

    public RecordTime() {
        startTime = 0;
        retTime = 0;
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void end() {
        retTime += System.currentTimeMillis() - startTime;
    }

    public long calc() {
        long ret = retTime;
        startTime = 0;
        retTime = 0;

        return ret;
    }
}