package search.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: Ding
 * Date: 5/30/13
 * Time: 1:10 AM
 */
public class SelectKth4 {
    private final static int subGroupSize = 5;

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

    private <T extends Comparable<T>> T select_itera(int low, int high, List<T> numbers, int keyIndex) {
        int size = high - low + 1;
        if (size <= subGroupSize) {
            return sortSelect(low, high, numbers, keyIndex);
        }

        int numMedians = size / subGroupSize;
        List<MedianArray> medians = new ArrayList<MedianArray>();
        for (int i=0;i<numMedians;i++) {
            int left = low + i * subGroupSize;
            int right = left + subGroupSize - 1;
            if (right > high) {
                right = high;
            }
            MedianArray ma = new MedianArray(numbers,left,right);
            medians.add(ma);
        }

        MedianArray mm = select_itera(0, medians.size() - 1, medians, numMedians / 2);

        return null;
    }
}
