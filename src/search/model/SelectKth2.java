package search.model;

import java.util.List;

/**
 * use QuickSort qsp iteratively, from 0th
 * User: Ding
 * Date: 5/29/13
 * Time: 9:35 PM
 */
public class SelectKth2 implements SelectKthI {
    private QuickSortPartition qsp;

    public SelectKth2() {
        qsp = new QuickSortPartition();
    }

    public <T extends Comparable<T>> T select(int keyIndex, List<T> numbers) {
        if(keyIndex < 0 || keyIndex >= numbers.size() ) {
            throw new IllegalStateException("out of index: " +
                    "keyIndex = " + keyIndex + ", "  +
                    "array size = " + numbers.size() + ".");
        }

        int left = 0;
        int right = numbers.size() - 1;
        while (true) {
            int pivotIndex = qsp.partition(left,right,numbers,left);
            int pivotDist = pivotIndex - left;
            if (pivotDist == keyIndex) {
                return numbers.get(pivotIndex);
            }
            else if(pivotDist > keyIndex) {
                right = pivotIndex - 1;
            }
            else {
                keyIndex -= pivotDist + 1;
                left = pivotIndex + 1;
            }
        }
    }


}
