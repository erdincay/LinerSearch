package tests.performance;

/**
 * User: Ding
 * Date: 5/12/13
 * Time: 11:01 AM
 * record the every executed time as [executed milliseconds / executed times]
 */
public class Record {
    public long execTime_;
    public int repeats_;

    public Record(long execTime, int repeats) {
        this.execTime_ = execTime;
        this.repeats_ = repeats;
    }

    @Override
    public String toString() {
        if (repeats_ == 1) {
            return Long.toString(execTime_);
        }
        else {
            return execTime_ + "/" + repeats_;
        }
    }
}
