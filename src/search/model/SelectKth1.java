package search.model;

import java.util.List;

/**
 * select using merger sort, from 0th
 * User: Ding
 * Date: 5/29/13
 * Time: 8:53 PM
 */
public class SelectKth1 {
    public <T extends Comparable<T>> T select(int keyIndex, List<T> numbers) {
        MergeSort ms = new MergeSort();
        ms.sort(numbers);
        return numbers.get(keyIndex);
    }
}
