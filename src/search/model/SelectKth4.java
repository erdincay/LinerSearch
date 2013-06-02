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

    private <T extends Comparable<T>> int sortSelectIndex(int left, int right, List<T> list, int keyIndex) {
        for (int j, i = left + 1; i <= right; i++) {
            T tmp = list.get(i);
            for (j = i; ((j > left) && (list.get(j - 1).compareTo(tmp) > 0)); j--) {
                list.set(j, list.get(j-1));
            }
            list.set(j,tmp);
        }

        return left + keyIndex;
    }

    public <T extends Comparable<T>> T select(int keyIndex, List<T> numbers) {
        int index = select_itera(0,numbers.size() - 1,numbers,keyIndex);
        return numbers.get(index);
    }

    private <T extends Comparable<T>> int select_itera(int low, int high, List<T> numbers, int keyIndex) {
        int size = high - low + 1;
        if (size <= subGroupSize) {
            return sortSelectIndex(low, high, numbers, keyIndex);
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

        int index = select_itera(0, medians.size() - 1, medians, medians.size() / 2);
        MedianArray medianItem = medians.get(index);
        int medianIndex = medianItem.getMedianIndex();

        int pivotIndex = qsp.partition(low,high,numbers,medianIndex);
        int pivotDist = pivotIndex - low;

        if (keyIndex == pivotDist) {
            return pivotIndex;
        }
        else if (keyIndex < pivotDist) {
            return select_itera(low, pivotIndex - 1,numbers,keyIndex);
        }
        else {
            return select_itera(pivotIndex + 1,high,numbers,keyIndex-(pivotDist + 1));
        }
    }
}
