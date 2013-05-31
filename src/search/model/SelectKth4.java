package search.model;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Ding
 * Date: 5/30/13
 * Time: 1:10 AM
 */
public class SelectKth4 implements SelectKthI {
    private final static int subGroupSize = 5;
    private QuickSortPartition qsp;

    public SelectKth4() {
        qsp = new QuickSortPartition();
    }

    private <T extends Comparable<T>> T sortSelect(int left, int right, List<T> list, int keyIndex) {
        for (int j, i = left + 1; i <= right; i++) {
            T tmp = list.get(i);
            for (j = i; ((j > left) && (list.get(j - 1).compareTo(tmp) > 0)); j--) {
                list.set(j, list.get(j-1));
            }
            list.set(j,tmp);
        }

        return list.get(keyIndex);
    }

    public <T extends Comparable<T>> T select(int keyIndex, List<T> numbers) {
        return select_itera(0,numbers.size() - 1,numbers,keyIndex);
    }

    private <T extends Comparable<T>> T getMedianOfMedian(int low, int high, List<T> numbers) {
        int size = high - low + 1;
        if (size <= subGroupSize) {
            return sortSelect(low, high, numbers, (low + high) / 2);
        }

        int numMedians = size / subGroupSize;
        List<MedianArray> medians = new ArrayList<MedianArray>();
        for (int i=0;i<numMedians;i++) {
            int left = low + i * subGroupSize;
            int right = left + subGroupSize - 1;
            if (right > high) {
                right = high;
            }
            MedianArray ma = new MedianArray(numbers,left,right );
            medians.add(ma);
        }

        return (T)getMedianOfMedian(0, medians.size() - 1, medians);
    }
    private <T extends Comparable<T>> T select_itera(int low, int high, List<T> numbers, int keyIndex) {
        int size = high - low + 1;
        if (size <= subGroupSize) {
            return sortSelect(low, high, numbers, keyIndex);
        }

        MedianArray ma = (MedianArray)getMedianOfMedian(low,high,numbers);
        T medianVal = (T)ma.getMedianVal();
        int medianIndex = ma.getMedianIndex();

        int pivotIndex = qsp.partition(low,high,numbers,medianIndex);

        if (keyIndex == pivotIndex) {
            return medianVal;
        }
        else if (keyIndex < pivotIndex) {
            return select_itera(0, pivotIndex - 1,numbers,keyIndex);
        }
        else {
            return select_itera(pivotIndex + 1,high,numbers,keyIndex);
        }
    }
}
