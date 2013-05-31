package tests.performance;

/**
  * User: Ding
 * Date: 5/12/13
 * Time: 12:41 AM
 */
public class RecordTime {
    private long startTime_;
    private long endTime_;

    public void start() {
        startTime_ = System.currentTimeMillis();
    }

    public void end() {
        endTime_ = System.currentTimeMillis();
    }

    public long calc() {
        return endTime_ - startTime_;
    }
}