package search.model;

import java.util.List;

/**
 * User: Ding
 * Date: 5/29/13
 * Time: 10:53 PM
 */
public class QuickSortPartition {
    public <T extends Comparable<T>> int partition(int low, int high, List<T> numbers, int pivotIndex) {
        if(low < 0 || low >= numbers.size() ||
                high < 0 || high >= numbers.size() ||
                low > high ||
                pivotIndex < low || pivotIndex > high) {
            throw new IllegalStateException("out of index: " +
                    "low = " + low + ", "  +
                    "high = " + high + ", "  +
                    "pivotIndex = " + pivotIndex + ", "  +
                    "array size = " + numbers.size() + ".");
        }

        T pivotVal = numbers.get(pivotIndex);
        swap(pivotIndex,high,numbers);
        int retIndex = low;
        for(int i = low; i <= high; i++) {
            if (numbers.get(i).compareTo(pivotVal) < 0) {
                swap(retIndex,i,numbers);
                retIndex++;
            }
        }
        swap(high,retIndex,numbers);

        return retIndex;
    }

    private <T extends Comparable<T>> void swap(int srcIndex, int dstIndex, List<T> numbers) {
        T temp = numbers.get(srcIndex);
        numbers.set(srcIndex,numbers.get(dstIndex));
        numbers.set(dstIndex,temp);
    }
}
