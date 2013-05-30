package search.model;

import java.util.List;

/**
 * select using merger sort
 * User: Ding
 * Date: 5/29/13
 * Time: 8:53 PM
 */
public class SelectKth1 {
    public Integer select(int index, List<Integer> numbers) {
        MergeSort ms = new MergeSort();
        ms.sort(numbers);
        return numbers.get(index);
    }
}
