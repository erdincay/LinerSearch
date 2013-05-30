package search.model;

import java.util.List;

/**
 * use QuickSort qsp recursively, from 0th
 * User: Ding
 * Date: 5/29/13
 * Time: 9:35 PM
 */
public class SelectKth3 {
    private QuickSortPartition qsp;

    public SelectKth3() {
        qsp = new QuickSortPartition();
    }

    public <T extends Comparable<T>> T select(int keyIndex, List<T> numbers) {
        if(keyIndex < 0 || keyIndex >= numbers.size() ) {
            throw new IllegalStateException("out of index: " +
                    "keyIndex = " + keyIndex + ", "  +
                    "array size = " + numbers.size() + ".");
        }
        return select_itera(0,numbers.size() - 1,numbers,keyIndex);
    }

    private <T extends Comparable<T>> T select_itera(int low, int high, List<T> numbers,int keyIndex) {
        if(low == high) {
            return numbers.get(low);
        }

        int pivotIndex = qsp.partition(low,high,numbers,low);
        int pivotDist = pivotIndex - low;

        if (pivotDist == keyIndex) {
            return numbers.get(pivotIndex);
        }
        else if (pivotDist > keyIndex) {
            return select_itera(low,pivotIndex-1,numbers,keyIndex);
        }
        else {
            return select_itera(pivotIndex+1,high,numbers,keyIndex - (pivotDist + 1));
        }
    }


}
