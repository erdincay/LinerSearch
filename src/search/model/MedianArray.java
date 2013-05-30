package search.model;

import java.util.List;

/**
 * User: Ding
 * Date: 5/30/13
 * Time: 12:42 PM
 */
public class MedianArray<T extends Comparable<T>> implements Comparable{
    private List<T> oriArray;
    private int low;
    private int high;

    public MedianArray(List<T> oriArray, int low, int high) {
        this.oriArray = oriArray;
        this.low = low;
        this.high = high;
        insertSort(low,high,oriArray);
    }

    private <T extends Comparable<T>> void insertSort(int left, int right, List<T> list) {
        for (int j, i = left + 1; i <= right; i++) {
            T tmp = list.get(i);
            for (j = i; ((j > left) && (list.get(j - 1).compareTo(tmp) > 0)); j--) {
                list.set(j, list.get(j-1));
            }
            list.set(j,tmp);
        }
    }

    private T getMedian() {
        return oriArray.get((low + high) / 2);
    }

    @Override
    public int compareTo(Object o) {
        return getMedian().compareTo((T)o);
    }
}
